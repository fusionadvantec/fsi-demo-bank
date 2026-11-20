# Fund Transfer Architecture

ลำดับการโอนเงินเริ่มที่ `TransferController` ผ่าน `TransferService` เข้า `TransferProcessor` / `AccountService` แล้วบันทึก movement ที่ `LegacyDao` (stand-in ของ database แบบ in-memory สำหรับ demo — ปัจจุบัน `transfer-api` ยังเป็น stub ที่ยังไม่ได้เรียก `core-banking`)

```mermaid
sequenceDiagram
    actor Client
    participant TC as TransferController
    participant TS as TransferService
    participant TP as TransferProcessor
    participant AS as AccountService
    participant DB as LegacyDao<br/>(DB stand-in)

    Client->>TC: POST /api/v1/transfers<br/>{fromAccount, toAccount, amount}
    TC->>TS: transfer(from, to, amount)
    TS->>TS: validate accounts & amount
    TS->>TP: processTransfer(from, to, amount)
    TP->>TP: validate transfer rules

    TP->>AS: debit(from, amount)
    AS->>AS: check balance / update HashMap
    AS->>DB: recordMovement(from, -amount, DEBIT)
    DB-->>AS: ok

    TP->>AS: credit(to, amount)
    AS->>AS: update HashMap
    AS->>DB: recordMovement(to, +amount, CREDIT)
    DB-->>AS: ok

    AS-->>TP: done
    TP-->>TS: TXN-XXXXXXXX
    TS-->>TC: transferRef
    TC-->>Client: 200 {transferRef, status: ACCEPTED}
```
