package com.parkgrid.parking.service;

import com.parkgrid.parking.entity.*; import com.parkgrid.parking.repo.*; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.*;
@Service public class ParkingManager {
 private final ParkingZoneRepository zones; private final ParkingSlotRepository slots;
 public ParkingManager(ParkingZoneRepository zones,ParkingSlotRepository slots){this.zones=zones;this.slots=slots;}
 @Transactional public ParkingZone createZone(String name,String location,int capacity){ParkingZone z=zones.save(new ParkingZone(name,location,capacity)); for(int i=1;i<=capacity;i++) slots.save(new ParkingSlot(z.getId(),"Z"+z.getId()+"-S"+i)); return z;}
 @Transactional public ParkingSlot addSlot(Long zoneId,String slotNumber){if(!zones.existsById(zoneId)) throw new IllegalArgumentException("Zone not found"); return slots.save(new ParkingSlot(zoneId,slotNumber));}
 @Transactional public boolean reserve(Long slotId){return slots.reserveIfAvailable(slotId)==1;}
 @Transactional public boolean release(Long slotId){return slots.release(slotId)==1;}
 public List<ParkingSlot> availability(){return slots.findByAvailableTrue();}
 public List<ParkingZone> zones(){return zones.findAll();}
 public Map<String,Object> occupancy(){long total=slots.count(); long available=slots.findByAvailableTrue().size(); return Map.of("totalSlots",total,"availableSlots",available,"occupiedSlots",total-available,"occupancyPercent",total==0?0:Math.round((total-available)*10000.0/total)/100.0);}
 public ParkingSlot getSlot(Long id){return slots.findById(id).orElseThrow(()->new NoSuchElementException("Slot not found"));}
}
