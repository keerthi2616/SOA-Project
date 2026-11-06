package com.parkgrid.parking.dto; import jakarta.validation.constraints.*; public record SlotRequest(@NotNull Long zoneId,@NotBlank String slotNumber){}
