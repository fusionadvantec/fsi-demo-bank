package th.co.fsidemobank.core.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Lightweight stand-in for a legacy persistence layer (fictional demo only).
 */
public class LegacyDao {

    public static final class Movement {
        private final String accountNo;
        private final BigDecimal amount;
        private final String type;

        public Movement(String accountNo, BigDecimal amount, String type) {
            this.accountNo = accountNo;
            this.amount = amount;
            this.type = type;
        }

        public String getAccountNo() {
            return accountNo;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public String getType() {
            return type;
        }
    }

    private final List<Movement> movements = new ArrayList<Movement>();

    public void recordMovement(String accountNo, BigDecimal amount, String type) {
        movements.add(new Movement(accountNo, amount, type));
    }

    public List<Movement> findMovements() {
        return Collections.unmodifiableList(movements);
    }
}
