package carrefourkata.driveanddeliver.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Booking {
    @Id @GeneratedValue
    private Long id;
    private String customerId;
    private LocalDateTime bookingTime;
    // manque la relations avec DriveSlot DeliverySlot
}