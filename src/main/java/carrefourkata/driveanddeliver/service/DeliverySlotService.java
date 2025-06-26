package carrefourkata.driveanddeliver.service;

import carrefourkata.driveanddeliver.model.DeliveryMode;
import carrefourkata.driveanddeliver.model.DeliverySlot;
import carrefourkata.driveanddeliver.repository.DeliverySlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliverySlotService {

    @Autowired
    private DeliverySlotRepository deliverySlotRepository;

    public DeliverySlotService(DeliverySlotRepository deliverySlotRepository) {
        this.deliverySlotRepository = deliverySlotRepository;
    }

    // génère les slots de livraison pour les deux prochaines semaines (Le dimanche est exclu)
    public void generateSlotsForNextTwoWeeks() {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusWeeks(2);

        for (LocalDate date = today; date.isBefore(endDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                List<DeliverySlot> slots = generateSlotsForDay(date);
            
                saveSlots(slots);
            }
        }
    }

    private saveSlots(List<DeliverySlot> slots){
        for(DeliveryMode slot : slots){
            if(!deliverySlotRepository.existsByStartTime(slot.getStartTime())) {
                deliverySlotRepository.save(slot);
            }
        }
    }

    // retourne les slots de livraison pour une journée donnée (les horaires sont de 9h à 13h et de 14h à 18h)
    private List<DeliverySlot> generateSlotsForDayAndMode(LocalDate date) {
        List<DeliverySlot> slots = new ArrayList<>();

        slots.addAll(generateSlotsForPeriod(date, LocalTime.of(9, 0), LocalTime.of(13, 0)));
        slots.addAll(generateSlotsForPeriod(date, LocalTime.of(14, 0), LocalTime.of(18, 0)));

        return slots;
    }

    // les slots DELIVERY / TODAY / ASAP sont de 1 heure avec 4 réservations maximum
    private List<DeliverySlot> generateSlotsForPeriod(LocalDate date, LocalTime start, LocalTime end) {
        List<DeliverySlot> result = new ArrayList<>();
        Duration interval = Duration.ofHours(1);
        int capacity = 4;

        LocalDateTime slotStart = LocalDateTime.of(date, start);
        LocalDateTime slotEnd = LocalDateTime.of(date, end);

        while (!slotStart.isAfter(slotEnd.minus(interval))) {
            DeliverySlot slot = new DeliverySlot(slotStart, slotStart.plus(interval), capacity);
            result.add(slot);
            slotStart = slotStart.plus(interval);
        }

        return result;
    }

    public List<DeliverySlot> getAllSlots() {
        return deliverySlotRepository.findAll();
    }

    public List<DeliverySlot> getAvailableSlots(LocalDateTime minTime){
        return deliverySlotRepository.findAvailableSlots(minTime);
    }

    // manque pour le delivery today
    // public List<DeliverySlot> get 

}