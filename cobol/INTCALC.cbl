      ******************************************************************
      * INTCALC.cbl - Fictional tiered deposit interest for FSI Demo Bank
      * Demo / training only. All rates and amounts are fictional.
      *
      * Annual rate tiers (THB principal):
      *   0        - 100,000   : 0.50%
      *   100,000  - 1,000,000 : 1.25%
      *   above 1,000,000     : 1.75%
      *
      * Output: WS-DAILY-INT = principal * annual-rate / 365
      ******************************************************************
       IDENTIFICATION DIVISION.
       PROGRAM-ID. INTCALC.

       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01  WS-PRINCIPAL         PIC 9(13)V99 VALUE ZERO.
       01  WS-ANNUAL-RATE       PIC 9V9(6)  VALUE ZERO.
       01  WS-DAILY-INT         PIC 9(11)V99 VALUE ZERO.
       01  WS-DAYS-YEAR         PIC 9(3)    VALUE 365.

       01  WS-TIER1-LIMIT       PIC 9(13)V99 VALUE 100000.00.
       01  WS-TIER2-LIMIT       PIC 9(13)V99 VALUE 1000000.00.
       01  WS-RATE-TIER1        PIC 9V9(6)  VALUE 0.005000.
       01  WS-RATE-TIER2        PIC 9V9(6)  VALUE 0.012500.
       01  WS-RATE-TIER3        PIC 9V9(6)  VALUE 0.017500.

       LINKAGE SECTION.
       01  LK-PRINCIPAL         PIC 9(13)V99.
       01  LK-DAILY-INT         PIC 9(11)V99.

       PROCEDURE DIVISION USING LK-PRINCIPAL LK-DAILY-INT.
       MAIN-LOGIC.
           MOVE LK-PRINCIPAL TO WS-PRINCIPAL
           PERFORM RESOLVE-RATE
           COMPUTE WS-DAILY-INT ROUNDED =
               WS-PRINCIPAL * WS-ANNUAL-RATE / WS-DAYS-YEAR
           MOVE WS-DAILY-INT TO LK-DAILY-INT
           GOBACK.

       RESOLVE-RATE.
           IF WS-PRINCIPAL <= WS-TIER1-LIMIT
               MOVE WS-RATE-TIER1 TO WS-ANNUAL-RATE
           ELSE
               IF WS-PRINCIPAL <= WS-TIER2-LIMIT
                   MOVE WS-RATE-TIER2 TO WS-ANNUAL-RATE
               ELSE
                   MOVE WS-RATE-TIER3 TO WS-ANNUAL-RATE
               END-IF
           END-IF.

       END PROGRAM INTCALC.
