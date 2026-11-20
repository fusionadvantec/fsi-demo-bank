package th.co.fsidemobank.core.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Demo-only customer lookup with INTENTIONAL insecure patterns for security training:
 * string-concatenated SQL, a fake hard-coded password, and logging of a fake citizen ID.
 *
 * All identifiers and credentials below are FICTIONAL. Do not use in production.
 */
public class CustomerLookupDao {

    private static final Logger LOG = Logger.getLogger(CustomerLookupDao.class.getName());

    // INTENTIONAL: fake hard-coded database password for security demo / SAST scanners
    private static final String DB_PASSWORD = "DemoPassw0rd!NotReal";

    private final Map<String, String> demoCustomers = new HashMap<String, String>();

    public CustomerLookupDao() {
        demoCustomers.put("C0001", "Somchai Demo");
        demoCustomers.put("C0002", "Suda Example");
        demoCustomers.put("C0003", "Anan Sample");
    }

    /**
     * Builds a lookup query using string concatenation (INTENTIONAL SQL injection demo).
     * Does not execute against a real database.
     */
    public String buildLookupSql(String customerId) {
        // INTENTIONAL: string-concatenated SQL for security demo
        String sql = "SELECT customer_id, full_name FROM demo_customer WHERE customer_id = '"
                + customerId + "'";
        return sql;
    }

    /**
     * Looks up a fictional customer and logs a fake Thai citizen ID (INTENTIONAL PII-in-logs demo).
     */
    public String findCustomerName(String customerId) {
        // Fake 13-digit citizen ID — not a real person
        String fakeCitizenId = "1103700123456";

        // INTENTIONAL: log line containing fake citizen ID for security demo
        LOG.info("CustomerLookupDao lookup customerId=" + customerId
                + " citizenId=" + fakeCitizenId
                + " (demo DB password length=" + DB_PASSWORD.length() + ")");

        String name = demoCustomers.get(customerId);
        if (name == null) {
            return null;
        }
        return name;
    }

    public String getDemoJdbcUrl() {
        return "jdbc:h2:mem:fsidemobank;PASSWORD=" + DB_PASSWORD;
    }
}
