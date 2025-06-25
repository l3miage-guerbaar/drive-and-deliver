
package carrefourkata.driveanddeliver.model;

import javax.persistence.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

public class DriveSlot {

    @Id @GeneratedValue
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int maxCapacity;
    
    private int bookedCount;

    // + getters/setters
}