package com.parkgrid.booking.service;
import com.parkgrid.booking.client.*; import com.parkgrid.booking.dto.*; import com.parkgrid.booking.entity.Booking; import com.parkgrid.booking.repo.BookingRepository; import org.springframework.stereotype.Service; import java.time.LocalDateTime; import java.util.*;
@Service public class BookingManager {
 private final BookingRepository repo; private final ParkingClient parking; private final BillingClient billing;
 public BookingManager(BookingRepository repo,ParkingClient parking,BillingClient billing){this.repo=repo;this.parking=parking;this.billing=billing;}
 public Booking create(BookingRequest r,String username,String token){
   if(r.endTime().isBefore(r.startTime())||r.endTime().isEqual(r.startTime())) throw new IllegalArgumentException("End time must be after start time");
   parking.reserve(r.slotId(),token);
   try { BillingResponse bill=billing.calculate(System.currentTimeMillis(),r.startTime(),r.endTime(),token); Booking b=repo.save(new Booking(username,r.slotId(),r.startTime(),r.endTime(),bill.amount())); return b; }
   catch(RuntimeException ex){parking.release(r.slotId(),token); throw ex;}
 }
 public List<Booking> mine(String username){return repo.findByUsernameOrderByStartTimeDesc(username);}
 public List<Booking> all(){return repo.findAll();}
 public Booking cancel(Long id,String username,String token,boolean admin){Booking b=repo.findById(id).orElseThrow(()->new NoSuchElementException("Booking not found")); if(!admin&&!b.getUsername().equals(username)) throw new SecurityException("Not allowed"); if("CANCELLED".equals(b.getStatus())) return b; parking.release(b.getSlotId(),token); b.setStatus("CANCELLED"); return repo.save(b);}
}
