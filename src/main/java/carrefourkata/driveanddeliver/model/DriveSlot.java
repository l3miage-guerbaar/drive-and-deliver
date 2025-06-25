package carrefourkata.driveanddeliver.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "drive_slots")
public class DriveSlot {

    @Id @GeneratedValue
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int maxCapacity;
    
    private int bookedCount;

    // + getters/setters
}