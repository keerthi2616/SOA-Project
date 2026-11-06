package com.parkgrid.booking.dto;
public record BillingResponse(String bookingId,long durationMinutes,long billableHours,double ratePerHour,double amount,String currency){}
