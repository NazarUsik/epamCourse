package ua.nure.usik.SummaryTask4.db;

import javafx.util.Pair;
import ua.nure.usik.SummaryTask4.db.constats.Fields;
import ua.nure.usik.SummaryTask4.db.constats.SQLQuery;
import ua.nure.usik.SummaryTask4.db.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/**
 * DB manager. Works with Apache Derby DB. Only the required DAO methods are
 * defined!
 *
 * @author N.Usik
 */

public final class DBManager {

    public static User findUser(Connection connection, String email, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_FIND_USER_BY_EMAIL_AND_PASS);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            User user = new User(result.getString(Fields.USER_FIRST_NAME),
                    result.getString(Fields.USER_LAST_NAME),
                    email, password, result.getInt(Fields.USER_ROLE_ID));
            user.setId(result.getInt(Fields.ID));
            return user;

        }
        return null;
    }

    public static User findUser(Connection connection, String email) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_FIND_USER_BY_EMAIL);
        statement.setString(1, email);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            User user = new User(result.getString(Fields.USER_FIRST_NAME),
                    result.getString(Fields.USER_LAST_NAME),
                    email, result.getString(Fields.USER_PASSWORD), result.getInt(Fields.USER_ROLE_ID));
            user.setId(result.getInt(Fields.ID));
            return user;
        }
        return null;
    }

    public static boolean insertStation(Connection connection, String nameStation, String nameStationRu)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_INSERT_STATION);
        statement.setString(1, nameStation);
        statement.setString(2, nameStationRu);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }


    public static Station getStationById(Connection connection, int stationId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_FIND_STATION_BY_ID);
        statement.setInt(1, stationId);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            Station station = new Station(result.getString(Fields.NAME), result.getString(Fields.NAME_RU));
            station.setId(result.getInt(Fields.ID));

            return station;
        }

        return null;
    }

    public static boolean updateStation(Connection connection, int stationId, String stationName, String stationNameRu) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_STATION);
        statement.setString(1, stationName);
        statement.setString(2, stationNameRu);
        statement.setInt(3, stationId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean deleteStation(Connection connection, int stationId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_DELETE_STATION);
        statement.setInt(1, stationId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public static Route findRoute(Connection connection, int routeId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_FIND_ROUTE);
        statement.setInt(1, routeId);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            Route route = new Route(result.getInt(Fields.ROUTE_TRAIN_ID), result.getInt(Fields.ROUTE_DEP_S_ID),
                    result.getInt(Fields.ROUTE_ARR_S_ID), result.getInt(Fields.ROUTE_SCHEDULE_ID));
            route.setId(result.getInt(Fields.ID));

            return route;
        }

        return null;
    }

    public static IntermediateStation findIntermediateStation(Connection connection, int routeId, int stationId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_FIND_INTERMEDIATE_STATION_BY_ID);
        statement.setInt(1, routeId);
        statement.setInt(2, stationId);


        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return new IntermediateStation(result.getInt(Fields.IS_ROUTE_ID),
                    result.getInt(Fields.IS_STATION_ID), result.getInt(Fields.IS_SCHEDULE_ID));
        }

        return null;
    }

    public static Schedule getScheduleById(Connection connection, int scheduleId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_FIND_SCHEDULE_BY_ID);
        statement.setInt(1, scheduleId);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            Schedule schedule = new Schedule(result.getString(Fields.SCHEDULE_DEPARTURE_TIME),
                    result.getString(Fields.SCHEDULE_ARRIVAL_TIME), result.getInt(Fields.SCHEDULE_TRAVEL_TIME));
            schedule.setId(result.getInt(Fields.ID));

            return schedule;
        }

        return null;
    }

    public static boolean insertSchedule(Connection connection, Schedule schedule) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_INSERT_SCHEDULE);
        statement.setString(1, schedule.getDepartureTime());
        statement.setString(2, schedule.getArrivalTime());
        statement.setInt(3, schedule.getTravelTime());

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean updateSchedule(Connection connection, int scheduleId, String departureTime,
                                         String arrivalTime, long travelTime)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_SCHEDULE);
        statement.setString(1, departureTime);
        statement.setString(2, arrivalTime);
        statement.setLong(3, travelTime);
        statement.setInt(4, scheduleId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public static boolean insertRoute(Connection connection, Route route) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_INSERT_ROUTE);
        statement.setInt(1, route.getTrainId());
        statement.setInt(2, route.getDepartureId());
        statement.setInt(3, route.getArrivalId());
        statement.setInt(4, route.getScheduleId());

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean updateRouteByTrainId(Connection connection, int routeId, int trainId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_ROUTE_BY_TRAIN_ID);
        statement.setInt(1, trainId);
        statement.setInt(2, routeId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean updateDepartureStationInRoute(Connection connection, int routeId, int departureStationId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_ROUTE_BY_DEP_STATION);
        statement.setInt(1, departureStationId);
        statement.setInt(2, routeId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean updateArrivalStationInRoute(Connection connection, int routeId, int arrivalStationId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_ROUTE_BY_ARRIVAL_STATION);
        statement.setInt(1, arrivalStationId);
        statement.setInt(2, routeId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }


    public static boolean deleteRoute(Connection connection, int routeId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_DELETE_ROUTE);
        statement.setInt(1, routeId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean insertUser(Connection connection, User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_INSERT_USER);
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getPassword());
        statement.setInt(5, user.getRoleId());

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean insertTrainComposition(Connection connection, int trainId, int carriageId, int numberCarriage)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_INSERT_TRAIN_COMPOSITION);
        statement.setInt(1, trainId);
        statement.setInt(2, carriageId);
        statement.setInt(3, numberCarriage);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public static boolean updateTrainCompositionByCarriage(Connection connection, int trainId, int carriageId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_TRAIN_COMPOSITION_BY_CARRIAGE);
        statement.setInt(1, trainId);
        statement.setInt(2, carriageId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }



    public static boolean insertCarriage(Connection connection, Carriage carriage) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_INSERT_CARRIAGE);
        statement.setInt(1, carriage.getTypeId());
        statement.setInt(2, carriage.getCountSeats());
        statement.setInt(3, carriage.getCountAvailableSeats());
        statement.setBoolean(4, carriage.isHaveRestaurant());

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean updateCarriage(Connection connection, int carriageId, int countAvailableSeats)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_CARRIAGE_BY_COUNT_AVAILABLE_SEATS);
        statement.setInt(1, countAvailableSeats);
        statement.setInt(2, carriageId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean deleteCarriage(Connection connection, int carriageId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_DELETE_CARRIAGE);
        statement.setInt(1, carriageId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean insertSeat(Connection connection, Seats seats) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_INSERT_SEATS);
        statement.setInt(1, seats.getCarriageId());
        statement.setInt(2, seats.getNumber());
        statement.setBoolean(3, seats.isAvailable());

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean updateSeat(Connection connection, int seatsId, boolean availableSeats)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_SEATS);
        statement.setBoolean(1, availableSeats);
        statement.setInt(2, seatsId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }


    public static Train findTrain(Connection connection, int trainId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_FIND_TRAIN);
        statement.setInt(1, trainId);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            Train train = new Train(result.getInt(Fields.TRAIN_TYPE_ID));
            train.setId(trainId);

            return train;
        }

        return null;
    }

    public static boolean insertTrain(Connection connection, Train train) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_INSERT_TRAIN);
        statement.setInt(1, train.getTypeId());

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }


    public static boolean deleteTrain(Connection connection, int trainId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_DELETE_TRAIN);
        statement.setInt(1, trainId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean insertTicket(Connection connection, Ticket ticket) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_INSERT_TICKET);
        statement.setInt(1, ticket.getRoutId());
        statement.setFloat(2, ticket.getPrice());
        statement.setInt(3, ticket.getCarriageId());
        statement.setInt(4, ticket.getSeatId());

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean updateTicket(Connection connection, int ticketId, float price) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_TICKET);
        statement.setFloat(1, price);
        statement.setInt(2, ticketId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean insertIntermediateStation(Connection connection, int routeId, int stationId, int scheduleId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_INSERT_INTERMEDIATE_STATION);
        statement.setInt(1, routeId);
        statement.setInt(2, scheduleId);
        statement.setInt(3, stationId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean updateIntermediateStationByStation(Connection connection, int routeId,
                                                             int stationId, int scheduleId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_INTERMEDIATE_STATION_BY_STATION);
        statement.setInt(1, stationId);
        statement.setInt(2, routeId);
        statement.setInt(3, scheduleId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }



    public static boolean deleteIntermediateStation(Connection connection, int routeId, int stationId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_DELETE_INTERMEDIATE_STATION);
        statement.setInt(1, routeId);
        statement.setInt(2, stationId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean insertTicketsList(Connection connection, int userId, int ticketId, int ticketStatusId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_INSERT_TICKETS_LIST);
        statement.setInt(1, userId);
        statement.setInt(2, ticketId);
        statement.setInt(3, ticketStatusId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }



    public static Route findRouteByStation
            (Connection connection, String departureStation, String arrivalStation, String startDate, String language)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement
                (language.equals("en") ? SQLQuery.SQL_FIND_ROUTE_BY_TWO_STATION :
                        SQLQuery.SQL_FIND_ROUTE_BY_TWO_STATION_RU);

        statement.setString(1, departureStation);
        statement.setString(2, arrivalStation);
        statement.setString(3, startDate);

        ResultSet result = statement.executeQuery();
        if (result.next()) {
            Route route = new Route(result.getInt(Fields.ROUTE_TRAIN_ID), result.getInt(Fields.ROUTE_DEP_S_ID),
                    result.getInt(Fields.ROUTE_ARR_S_ID), result.getInt(Fields.ROUTE_SCHEDULE_ID));
            route.setId(result.getInt(Fields.TRAIN_ROUTE + "." + Fields.ID));

            return route;
        }
        return null;
    }

    public static List<Route> findRouteByStation
            (Connection connection, String station, String startDate, String language)
            throws SQLException {
        List<Route> list = new LinkedList<>();

        PreparedStatement statement = connection.prepareStatement
                (language.equals("en") ? SQLQuery.SQL_FIND_ROUTE_BY_ONE_STATION :
                        SQLQuery.SQL_FIND_ROUTE_BY_ONE_STATION_RU);
        statement.setString(1, station);
        statement.setString(2, station);
        statement.setString(3, startDate);

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Route route = new Route(result.getInt(Fields.ROUTE_TRAIN_ID), result.getInt(Fields.ROUTE_DEP_S_ID),
                    result.getInt(Fields.ROUTE_ARR_S_ID), result.getInt(Fields.ROUTE_SCHEDULE_ID));
            route.setId(result.getInt(Fields.TRAIN_ROUTE + "." + Fields.ID));

            list.add(route);
        }
        return list;
    }


    public static List<IntermediateStation> findIntermediateStation
            (Connection connection, String stationName, String startDate, String language)
            throws SQLException {
        List<IntermediateStation> list = new LinkedList<>();


        PreparedStatement statement = connection.prepareStatement
                (language.equals("en") ? SQLQuery.SQL_FIND_INTERMEDIATE_STATION :
                        SQLQuery.SQL_FIND_INTERMEDIATE_STATION_RU);
        statement.setString(1, stationName);
        statement.setString(2, startDate);

        ResultSet result = statement.executeQuery();
        while (result.next()) {

            list.add(new IntermediateStation(result.getInt(Fields.IS_ROUTE_ID),
                    result.getInt(Fields.IS_STATION_ID), result.getInt(Fields.IS_SCHEDULE_ID)));

        }
        return list;
    }

    public static List<Pair<String, Train>> getAllTrain(Connection connection, String language) throws SQLException {
        List<Pair<String, Train>> list = new LinkedList<Pair<String, Train>>();

        ResultSet result = connection.createStatement().executeQuery(SQLQuery.SQL_FIND_ALL_TRAIN);

        while (result.next()) {
            Train train = new Train(result.getInt(Fields.TRAIN_TYPE_ID));
            train.setId(result.getInt(Fields.ID));

            if (language.equals("en")) {
                list.add(new Pair<>(result.getString(Fields.NAME), train));
            } else {
                list.add(new Pair<>(result.getString(Fields.NAME_RU), train));
            }
        }
        return list;
    }

    public static List<Pair<Integer, Pair<String, Carriage>>> getAllCarriage(Connection connection, String language)
            throws SQLException {
        List<Pair<Integer, Pair<String, Carriage>>> list = new LinkedList<Pair<Integer, Pair<String, Carriage>>>();

        ResultSet result = connection.createStatement().executeQuery(SQLQuery.SQL_FIND_ALL_CARRIAGE);

        while (result.next()) {
            Carriage carriage = new Carriage(result.getInt(Fields.CARRIAGE_TYPE_ID),
                    result.getInt(Fields.CARRIAGE_COUNT_SEATS),
                    result.getInt(Fields.CARRIAGE_COUNT_AVAILABLE_SEATS),
                    result.getBoolean(Fields.CARRIAGE_HAVE_REST));
            carriage.setId(result.getInt(Fields.ID));

            if (language.equals("en")) {
                list.add(new Pair<>(result.getInt(Fields.ROUTE_TRAIN_ID),
                        new Pair<>(result.getString(Fields.NAME), carriage)));
            } else {
                list.add(new Pair<>(result.getInt(Fields.ROUTE_TRAIN_ID),
                        new Pair<>(result.getString(Fields.NAME_RU), carriage)));
            }
        }
        return list;
    }

    public static int getCarriageIdLastAdd(Connection connection) throws SQLException {
        ResultSet result = connection.createStatement().executeQuery(SQLQuery.SQL_LAST_ADD_CARRIAGE_ID);

        if (result.next()) {
            return result.getInt(Fields.ID);
        }
        return 0;
    }



    public static int getLastCarriageNumberByTrain(Connection connection, int trainId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_GET_LAST_CARRIAGE_NUMBER_BY_TRAIN);
        statement.setInt(1, trainId);

        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return result.getInt(1);
        }
        return 0;
    }

    public static List<Station> getAllStation(Connection connection) throws SQLException {
        ResultSet result = connection.createStatement().executeQuery(SQLQuery.SQL_GET_ALL_STATION);

        List<Station> list = new LinkedList<>();

        while (result.next()) {
            Station station = new Station(result.getString(Fields.NAME), result.getString(Fields.NAME_RU));
            station.setId(result.getInt(Fields.ID));
            list.add(station);
        }

        return list;
    }

    public static Map<String, Entity> getAllRoute(Connection connection) throws SQLException {
        Map<String, Entity> map = new LinkedHashMap<>();

        ResultSet result = connection.createStatement().executeQuery(SQLQuery.SQL_GET_ALL_ROUTE);

        int i = 1;
        while (result.next()) {
            Route route = new Route(result.getInt(Fields.ROUTE_TRAIN_ID), result.getInt(Fields.ROUTE_DEP_S_ID),
                    result.getInt(Fields.ROUTE_ARR_S_ID), result.getInt(Fields.ROUTE_SCHEDULE_ID));
            route.setId(result.getInt(Fields.ID));

            Station depStation = new Station(result.getString(Fields.ROUTE_DEP_STATION_NAME),
                    result.getString(Fields.ROUTE_DEP_STATION_NAME_RU));
            depStation.setId(result.getInt(Fields.ROUTE_DEP_S_ID));

            Station arrStation = new Station(result.getString(Fields.ROUTE_ARR_STATION_NAME),
                    result.getString(Fields.ROUTE_ARR_STATION_NAME_RU));
            arrStation.setId(result.getInt(Fields.ROUTE_ARR_S_ID));

            Schedule schedule = new Schedule(result.getString(Fields.SCHEDULE_DEPARTURE_TIME),
                    result.getString(Fields.SCHEDULE_ARRIVAL_TIME), result.getInt(Fields.SCHEDULE_TRAVEL_TIME));
            schedule.setId(result.getInt(Fields.ROUTE_SCHEDULE_ID));

            map.put(Fields.ROUTE + i, route);
            map.put(Fields.STATION + "_dep" + i, depStation);
            map.put(Fields.STATION + "_arr" + i, arrStation);
            map.put(Fields.SCHEDULE + i, schedule);

            i++;
        }

        return map;
    }

    public static Map<String, Entity> getAllIntermediateStation(Connection connection) throws SQLException {
        Map<String, Entity> map = new LinkedHashMap<>();

        ResultSet result = connection.createStatement().executeQuery(SQLQuery.SQL_GET_ALL_INTERMEDIATE_STATION);

        int i = 1;
        while (result.next()) {
            IntermediateStation intermediateStation = new IntermediateStation(result.getInt(Fields.IS_ROUTE_ID),
                    result.getInt(Fields.IS_STATION_ID), result.getInt(Fields.IS_SCHEDULE_ID));

            Station station = new Station(result.getString(Fields.NAME), result.getString(Fields.NAME_RU));
            station.setId(result.getInt(Fields.IS_STATION_ID));

            Schedule schedule = new Schedule(result.getString(Fields.SCHEDULE_DEPARTURE_TIME),
                    result.getString(Fields.SCHEDULE_ARRIVAL_TIME), result.getInt(Fields.SCHEDULE_TRAVEL_TIME));
            schedule.setId(result.getInt(Fields.IS_SCHEDULE_ID));

            map.put(Fields.IS + i, intermediateStation);
            map.put(Fields.STATION + i, station);
            map.put(Fields.SCHEDULE + i, schedule);

            i++;
        }

        return map;
    }



    public static Station findStationByName(Connection connection, String stationName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_FIND_STATION_BY_NAME);
        statement.setString(1, stationName);

        ResultSet result = statement.executeQuery();
        if (result.next()) {
            Station station = new Station(stationName, result.getString(Fields.NAME_RU));
            station.setId(result.getInt(Fields.ID));

            return station;
        }

        return null;
    }

    public static Station findStationByNameRu(Connection connection, String stationNameRu) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_FIND_STATION_BY_NAME_RU);
        statement.setString(1, stationNameRu);

        ResultSet result = statement.executeQuery();
        if (result.next()) {
            Station station = new Station(result.getString(Fields.NAME), result.getString(Fields.NAME_RU));
            station.setId(result.getInt(Fields.ID));

            return station;
        }

        return null;
    }

    public static int getLastScheduleId(Connection connection) throws SQLException {
        ResultSet result = connection.createStatement().executeQuery(SQLQuery.SQL_GET_LAST_SCHEDULE_ID);

        if (result.next()) {
            return result.getInt(Fields.ID);
        }

        return 0;
    }

    public static int getLastRouteId(Connection connection) throws SQLException {
        ResultSet result = connection.createStatement().executeQuery(SQLQuery.SQL_GET_LAST_ROUTE_ID);

        if (result.next()) {
            return result.getInt(Fields.ID);
        }

        return 0;
    }



    public static Map<Seats, Carriage> findCarriagesSeatsByTrainId(Connection connection, int trainId)
            throws SQLException {
        Map<Seats, Carriage> map = new HashMap<>();

        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_FIND_CARRIAGES_SEATS_BY_TRAIN_ID);
        statement.setInt(1, trainId);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            Seats seats = new Seats(result.getInt(Fields.SEATS_CARRIAGE_ID),
                    result.getInt(Fields.SEATS_NUMBER), result.getBoolean(Fields.SEATS_AVAILABLE));
            seats.setId(result.getInt("s." + Fields.ID));

            Carriage carriage = new Carriage(result.getInt(Fields.CARRIAGE_TYPE_ID),
                    result.getInt(Fields.CARRIAGE_COUNT_SEATS),
                    result.getInt(Fields.CARRIAGE_COUNT_AVAILABLE_SEATS),
                    result.getBoolean(Fields.CARRIAGE_HAVE_REST));
            carriage.setId(result.getInt("c." + Fields.ID));

            map.put(seats, carriage);
        }

        return map;
    }

    public static boolean availableRoute(Connection connection, int routeId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_FIND_ROUTE);
        statement.setInt(1, routeId);

        ResultSet result = statement.executeQuery();

        return result.next();
    }

    public static Map<Pair<Integer, Float>, Pair<Integer, Integer>> getAllTickets(Connection connection)
            throws SQLException {
        Map<Pair<Integer, Float>, Pair<Integer, Integer>> map = new LinkedHashMap<>();

        ResultSet resultAvail = connection.createStatement().executeQuery(SQLQuery.SQL_GET_ALL_AVAILABLE_TICKETS);
        ResultSet resultAll = connection.createStatement().executeQuery(SQLQuery.SQL_GET_ALL_TICKETS);


        while (resultAvail.next() && resultAll.next()) {
            map.put(new Pair<>(resultAvail.getInt(Fields.ID), resultAvail.getFloat(Fields.TICKET_PRICE)),
                    new Pair<>(resultAll.getInt(Fields.SEATS), resultAvail.getInt(Fields.SEATS)));
        }

        return map;
    }

    public static List<Integer> getAllTicketIdByRoute(Connection connection, int routeId) throws SQLException {
        List<Integer> list = new LinkedList<>();

        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_GET_ALL_TICKET_ID_BY_ROUTE_ID);
        statement.setInt(1, routeId);

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            list.add(result.getInt(1));
        }

        return list;
    }

    public static List<Pair<User, String>> getAllUsers(Connection connection) throws SQLException {
        List<Pair<User, String>> list = new LinkedList<>();

        ResultSet result = connection.createStatement().executeQuery(SQLQuery.SQL_GET_ALL_USERS);

        while (result.next()) {
            User user = new User(result.getString(Fields.USER_FIRST_NAME),
                    result.getString(Fields.USER_LAST_NAME),
                    result.getString(Fields.USER_EMAIL), null,
                    result.getInt(Fields.USER_ROLE_ID));
            user.setId(result.getInt(Fields.ID));

            list.add(new Pair<>(user, result.getString(Fields.NAME)));
        }

        return list;
    }

    public static Pair<Integer, Float> getTicketsInfoByRoute(Connection connection, int routeId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_GET_TICKETS_INFO_BY_ROUTE);
        statement.setInt(1, routeId);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return new Pair<>(result.getInt(Fields.COUNT_TICKETS), result.getFloat(Fields.TICKET_PRICE));
        }

        return null;
    }


    public static List<IntermediateStation> getIntermediateStationsByRoute(Connection connection, int routeId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_GET_ALL_INTERMEDIATE_STATION_BY_ROUTE);
        statement.setInt(1, routeId);

        List<IntermediateStation> list = new LinkedList<>();

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            list.add(new IntermediateStation(routeId, result.getInt(Fields.IS_STATION_ID),
                    result.getInt(Fields.IS_SCHEDULE_ID)));
        }

        return list;
    }

    public static Map<String, Integer> getTrainInfo(Connection connection, int trainId, String language)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_GET_INFO_BY_TRAIN);
        statement.setInt(1, trainId);

        Map<String, Integer> map = new LinkedHashMap<>();

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            map.put(result.getString(language.equals("en") ? Fields.NAME : Fields.NAME_RU),
                    result.getInt(Fields.CARRIAGE_COUNT_AVAILABLE_SEATS));
        }

        return map;
    }

    public static int getTicketIdByRoute(Connection connection, int routeId, String carriageType) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_GET_TICKET_ID_BY_ROUTE);
        statement.setInt(1, routeId);
        statement.setString(2, carriageType);

        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return (result.getInt(1));
        }

        return 0;
    }

    public static Ticket getTicketById(Connection connection, int tickedId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_GET_TICKET_BY_ID);
        statement.setInt(1, tickedId);

        ResultSet result = statement.executeQuery();
        if (result.next()) {
            Ticket ticket = new Ticket(result.getInt(Fields.IS_ROUTE_ID), result.getFloat(Fields.TICKET_PRICE), result.getInt(Fields.SEATS_CARRIAGE_ID), result.getInt(Fields.TICKET_SEAT_ID));
            ticket.setId(tickedId);
            return ticket;
        }

        return null;
    }

    public static Carriage getCarriage(Connection connection, int carriageId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_GET_CARRIAGE);
        statement.setInt(1, carriageId);

        ResultSet result = statement.executeQuery();
        if (result.next()) {
            Carriage carriage = new Carriage(result.getInt(Fields.CARRIAGE_TYPE_ID),
                    result.getInt(Fields.CARRIAGE_COUNT_SEATS), result.getInt(Fields.CARRIAGE_COUNT_AVAILABLE_SEATS),
                    result.getBoolean(Fields.CARRIAGE_HAVE_REST));
            carriage.setId(result.getInt(Fields.ID));
            return carriage;
        }
        return null;
    }

    public static List<Ticket> getTicketsByUser(Connection connection, int userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_GET_ALL_TICKETS_BY_USER);
        statement.setInt(1, userId);

        List<Ticket> list = new LinkedList<>();

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Ticket ticket = new Ticket(result.getInt(Fields.IS_ROUTE_ID), result.getFloat(Fields.TICKET_PRICE), result.getInt(Fields.SEATS_CARRIAGE_ID), result.getInt(Fields.TICKET_SEAT_ID));
            ticket.setId(result.getInt(Fields.TICKET_ID));

            list.add(ticket);
        }

        return list;
    }
}