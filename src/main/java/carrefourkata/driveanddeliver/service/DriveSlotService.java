package carrefourkata.driveanddeliver.service;

import carrefourkata.driveanddeliver.model.DriveSlot;
import carrefourkata.driveanddeliver.repository.DriveSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

@Service
public class DriveSlotService {

    @Autowired
    private DriveSlotRepository driveSlotRepository;

    public DriveSlotService(DriveSlotRepository driveSlotRepository) {
        this.driveSlotRepository = driveSlotRepository;
    }

    // génère les slots de drive pour les deux prochaines semaines
    // Le dimanche est exclu
    public void generateDriveSlotsForNextTwoWeeks() {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusWeeks(2);

        // Parcourt du jour d'aujourd'hui jusqu'à dans deux semaines
        for (LocalDate date = today; date.isBefore(endDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                List<DriveSlot> slots = generateDriveSlotsForDay(date);
                for (DriveSlot slot : slots) {
                    if (!driveSlotRepository.existsByStartTime(slot.getStartTime())) {
                        driveSlotRepository.save(slot);
                    }
                }
            }
        }
    }

    // Génère les slots de drive pour une journée donnée (les horaires sont de 9h à 13h et de 14h à 18h)
    private List<DriveSlot> generateDriveSlotsForDay(LocalDate date) {
        List<DriveSlot> slots = new ArrayList<>();
        
        slots.addAll(generateDriveSlotsForPeriod(date, LocalTime.of(9, 0), LocalTime.of(13, 0)));
        slots.addAll(generateDriveSlotsForPeriod(date, LocalTime.of(14, 0), LocalTime.of(18, 0)));
        
        return slots;
    }

    // Les slots sont générés pour les horaires de drive
    // Les slots DRIVE sont de 15 minutes avec 3 réservations maximum
    private List<DriveSlot> generateDriveSlotsForPeriod(LocalDate date, LocalTime start, LocalTime end) {
        List<DriveSlot> result = new ArrayList<>();
        Duration interval = Duration.ofMinutes(15);
        int capacity = 3;

        LocalDateTime slotStart = LocalDateTime.of(date, start);
        LocalDateTime slotEnd = LocalDateTime.of(date, end);

        while (!slotStart.isAfter(slotEnd.minus(interval))) {
            DriveSlot slot = new DriveSlot();
            slot.setStartTime(slotStart);
            slot.setEndTime(slotStart.plus(interval));
            slot.setMaxCapacity(capacity);
            slot.setBookedCount(0);
            result.add(slot);

            slotStart = slotStart.plus(interval);
        }

        return result;
    }
}