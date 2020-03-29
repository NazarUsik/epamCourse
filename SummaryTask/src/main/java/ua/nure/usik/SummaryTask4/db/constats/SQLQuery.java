package ua.nure.usik.SummaryTask4.db.constats;

public final class SQLQuery {

    public static final String SQL_INSERT_STATION =
            "INSERT INTO Station VALUES (DEFAULT, ?);";

    public static final String SQL_UPDATE_STATION =
            "UPDATE station SET name = ? WHERE id = ?";

    public static final String SQL_DELETE_STATION =
            "DELETE FROM station WHERE name = ?";

    public static final String SQL_INSERT_ROUTE =
            "INSERT INTO train_route VALUES (DEFAULT, ?, ?, ?, ?);";

    public static final String SQL_DELETE_ROUTE =
            "DELETE FROM train_route WHERE id = ?";

    public static final String SQL_UPDATE_ROUTE_BY_DEP_STATION =
            "UPDATE train_route SET departure_station_id = ? WHERE id = ?";

    public static final String SQL_UPDATE_ROUTE_BY_ARRIVAL_STATION =
            "UPDATE train_route SET arrival_station_id = ? WHERE id = ?";

    public static final String SQL_UPDATE_ROUTE_BY_SCHEDULE =
            "UPDATE train_route SET schedule_id = ? WHERE id = ?";

    public static final String SQL_INSERT_SCHEDULE =
            "INSERT INTO schedule VALUES (DEFAULT, ?, ?, ?);";

    public static final String SQL_UPDATE_SCHEDULE_BY_ARR_TIME =
            "UPDATE schedule SET arrival_time = ? WHERE id = ?";

    public static final String SQL_UPDATE_SCHEDULE_BY_DEP_TIME =
            "UPDATE schedule SET departure_time = ? WHERE  id = ?";

    public static final String SQL_DELETE_SCHEDULE =
            "DELETE FROM schedule WHERE id = ?";

    public static final String SQL_INSERT_USER =
            "INSERT INTO user VALUES (DEFAULT, ?, ?, ?, ?, ?);";

    public static final String SQL_UPDATE_USER =
            "UPDATE user SET password = ? WHERE id = ?";

    public static final String SQL_FIND_USER_BY_EMAIL_AND_PASS =
            "SELECT * FROM  final_project_db.user us WHERE us.email = ? AND us.password = ?;";

    public static final String SQL_FIND_USER_BY_EMAIL =
            "SELECT * FROM  final_project_db.user us WHERE us.email = ?;";

    public static final String SQL_INSERT_TRAIN_COMPOSITION =
            "INSERT INTO train_composition VALUES (?, ?, ?)";

    public static final String SQL_UPDATE_TRAIN_COMPOSITION_BY_TRAIN =
            "UPDATE train_composition SET train_id = ? WHERE train_id = ?";

    public static final String SQL_UPDATE_TRAIN_COMPOSITION_BY_CARRIAGE =
            "UPDATE train_composition SET carriage_id = ? WHERE train_id = ?";

    public static final String SQL_DELETE_TRAIN_COMPOSITION_BY_TRAIN =
            "DELETE FROM train_composition WHERE train_id = ?";

    public static final String SQL_DELETE_TRAIN_COMPOSITION_BY_CARRIAGE =
            "DELETE FROM train_composition WHERE carriage_id = ?";

    public static final String SQL_INSERT_CARRIAGE =
            "INSERT INTO carriage VALUES (DEFAULT, ?, ?, ?, ?);";

    public static final String SQL_UPDATE_CARRIAGE_BY_COUNT_AVAILABLE_SEATS =
            "UPDATE carriage SET count_available_seats = ? WHERE id = ?";

    public static final String SQL_DELETE_CARRIAGE =
            "DELETE FROM carriage WHERE id = ?";

    public static final String SQL_INSERT_SEATS =
            "INSERT INTO seats VALUES (DEFAULT, ?, ?, ?);";

