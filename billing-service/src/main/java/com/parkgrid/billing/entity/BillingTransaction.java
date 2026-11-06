package com.parkgrid.billing.entity;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="billing_transactions") public class BillingTransaction {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; private String bookingId; private String username; private double amount; private long durationMinutes; private LocalDateTime createdAt;
 public BillingTransaction(){} public BillingTransaction(String bookingId,String username,double amount,long durationMinutes){this.bookingId=bookingId;this.username=username;this.amount=amount;this.durationMinutes=durationMinutes;this.createdAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getBookingId(){return bookingId;} public String getUsername(){return username;} public double getAmount(){return amount;} public long getDurationMinutes(){return durationMinutes;} public LocalDateTime getCreatedAt(){return createdAt;}
}
