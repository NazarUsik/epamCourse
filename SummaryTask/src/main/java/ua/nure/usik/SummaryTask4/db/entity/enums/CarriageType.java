package ua.nure.usik.SummaryTask4.db.entity.enums;

import ua.nure.usik.SummaryTask4.db.entity.Carriage;

public enum CarriageType {
    COUPE, LUX, PLATSKART, SEAT_PLACE;

    public static CarriageType getCarriageType(Carriage carriage) {
        int carriageId = carriage.getTypeId();
        return CarriageType.values()[carriageId - 1];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
