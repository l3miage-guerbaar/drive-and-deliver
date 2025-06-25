package carrefourkata.driveanddeliver.controller;

import carrefourkata.driveanddeliver.model.DriveSlot;
import carrefourkata.driveanddeliver.service.DriveSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/drive-slots")
public class DriveSlotController {

    @Autowired
    private final DriveSlotService driveSlotService;


    public DriveSlotController(DriveSlotService driveSlotService) {
        this.driveSlotService = driveSlotService;
    }

    // Endpoint pour générer les slots de drive pour les deux prochaines semaines
    @PostMapping("/generate")
    public ResponseEntity<String> generateDriveSlots() {
        driveSlotService.generateDriveSlotsForNextTwoWeeks();
        return ResponseEntity.ok("Drive slots generated successfully.");
    }

    // Liste de tous les créneaux de drive
    @GetMapping
    public List<DriveSlot> getAllDriveSlots() {
        return driveSlotService.getAllSlots();
    }
    
}
