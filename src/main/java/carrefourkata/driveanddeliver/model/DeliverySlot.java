package carrefourkata.driveanddeliver.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DeliverySlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private DeliveryMode mode;

    private int maxCapacity;
    
    private int bookedCount;

    // Getters & Setters
}