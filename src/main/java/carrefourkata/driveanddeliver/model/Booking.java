package carrefourkata.driveanddeliver.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Booking {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryMode deliveryMode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drive_slot_id")
    private DriveSlot driveSlot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_slot_id")
    private DeliverySlot deliverySlot;

    public Booking() {
    }

    public Booking(String customerId, LocalDateTime bookingTime, DeliveryMode deliveryMode, DeliverySlot deliverySlot) {
        this.customerId = customerId;
        this.bookingTime = bookingTime;
        this.deliveryMode = deliveryMode;
        this.deliverySlot = deliverySlot;
    }

    public Booking(String customerId, LocalDateTime bookingTime, DeliveryMode deliveryMode, DriveSlot driveSlot) {
        this.customerId = customerId;
        this.bookingTime = bookingTime;
        this.deliveryMode = deliveryMode;
        this.driveSlot = driveSlot;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public DeliveryMode getDeliveryMode() {
        return deliveryMode;
    }
    public void setDeliveryMode(DeliveryMode deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public DriveSlot getDriveSlot() {
        return driveSlot;
    }
    public void setDriveSlot(DriveSlot driveSlot) {
        this.driveSlot = driveSlot;
    }

    public DeliverySlot getDeliverySlot() {
        return deliverySlot;
    }
    public void setDeliverySlot(DeliverySlot deliverySlot) {
        this.deliverySlot = deliverySlot;
    }

    public LocalDateTime getStartTime() {
        return driveSlot != null? driveSlot.getStartTime() : deliverySlot.getStartTime();
    }

    public LocalDateTime getEndTime() {
        return driveSlot != null? driveSlot.getEndTime() : deliverySlot.getEndTime();
    }

}