package carrefourkata.driveanddeliver;

import carrefourkata.driveanddeliver.service.DeliverySlotService;
import carrefourkata.driveanddeliver.service.DriveSlotService;  
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
public class SlotInitializationService {

    private final DriveSlotService driveSlotService;
    private final DeliverySlotService deliverySlotService;

    public SlotInitializationService(DriveSlotService driveSlotService, DeliverySlotService deliverySlotService) {
        this.driveSlotService = driveSlotService;
        this.deliverySlotService = deliverySlotService;
    }

    @PostConstruct
    public void initSlots() {
        deliverySlotService.generateDeliverySlotsForNextTwoWeeks();
        driveSlotService.generateDriveSlotsForNextTwoWeeks();
    }
}