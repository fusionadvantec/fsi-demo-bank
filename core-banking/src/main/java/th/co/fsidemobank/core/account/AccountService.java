package th.co.fsidemobank.core.account;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import th.co.fsidemobank.core.dao.LegacyDao;

/**
 * In-memory account operations for the fictional FSI Demo Bank core module.
 */
public class AccountService {

    private final LegacyDao legacyDao;
    private final Map<String, BigDecimal> balances = new HashMap<String, BigDecimal>();

    public AccountService(LegacyDao legacyDao) {
        this.legacyDao = legacyDao;
        seedDemoBalances();
    }

    private void seedDemoBalances() {
        balances.put("001-1-00001-0", new BigDecimal("150000.00"));
        balances.put("001-1-00002-0", new BigDecimal("87500.50"));
        balances.put("001-2-00010-9", new BigDecimal("2500000.00"));
    }

    public BigDecimal getBalance(String accountNo) {
        BigDecimal balance = balances.get(accountNo);
        if (balance == null) {
            throw new IllegalArgumentException("Unknown demo account: " + accountNo);
        }
        return balance;
    }

    public void debit(String accountNo, BigDecimal amount) {
        BigDecimal current = getBalance(accountNo);
        if (current.compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient funds on demo account " + accountNo);
        }
        balances.put(accountNo, current.subtract(amount));
        legacyDao.recordMovement(accountNo, amount.negate(), "DEBIT");
    }

    public void credit(String accountNo, BigDecimal amount) {
        BigDecimal current = getBalance(accountNo);
        balances.put(accountNo, current.add(amount));
        legacyDao.recordMovement(accountNo, amount, "CREDIT");
    }
}
