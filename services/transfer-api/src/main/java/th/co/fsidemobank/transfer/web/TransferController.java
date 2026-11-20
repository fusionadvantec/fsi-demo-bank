package th.co.fsidemobank.transfer.web;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.co.fsidemobank.transfer.service.TransferService;

@RestController
@RequestMapping("/api/v1/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> create(@RequestBody Map<String, Object> body) {
        String from = String.valueOf(body.get("fromAccount"));
        String to = String.valueOf(body.get("toAccount"));
        BigDecimal amount = new BigDecimal(String.valueOf(body.get("amount")));
        String ref = transferService.transfer(from, to, amount);
        return ResponseEntity.ok(Map.of("transferRef", ref, "status", "ACCEPTED"));
    }
}
