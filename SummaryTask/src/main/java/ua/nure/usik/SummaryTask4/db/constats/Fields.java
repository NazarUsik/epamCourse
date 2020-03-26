package ua.nure.usik.SummaryTask4.db.constats;

/**
 * Holder for fields names of DB tables and beans.
 *
 * @author D.Kolesnikov
 */
public final class Fields {

    public static final String ID = "id";

    // entities
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_ROLE_ID = "role_id";


    public static final String SCHEDULE = "Schedule";
    public static final String SCHEDULE_DEPARTURE_TIME = "departure_time";
    public static final String SCHEDULE_ARRIVAL_TIME = "arrival_time";
    public static final String SCHEDULE_TRAVEL_TIME = "travel_time";

    public static final String STATION = "Station";
    public static final String STATION_NAME = "name";

    public static final String IS_ROUTE_ID = "route_id";
    public static final String IS_SCHEDULE_ID = "schedule_id";
    public static final String IS_STATION_ID = "station_id";

    public static final String ROUTE = "Route";
    public static final String ROUTE_TRAIN_ID = "train_id";
    public static final String ROUTE_DEP_S_ID = "departure_station_id";
    public static final String ROUTE_ARR_S_ID = "arrival_station_id";
    public static final String ROUTE_SCHEDULE_ID = "schedule_id";
}