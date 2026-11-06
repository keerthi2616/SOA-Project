package com.parkgrid.booking.dto;
import jakarta.validation.constraints.NotNull; import java.time.LocalDateTime;
public record BookingRequest(@NotNull Long slotId,@NotNull LocalDateTime startTime,@NotNull LocalDateTime endTime){}
