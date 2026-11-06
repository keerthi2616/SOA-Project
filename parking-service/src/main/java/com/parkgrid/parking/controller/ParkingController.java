package com.parkgrid.parking.controller;
import com.parkgrid.parking.dto.*; import com.parkgrid.parking.entity.*; import com.parkgrid.parking.service.ParkingManager; import jakarta.validation.Valid; import java.util.*; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/parking") public class ParkingController {
 private final ParkingManager manager; public ParkingController(ParkingManager m){manager=m;}
 @GetMapping("/zones") public List<ParkingZone> zones(){return manager.zones();}
 @GetMapping("/availability") public List<ParkingSlot> availability(){return manager.availability();}
 @GetMapping("/slots/{id}") public ParkingSlot slot(@PathVariable Long id){return manager.getSlot(id);}
 @PostMapping("/zones") public ResponseEntity<ParkingZone> createZone(@Valid @RequestBody ZoneRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(manager.createZone(r.name(),r.location(),r.capacity()));}
 @PostMapping("/slots") public ResponseEntity<ParkingSlot> addSlot(@Valid @RequestBody SlotRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(manager.addSlot(r.zoneId(),r.slotNumber()));}
 @GetMapping("/occupancy") public Map<String,Object> occupancy(){return manager.occupancy();}
 @PutMapping("/slots/{id}/reserve") public ResponseEntity<?> reserve(@PathVariable Long id){if(manager.reserve(id)) return ResponseEntity.ok(Map.of("slotId",id,"reserved",true)); return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("slotId",id,"reserved",false,"message","Slot is already reserved"));}
 @PutMapping("/slots/{id}/release") public ResponseEntity<?> release(@PathVariable Long id){if(manager.release(id)) return ResponseEntity.ok(Map.of("slotId",id,"released",true)); return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Slot not found"));}
}
