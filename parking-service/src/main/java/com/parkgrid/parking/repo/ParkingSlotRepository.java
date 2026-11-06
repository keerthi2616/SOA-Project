package com.parkgrid.parking.repo;
import com.parkgrid.parking.entity.ParkingSlot; import org.springframework.data.jpa.repository.*; import org.springframework.data.repository.query.Param; import java.util.*;
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot,Long>{
 List<ParkingSlot> findByZoneId(Long zoneId); List<ParkingSlot> findByAvailableTrue(); long countByZoneId(Long zoneId); long countByZoneIdAndAvailableTrue(Long zoneId);
 @Modifying @Query("update ParkingSlot s set s.available=false where s.id=:id and s.available=true") int reserveIfAvailable(@Param("id") Long id);
 @Modifying @Query("update ParkingSlot s set s.available=true where s.id=:id") int release(@Param("id") Long id);
}
