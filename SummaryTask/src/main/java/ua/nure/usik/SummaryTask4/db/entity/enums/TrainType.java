package ua.nure.usik.SummaryTask4.db.entity.enums;

import ua.nure.usik.SummaryTask4.db.entity.Train;

public enum TrainType {
    ELECTRIC, STREAM, DIESEL;

    public static TrainType getTrainType(Train train) {
        int typeId = train.getTypeId();
        return TrainType.values()[typeId - 1];
    }

    public static int getTrainTypeId(String nameType) {
        TrainType[] types = TrainType.values();
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
