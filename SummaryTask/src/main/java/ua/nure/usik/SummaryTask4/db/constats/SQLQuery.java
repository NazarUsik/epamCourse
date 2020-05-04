package ua.nure.usik.SummaryTask4.db.constats;

public final class SQLQuery {

    public static final String SQL_INSERT_STATION =
            "INSERT INTO Station VALUES (DEFAULT, ?, ?);";

    public static final String SQL_FIND_STATION_BY_ID =
            "SELECT * FROM station WHERE id = ?";

    public static final String SQL_UPDATE_STATION =
            "UPDATE station SET name = ?, name_ru = ? WHERE id = ?";

    public static final String SQL_DELETE_STATION =
            "DELETE FROM station WHERE id = ?";

    public static final String SQL_INSERT_ROUTE =
            "INSERT INTO train_route VALUES (DEFAULT, ?, ?, ?, ?);";

    public static final String SQL_DELETE_ROUTE =
            "DELETE FROM train_route WHERE id = ?";

    public static final String SQL_UPDATE_ROUTE_BY_TRAIN_ID =
            "UPDATE train_route SET train_id = ? WHERE id = ?";

    public static final String SQL_UPDATE_ROUTE_BY_DEP_STATION =
            "UPDATE train_route SET departure_station_id = ? WHERE id = ?";

    public static final String SQL_UPDATE_ROUTE_BY_ARRIVAL_STATION =
            "UPDATE train_route SET arrival_station_id = ? WHERE id = ?";

    public static final String SQL_UPDATE_ROUTE_BY_SCHEDULE =
            "UPDATE train_route SET schedule_id = ? WHERE id = ?";

    public static final String SQL_FIND_SCHEDULE_BY_ID =
            "SELECT * FROM schedule WHERE id = ?";


    public static final String SQL_INSERT_SCHEDULE =
            "INSERT INTO schedule VALUES (DEFAULT, ?, ?, ?);";

    public static final String SQL_UPDATE_SCHEDULE =
            "UPDATE schedule SET departure_time = ?, arrival_time = ?, travel_time = ? WHERE id = ?";

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

    public static final String SQL_GET_LAST_CARRIAGE_NUMBER_BY_TRAIN =
            "select carriage_number " +
                    "from train_composition " +
                    "where train_id = ? " +
                    "ORDER BY carriage_number desc " +
                    "limit 1;";

    public static final String SQL_INSERT_TRAIN_COMPOSITION =
            "INSERT INTO train_composition VALUES (?, ?, ?)";

    public static final String SQL_UPDATE_TRAIN_COMPOSITION_BY_CARRIAGE =
            "UPDATE train_composition SET train_id = ? WHERE carriage_id = ?";

    public static final String SQL_UPDATE_TRAIN_COMPOSITION_BY_TRAIN =
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

    public static final String SQL_FIND_TRAIN =
            "SELECT * FROM train WHERE id = ?";

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

    public static final String SQL_UPDATE_INTERMEDIATE_STATION_BY_STATION =
            "UPDATE intermediate_stations SET station_id = ? WHERE route_id = ? and schedule_id = ?";

    public static final String SQL_UPDATE_INTERMEDIATE_STATION_BY_SCHEDULE =
            "UPDATE intermediate_stations SET schedule_id = ? WHERE route_id = ? and station_id = ?";

    public static final String SQL_DELETE_INTERMEDIATE_STATION =
            "DELETE FROM intermediate_stations WHERE route_id = ? and station_id = ?";

    public static final String SQL_INSERT_TICKETS_LIST =
            "INSERT INTO ticketslist VALUES (?, ?, ?);";

    public static final String SQL_UPDATE_TICKETS_LIST =
            "UPDATE ticketslist SET ticket_status_id = ? WHERE user_id = ?";

    public static final String SQL_DELETE_TICKETS_LIST =
            "DELETE FROM ticketslist WHERE user_id = ? and ticket_id = ?";

