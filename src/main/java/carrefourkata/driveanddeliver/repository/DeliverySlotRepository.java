package carrefourkata.driveanddeliver.repository;

import carrefourkata.driveanddeliver.model.DeliveryMode;
import carrefourkata.driveanddeliver.model.DeliverySlot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;


public interface DeliverySlotRepository extends JpaRepository<DeliverySlot, Long> {

    boolean existsByStartTimeAndMode(LocalDateTime startTime, DeliveryMode mode);

}