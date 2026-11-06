package com.parkgrid.parking.entity;
import jakarta.persistence.*;
@Entity @Table(name="parking_slots", indexes=@Index(name="idx_slot_zone", columnList="zoneId")) public class ParkingSlot {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private Long zoneId; @Column(nullable=false) private String slotNumber; @Column(nullable=false) private boolean available=true;
 public ParkingSlot(){} public ParkingSlot(Long zoneId,String slotNumber){this.zoneId=zoneId;this.slotNumber=slotNumber;}
 public Long getId(){return id;} public Long getZoneId(){return zoneId;} public String getSlotNumber(){return slotNumber;} public boolean isAvailable(){return available;}
 public void setZoneId(Long v){zoneId=v;} public void setSlotNumber(String v){slotNumber=v;} public void setAvailable(boolean v){available=v;}
}
