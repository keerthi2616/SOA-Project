package com.parkgrid.parking.dto; import jakarta.validation.constraints.*; public record ZoneRequest(@NotBlank String name,@NotBlank String location,@Min(1) int capacity){}
