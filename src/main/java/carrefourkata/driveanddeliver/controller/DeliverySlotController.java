package carrefourkata.driveanddeliver.controller;

import carrefourkata.driveanddeliver.service.DeliverySlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/delivery-slots")
public class DeliverySlotController {
    
    @Autowired
    private DeliverySlotService deliverySlotService;

    public DeliverySlotController(DeliverySlotService deliverySlotService) {
        this.deliverySlotService = deliverySlotService;
    }

    // Endpoint pour générer les slots de livraison pour les deux prochaines semaines
    @PostMapping("/generate")
    public ResponseEntity<String> generateDeliverySlots() {
        deliverySlotService.generateSlotsForNextTwoWeeks();
        return ResponseEntity.ok("Delivery slots generated successfully.");
    }

    // Liste de tous les créneaux
    @GetMapping
    public List<DeliverySlot> getAllDeliverySlots() {
        return deliverySlotService.getAllSlots();
    }


}
