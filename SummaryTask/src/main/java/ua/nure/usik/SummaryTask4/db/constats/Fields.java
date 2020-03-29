package ua.nure.usik.SummaryTask4.db.constats;

/**
 * Holder for fields names of DB tables and beans.
 *
 * @author D.Kolesnikov
 */
public final class Fields {

    public static final String ID = "id";
    public static final String NAME = "name";


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

    public static final String IS_ROUTE_ID = "route_id";
    public static final String IS_SCHEDULE_ID = "schedule_id";
    public static final String IS_STATION_ID = "station_id";

    public static final String ROUTE = "Route";
    public static final String ROUTE_TRAIN_ID = "train_id";
    public static final String ROUTE_DEP_S_ID = "departure_station_id";
    public static final String ROUTE_ARR_S_ID = "arrival_station_id";
    public static final String ROUTE_SCHEDULE_ID = "schedule_id";

    public static final String TRAIN = "Train";
    public static final String TRAIN_TYPE_ID = "type_id";

    public static final String CARRIAGE = "Train";
    public static final String CARRIAGE_TYPE_ID = "type_id";
    public static final String CARRIAGE_COUNT_SEATS = "count_seats";
    public static final String CARRIAGE_COUNT_AVAILABLE_SEATS = "count_available_seats";
    public static final String CARRIAGE_HAVE_REST = "haveRestaurant";
}