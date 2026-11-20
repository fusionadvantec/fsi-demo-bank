package th.co.fsidemobank.transfer.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class TransferService {

    public String transfer(String fromAccount, String toAccount, BigDecimal amount) {
        if (fromAccount == null || fromAccount.isBlank()
                || toAccount == null || toAccount.isBlank()) {
            throw new IllegalArgumentException("fromAccount and toAccount are required");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }
        if (fromAccount.equals(toAccount)) {
            throw new IllegalArgumentException("cannot transfer to the same account");
        }
        return "API-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
