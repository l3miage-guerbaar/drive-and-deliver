package carrefourkata.driveanddeliver.model;

impoert jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "delivery_slots")
public class DeliverySlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int maxCapacity;
    
    private int bookedCount;

    @OneToMany(mappedBy = "deliverySlot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Booking> bookings;

    public DeliverySlot() {
    }

    depublic DeliverySlot(LocalDateTime startTime, LocalDateTime endTime, int maxCapacity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxCapacity = maxCapacity;
        this.bookedCount = 0;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getBookedCount() {
        return bookedCount;
    }
    public void setBookedCount(int bookedCount) {
        this.bookedCount = bookedCount;
    }   

    public boolean isAvailable() {
        return bookedCount < maxCapacity;
    }

    public void incrementBookedCount() {
        this.bookedCount++;
    }
}