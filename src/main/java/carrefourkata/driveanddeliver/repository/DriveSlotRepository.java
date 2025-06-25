package carrefourkata.driveanddeliver.repository;

import carrefourkata.driveanddeliver.model.DriveSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

public interface DriveSlotRepository extends JpaRepository<DriveSlot, Long> {

    boolean existsByStartTime(LocalDateTime startTime);

}