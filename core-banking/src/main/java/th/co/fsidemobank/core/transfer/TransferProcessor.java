package th.co.fsidemobank.core.transfer;

import java.math.BigDecimal;
import java.util.UUID;

import th.co.fsidemobank.core.account.AccountService;

/**
 * Fictional fund-transfer orchestration for FSI Demo Bank (demo only).
 */
public class TransferProcessor {

    private final AccountService accountService;

    public TransferProcessor(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Moves funds between two demo accounts. Returns a fictional transfer reference.
     */
    public String processTransfer(String fromAccountNo, String toAccountNo, BigDecimal amount) {
        if (fromAccountNo == null || toAccountNo == null) {
            throw new IllegalArgumentException("Account numbers are required");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        if (fromAccountNo.equals(toAccountNo)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        accountService.debit(fromAccountNo, amount);
        accountService.credit(toAccountNo, amount);

        return "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
