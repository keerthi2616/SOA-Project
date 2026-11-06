package com.parkgrid.booking.controller;
import com.parkgrid.booking.dto.*; import com.parkgrid.booking.entity.Booking; import com.parkgrid.booking.service.BookingManager; import jakarta.validation.Valid; import java.util.*; import org.springframework.http.*; import org.springframework.security.core.*; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/bookings") public class BookingController {
 private final BookingManager manager; public BookingController(BookingManager m){manager=m;}
 @PostMapping public ResponseEntity<?> create(@Valid @RequestBody BookingRequest request,Authentication auth,@RequestHeader(value="Authorization",required=false)String header){try{String token=header==null?"":header.replaceFirst("Bearer ",""); return ResponseEntity.status(HttpStatus.CREATED).body(manager.create(request,auth.getName(),token));}catch(IllegalStateException e){return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message",e.getMessage()));}catch(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));}}
 @GetMapping("/my") public List<Booking> mine(Authentication auth){return manager.mine(auth.getName());}
 @GetMapping("/all") public List<Booking> all(){return manager.all();}
 @PutMapping("/{id}/cancel") public Booking cancel(@PathVariable Long id,Authentication auth,@RequestHeader("Authorization")String header){String token=header.replaceFirst("Bearer ","");boolean admin=auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_ADMIN"));return manager.cancel(id,auth.getName(),token,admin);}
}
