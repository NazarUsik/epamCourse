package ua.nure.usik.SummaryTask4.db.entity.enums;

import ua.nure.usik.SummaryTask4.db.entity.Train;

public class TrainTypeBean {
    public String getTrainType(Train train) {
        return TrainType.getTrainType(train).getName();
    }
}
