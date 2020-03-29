package ua.nure.usik.SummaryTask4.db.entity.enums;

import ua.nure.usik.SummaryTask4.db.entity.Carriage;

public enum CarriageType {
    COUPE, LUX, PLATSKART, SEAT_PLACE;

    public static CarriageType getCarriageType(Carriage carriage) {
        int carriageId = carriage.getTypeId();
        return CarriageType.values()[carriageId - 1];
    }

    public static int getCarriageTypeId(String nameType) {
        CarriageType[] types = CarriageType.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].getName().equals(nameType)) {
                return i + 1;
            }
        }
        return 0;
    }

    public String getName() {
        return name().toUpperCase();
    }
}
