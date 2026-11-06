package com.parkgrid.booking.client;
import com.parkgrid.booking.dto.BillingResponse; import java.time.LocalDateTime; import java.util.Map; import org.springframework.stereotype.Component; import org.springframework.web.reactive.function.client.WebClient;
@Component public class BillingClient {
 private final WebClient.Builder builder; public BillingClient(WebClient.Builder builder){this.builder=builder;}
 public BillingResponse calculate(Long bookingId,LocalDateTime start,LocalDateTime end,String token){Map<String,Object> body=Map.of("bookingId",String.valueOf(bookingId),"startTime",start,"endTime",end); return builder.build().post().uri("http://BILLING-SERVICE/api/billing/calculate").headers(h->h.setBearerAuth(token)).bodyValue(body).retrieve().bodyToMono(BillingResponse.class).block();}
}
