package com.parkgrid.parking.entity;
import jakarta.persistence.*;
@Entity @Table(name="parking_zones") public class ParkingZone {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String name; @Column(nullable=false) private String location; @Column(nullable=false) private int capacity;
 public ParkingZone(){} public ParkingZone(String name,String location,int capacity){this.name=name;this.location=location;this.capacity=capacity;}
 public Long getId(){return id;} public String getName(){return name;} public String getLocation(){return location;} public int getCapacity(){return capacity;}
 public void setName(String v){name=v;} public void setLocation(String v){location=v;} public void setCapacity(int v){capacity=v;}
}