    public static final String SQL_UPDATE_SEATS =
            "UPDATE seats SET available = ? WHERE id = ?";

    public static final String SQL_DELETE_SEATS =
            "DELETE FROM seats WHERE id = ?";

    public static final String SQL_INSERT_TRAIN =
            "INSERT INTO train VALUES (DEFAULT, ?);";

    public static final String SQL_UPDATE_TRAIN =
            "UPDATE train SET type_id = ? WHERE id = ?";

    public static final String SQL_DELETE_TRAIN =
            "DELETE FROM train WHERE id = ?";

    public static final String SQL_INSERT_TICKET =
            "INSERT INTO ticket VALUES (DEFAULT, ?, ?, ?, ?);";

    public static final String SQL_UPDATE_TICKET =
            "UPDATE ticket SET price = ? WHERE id = ?";

    public static final String SQL_DELETE_TICKET =
            "DELETE FROM ticket WHERE id = ?";

    public static final String SQL_INSERT_INTERMEDIATE_STATION =
            "INSERT INTO intermediate_stations VALUES (?, ?, ?);";

    public static final String SQL_UPDATE_INTERMEDIATE_STATION =
            "UPDATE intermediate_stations SET schedule_id = ? WHERE route_id = ?";

    public static final String SQL_DELETE_INTERMEDIATE_STATION =
            "DELETE FROM intermediate_stations WHERE route_id = ? and station_id = ?";

    public static final String SQL_INSERT_TICKETS_LIST =
            "INSERT INTO ticketslist VALUES (?, ?, ?);";

    public static final String SQL_UPDATE_TICKETS_LIST =
            "UPDATE ticketslist SET ticket_status_id = ? WHERE user_id = ?";

    public static final String SQL_DELETE_TICKETS_LIST =
            "DELETE FROM ticketslist WHERE user_id = ? and ticket_id = ?";

    public static final String SQL_FIND_ROUTE_BY_STATION =
            "SELECT *\n" +
                    "FROM train_route tr\n" +
                    "         JOIN schedule sch on tr.schedule_id = sch.id\n" +
                    "         JOIN station dep on tr.departure_station_id = dep.id\n" +
                    "         JOIN station arr on tr.arrival_station_id = arr.id\n" +
                    "         JOIN ticket t on tr.id = t.rout_id\n" +
                    "WHERE dep.name = ? AND arr.name = ? AND sch.departure_time > DATE(?);";

    public static final String SQL_FIND_INTERMEDIATE_STATION =
            "SELECT *\n" +
                    "FROM intermediate_stations ins\n" +
                    "         JOIN station s2 on ins.station_id = s2.id\n" +
                    "         JOIN schedule s on ins.schedule_id = s.id\n" +
                    "where s2.name = ?\n" +
                    "  and s.departure_time > ?";

    public static final String SQL_FIND_ALL_TRAIN =
            "SELECT * FROM train t JOIN train_type tt on t.type_id = tt.id;";

    public static final String SQL_FIND_ALL_CARRIAGE =
            "SELECT tc.train_id,\n" +
                    "       c.id,\n" +
                    "       c.type_id,\n" +
                    "       ct.name,\n" +
                    "       c.count_seats,\n" +
                    "       c.count_available_seats,\n" +
                    "       c.haveRestaurant\n" +
                    "FROM carriage c\n" +
                    "         JOIN carriage_type ct on c.type_id = ct.id\n" +
                    "         JOIN train_composition tc on c.id = tc.carriage_id\n" +
                    "ORDER BY train_id;";

    public static final String SQL_LAST_ADD_CARRIAGE_ID =
            "SELECT id\n" +
                    "FROM carriage\n" +
                    "ORDER BY id DESC\n" +
                    "LIMIT 1;\n";

    public static final String SQL_LAST_ADD_TRAIN_ID =
            "SELECT id\n" +
                    "FROM train\n" +
                    "ORDER BY id DESC\n" +
                    "LIMIT 1;\n";

}
