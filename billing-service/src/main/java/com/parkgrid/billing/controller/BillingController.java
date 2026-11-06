package com.parkgrid.billing.controller;
import com.parkgrid.billing.dto.*; import com.parkgrid.billing.service.BillingCalculator; import jakarta.validation.Valid; import java.util.Map; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/billing") public class BillingController {
 private final BillingCalculator calc; public BillingController(BillingCalculator c){calc=c;}
 @PostMapping("/calculate") public BillingResponse calculate(@Valid @RequestBody BillingRequest request,Authentication auth){return calc.calculate(request,auth.getName());}
 @GetMapping("/revenue") public Map<String,Object> revenue(){return calc.revenue();}
}
