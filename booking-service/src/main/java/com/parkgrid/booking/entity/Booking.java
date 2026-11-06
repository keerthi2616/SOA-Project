package com.parkgrid.booking.entity;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="bookings") public class Booking {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(nullable=false) private String username; @Column(nullable=false) private Long slotId; @Column(nullable=false) private LocalDateTime startTime; @Column(nullable=false) private LocalDateTime endTime; @Column(nullable=false) private double amount; @Column(nullable=false) private String status;
 public Booking(){} public Booking(String username,Long slotId,LocalDateTime start,LocalDateTime end,double amount){this.username=username;this.slotId=slotId;this.startTime=start;this.endTime=end;this.amount=amount;this.status="CONFIRMED";}
 public Long getId(){return id;} public String getUsername(){return username;} public Long getSlotId(){return slotId;} public LocalDateTime getStartTime(){return startTime;} public LocalDateTime getEndTime(){return endTime;} public double getAmount(){return amount;} public String getStatus(){return status;} public void setStatus(String s){status=s;}
}
