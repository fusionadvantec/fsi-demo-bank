package th.co.fsidemobank.core.interest;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Java-side mirror of fictional tiered deposit interest rules
 * (aligned with COBOL program INTCALC for demo purposes).
 *
 * Tiers (annual rate, fictional):
 *   0        – 100,000   THB : 0.50%
 *   100,000  – 1,000,000 THB : 1.25%
 *   above 1,000,000      THB : 1.75%
 */
public class InterestCalculator {

    private static final BigDecimal TIER1_LIMIT = new BigDecimal("100000");
    private static final BigDecimal TIER2_LIMIT = new BigDecimal("1000000");

    private static final BigDecimal RATE_TIER1 = new BigDecimal("0.0050");
    private static final BigDecimal RATE_TIER2 = new BigDecimal("0.0125");
    private static final BigDecimal RATE_TIER3 = new BigDecimal("0.0175");

    private static final int DAYS_IN_YEAR = 365;

    /**
     * Calculates simple daily interest for one day on the given principal.
     */
    public BigDecimal calculateDailyInterest(BigDecimal principal) {
        if (principal == null || principal.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        BigDecimal annualRate = resolveAnnualRate(principal);
        BigDecimal daily = principal
                .multiply(annualRate)
                .divide(new BigDecimal(DAYS_IN_YEAR), 6, RoundingMode.HALF_UP);

        return daily.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal resolveAnnualRate(BigDecimal principal) {
        if (principal.compareTo(TIER1_LIMIT) <= 0) {
            return RATE_TIER1;
        }
        if (principal.compareTo(TIER2_LIMIT) <= 0) {
            return RATE_TIER2;
        }
        return RATE_TIER3;
    }
}
