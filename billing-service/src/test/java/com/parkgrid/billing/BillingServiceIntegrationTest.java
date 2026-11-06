package com.parkgrid.billing;

import com.parkgrid.billing.dto.BillingRequest;
import com.parkgrid.billing.dto.BillingResponse;
import com.parkgrid.billing.service.BillingCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "eureka.client.enabled=false")
class BillingServiceIntegrationTest {
    @Autowired BillingCalculator calculator;

    @Test
    void contextLoadsAndCalculatesBillingUsingRealH2Repository() {
        LocalDateTime start = LocalDateTime.of(2026, 9, 20, 10, 0);
        BillingResponse response = calculator.calculate(new BillingRequest(start, start.plusMinutes(90), null), "integration-user");
        assertEquals(2, response.billableHours());
        assertEquals(80.0, response.amount());
    }
}
