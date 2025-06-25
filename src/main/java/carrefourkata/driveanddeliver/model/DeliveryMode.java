package carrefourkata.driveanddeliver.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum DeliveryMode {
    DRIVE,
    DELIVERY,
    DELIVERY_TODAY,
    DELIVERY_ASAP
}
