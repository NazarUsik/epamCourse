package ua.nure.usik.SummaryTask4.db.entity.enums;

import ua.nure.usik.SummaryTask4.db.entity.Train;

public enum TrainType {
    STREAM, ELECTRIC, DIESEL;

    public static TrainType getTrainType(Train train) {
        int typeId = train.getTypeId();
        return TrainType.values()[typeId - 1];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
