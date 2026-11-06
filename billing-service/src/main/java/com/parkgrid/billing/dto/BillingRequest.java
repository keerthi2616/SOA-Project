package com.parkgrid.billing.dto;
import jakarta.validation.constraints.NotNull; import java.time.LocalDateTime;
public record BillingRequest(@NotNull LocalDateTime startTime,@NotNull LocalDateTime endTime,String bookingId){ }
