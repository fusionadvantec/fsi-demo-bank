package th.co.fsidemobank.transfer.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class TransferServiceTest {

    private final TransferService transferService = new TransferService();

    @Test
    void transferReturnsDemoReference() {
        String ref = transferService.transfer(
                "001-1-00001-0",
                "001-1-00002-0",
                new BigDecimal("100.00"));

        assertTrue(ref.startsWith("API-"));
        assertTrue(ref.length() > 4);
    }
}