    public static final String SQL_FIND_ROUTE_BY_TWO_STATION =
            "SELECT * " +
                    "FROM train_route " +
                    "         JOIN schedule sch on train_route.schedule_id = sch.id " +
                    "         JOIN station dep on train_route.departure_station_id = dep.id " +
                    "         JOIN station arr on train_route.arrival_station_id = arr.id " +
                    "WHERE dep.name = ? AND arr.name = ? AND sch.departure_time > DATE(?);";

    public static final String SQL_FIND_ROUTE_BY_TWO_STATION_RU =
            "SELECT * " +
                    "FROM train_route " +
                    "         JOIN schedule sch on train_route.schedule_id = sch.id " +
                    "         JOIN station dep on train_route.departure_station_id = dep.id " +
                    "         JOIN station arr on train_route.arrival_station_id = arr.id " +
                    "WHERE dep.name_ru = ? AND arr.name_ru = ? AND sch.departure_time > DATE(?);";

    public static final String SQL_FIND_ROUTE_BY_ONE_STATION =
            "SELECT * " +
                    "FROM train_route " +
                    "         JOIN schedule sch on train_route.schedule_id = sch.id " +
                    "         JOIN station dep on train_route.departure_station_id = dep.id " +
                    "         JOIN station arr on train_route.arrival_station_id = arr.id " +
                    "WHERE (dep.name = ? OR arr.name = ?) AND sch.departure_time > DATE(?);";

    public static final String SQL_FIND_ROUTE_BY_ONE_STATION_RU =
            "SELECT * " +
                    "FROM train_route " +
                    "         JOIN schedule sch on train_route.schedule_id = sch.id " +
                    "         JOIN station dep on train_route.departure_station_id = dep.id " +
                    "         JOIN station arr on train_route.arrival_station_id = arr.id " +
                    "WHERE (dep.name_ru = ? OR arr.name_ru = ?) AND sch.departure_time > DATE(?);";

    public static final String SQL_FIND_INTERMEDIATE_STATION =
            "SELECT * " +
                    "FROM intermediate_stations ins " +
                    "         JOIN station s2 on ins.station_id = s2.id " +
                    "         JOIN schedule s on ins.schedule_id = s.id " +
                    "WHERE s2.name = ? " +
                    "  AND s.departure_time > ?";

    public static final String SQL_FIND_INTERMEDIATE_STATION_RU =
            "SELECT * " +
                    "FROM intermediate_stations ins " +
                    "         JOIN station s2 on ins.station_id = s2.id " +
                    "         JOIN schedule s on ins.schedule_id = s.id " +
                    "WHERE s2.name_ru = ? " +
                    "  AND s.departure_time > ?";

    public static final String SQL_FIND_ALL_TRAIN =
            "SELECT * FROM train t JOIN train_type tt on t.type_id = tt.id;";

    //language=MySQL
    public static final String SQL_FIND_ALL_CARRIAGE =
            "SELECT *" +
                    " FROM carriage c" +
                    "         JOIN carriage_type ct on c.type_id = ct.id" +
                    "         JOIN train_composition tc on c.id = tc.carriage_id" +
                    " ORDER BY train_id;";

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

    public static final String SQL_GET_ALL_STATION =
            "SELECT * FROM station ORDER BY station.name;";

    public static final String SQL_GET_ALL_ROUTE =
            "SELECT tr.id," +
                    "       tr.arrival_station_id," +
                    "       tr.departure_station_id," +
                    "       tr.schedule_id," +
                    "       tr.train_id," +
                    "       s.departure_time," +
                    "       s.travel_time," +
                    "       s.arrival_time," +
                    "       dep.name dep_station_name," +
                    "       arr.name arr_station_name, " +
                    "       dep.name_ru dep_station_name_ru," +
                    "       arr.name_ru arr_station_name_ru " +
                    "FROM train_route tr" +
                    "         JOIN schedule s ON tr.schedule_id = s.id " +
                    "         JOIN station dep ON tr.departure_station_id = dep.id " +
                    "         JOIN station arr ON tr.arrival_station_id = arr.id;";

    public static final String SQL_GET_ALL_INTERMEDIATE_STATION =
            "SELECT * " +
                    "FROM intermediate_stations" +
                    "         JOIN schedule sch on intermediate_stations.schedule_id = sch.id" +
                    "         JOIN station st on intermediate_stations.station_id = st.id;";

