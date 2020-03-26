package ua.nure.usik.SummaryTask4.db.entity.enums;

import ua.nure.usik.SummaryTask4.db.entity.Ticket;

public enum Status {
    NOT_ACTIVE, PAID;

    public Status getStatus(Ticket ticket) {
        int statusId = ticket.getCarriageId();
        return Status.values()[statusId - 1];
    }

    public String getName() {
        return name().toLowerCase();
    }

}
