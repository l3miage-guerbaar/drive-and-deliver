package carrefourkata.driveanddeliver.repository;

import carrefourkata.driveanddeliver.model.DeliveryMode;
import carrefourkata.driveanddeliver.model.DeliverySlot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;


public interface DeliverySlotRepository extends JpaRepository<DeliverySlot, Long> {

    // Vérifie si un slot de livraison existe déjà pour une heure de début donnée
    boolean existsByStartTime(LocalDateTime startTime);

    // Récupère les slots de livraison disponibles à partir d'une heure minimale
    @Query("SELECT ds FROM DriveSlot ds WHERE ds.startTime >= :minTime AND ds.bookedCount < ds.maxCapacity ORDER BY ds.startTime")
    List<DeliverySlot> findAvailableSlots(LocalDateTime minTime);

    // Récupère les slots de livraison pour une période donnée
    @Query("SELECT ds FROM DeliverySlot ds WHERE ds.startTime BETWEEN :startTime AND :endTime ORDER BY ds.startTime")
    public List<DeliverySlot> findByStartTimeBetween(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

}