    public static final String SQL_FIND_STATION_BY_NAME =
            "SELECT * FROM station where name = ?;";

    public static final String SQL_FIND_STATION_BY_NAME_RU =
            "SELECT * FROM station where name_ru = ?;";

    public static final String SQL_GET_LAST_SCHEDULE_ID =
            "SELECT * FROM schedule ORDER BY id DESC LIMIT 1;";

    public static final String SQL_GET_LAST_ROUTE_ID =
            "SELECT * FROM train_route ORDER BY id DESC LIMIT 1;";

    public static final String SQL_SUM_ALL_SEATS_FROM_TRAIN =
            "SELECT SUM(c.count_seats) count_seats " +
                    "FROM train t " +
                    "         JOIN train_composition tc on t.id = tc.train_id " +
                    "         JOIN carriage c on tc.carriage_id = c.id " +
                    "WHERE t.id = ?;";


    public static final String SQL_FIND_CARRIAGES_SEATS_BY_TRAIN_ID =
            "SELECT * " +
                    "FROM train t " +
                    "         JOIN train_composition tc on t.id = tc.train_id " +
                    "         JOIN carriage c on tc.carriage_id = c.id " +
                    "         JOIN seats s on c.id = s.carriage_id " +
                    "WHERE t.id = ?;";

    public static final String SQL_FIND_ROUTE =
            "SELECT * FROM  train_route WHERE id = ?;";

    public static final String SQL_FIND_INTERMEDIATE_STATION_BY_ID =
            "SELECT * FROM  intermediate_stations WHERE route_id = ? and station_id = ?;";

    public static final String SQL_GET_ALL_AVAILABLE_TICKETS =
            "SELECT tr.id, t.price, COUNT(t.id) Seats " +
                    "FROM ticket t" +
                    "         JOIN train_route tr on t.route_id = tr.id" +
                    "        JOIN seats s on t.seat_id = s.id " +
                    "WHERE s.available " +
                    "GROUP BY tr.id, t.price;";

    public static final String SQL_GET_ALL_TICKETS =
            "SELECT tr.id, t.price, COUNT(t.id) Seats " +
                    "FROM ticket t" +
                    "         JOIN train_route tr on t.route_id = tr.id" +
                    "        JOIN seats s on t.seat_id = s.id " +
                    "GROUP BY tr.id, t.price;";

    public static final String SQL_GET_ALL_TICKET_ID_BY_ROUTE_ID =
            "SELECT t.id " +
                    "FROM ticket t " +
                    "JOIN train_route tr on t.route_id = tr.id " +
                    "WHERE tr.id = ?";

    public static final String SQL_GET_ALL_USERS =
            "SELECT u.id, first_name, last_name, email, name, role_id " +
                    "FROM user u" +
                    "         JOIN role r on u.role_id = r.id;";

    public static final String SQL_GET_TICKETS_INFO_BY_ROUTE =
            "SELECT COUNT(t.id) count_tickets, t.price " +
                    "FROM ticket t " +
                    "JOIN train_route tr on t.route_id = tr.id " +
                    "JOIN seats s on t.seat_id = s.id " +
                    "WHERE tr.id = ? AND s.available";

    public static final String SQL_GET_AMOUNT_STATION_BY_ROUTE =
            "SELECT COUNT(station_id) FROM intermediate_stations WHERE route_id = ?";

    public static final String SQL_GET_AMOUNT_STATION_BY_ROUTE_AND_STATION =
            "SELECT COUNT(station_id) " +
                    "FROM intermediate_stations " +
                    "JOIN schedule s on intermediate_stations.schedule_id = s.id " +
                    "WHERE route_id = ? AND s.travel_time < ? AND s.travel_time > ?";

    public static final String SQL_GET_ALL_INTERMEDIATE_STATION_BY_ROUTE =
            "SELECT * FROM intermediate_stations ins " +
                    "JOIN schedule s on ins.schedule_id = s.id " +
                    "WHERE route_id = ? " +
                    "ORDER BY s.travel_time";
}
