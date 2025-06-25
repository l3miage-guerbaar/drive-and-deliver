package carrefourkata.driveanddeliver.model;

import jakarta
import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_slots")
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