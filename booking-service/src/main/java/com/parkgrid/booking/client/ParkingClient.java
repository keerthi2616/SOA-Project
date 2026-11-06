package com.parkgrid.booking.client;
import org.springframework.stereotype.Component; import org.springframework.web.reactive.function.client.WebClient; import org.springframework.http.HttpStatusCode; import org.springframework.web.reactive.function.client.WebClientResponseException;
@Component public class ParkingClient {
 private final WebClient.Builder builder; public ParkingClient(WebClient.Builder builder){this.builder=builder;}
 public void reserve(Long slotId,String token){builder.build().put().uri("http://PARKING-SERVICE/api/parking/slots/{id}/reserve",slotId).headers(h->h.setBearerAuth(token)).retrieve().onStatus(HttpStatusCode::isError,r->r.createException().flatMap(e->reactor.core.publisher.Mono.error(new IllegalStateException("Parking reserve failed: "+e.getStatusCode())))).toBodilessEntity().block();}
 public void release(Long slotId,String token){builder.build().put().uri("http://PARKING-SERVICE/api/parking/slots/{id}/release",slotId).headers(h->h.setBearerAuth(token)).retrieve().toBodilessEntity().block();}
}
