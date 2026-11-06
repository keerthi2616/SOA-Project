package com.parkgrid.billing.service;
import com.parkgrid.billing.dto.*; import com.parkgrid.billing.entity.BillingTransaction; import com.parkgrid.billing.repo.BillingTransactionRepository; import org.springframework.stereotype.Service; import java.time.Duration; import java.time.LocalDateTime; import java.util.*;
@Service public class BillingCalculator {
 private static final double RATE=40.0;
 private final BillingTransactionRepository repo; public BillingCalculator(BillingTransactionRepository repo){this.repo=repo;}
 public BillingResponse calculate(BillingRequest r,String username){if(r.endTime().isBefore(r.startTime())||r.endTime().isEqual(r.startTime())) throw new IllegalArgumentException("End time must be after start time"); long minutes=Duration.between(r.startTime(),r.endTime()).toMinutes(); long hours=Math.max(1,(long)Math.ceil(minutes/60.0)); double amount=hours*RATE; if(r.bookingId()!=null) repo.save(new BillingTransaction(r.bookingId(),username,amount,minutes)); return new BillingResponse(r.bookingId(),minutes,hours,RATE,amount,"INR");}
 public Map<String,Object> revenue(){double total=repo.findAll().stream().mapToDouble(BillingTransaction::getAmount).sum(); return Map.of("transactions",repo.count(),"totalRevenue",total,"currency","INR");}
}
