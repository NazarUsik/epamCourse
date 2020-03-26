package ua.nure.usik.SummaryTask4.db;

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

    public static boolean insertStation(Connection connection, String nameStation) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_INSERT_STATION);
        statement.setString(1, nameStation);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean updateStation(Connection connection, int stationId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_STATION);
        statement.setInt(1, stationId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean deleteStation(Connection connection, String nameStation) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_DELETE_STATION);
        statement.setString(1, nameStation);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
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

    public static boolean updateDepartureTimeInSchedule(Connection connection, int scheduleId, String departureTime)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_SCHEDULE_BY_DEP_TIME);
        statement.setString(1, departureTime);
        statement.setInt(2, scheduleId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean updateArrivalTimeInSchedule(Connection connection, int scheduleId, String arrivalTime)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_SCHEDULE_BY_ARR_TIME);
        statement.setString(1, arrivalTime);
        statement.setInt(2, scheduleId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean deleteSchedule(Connection connection, int scheduleId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_DELETE_SCHEDULE);
        statement.setInt(1, scheduleId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
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

    public static boolean updateDepartureStationInRoute(Connection connection, int routeId, String departureTime)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_ROUTE_BY_DEP_STATION);
        statement.setString(1, departureTime);
        statement.setInt(2, routeId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean updateArrivalStationInRoute(Connection connection, int routeId, String arrivalTime)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_ROUTE_BY_ARRIVAL_STATION);
        statement.setString(1, arrivalTime);
        statement.setInt(2, routeId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean updateScheduleInRoute(Connection connection, int routeId, int scheduleId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_ROUTE_BY_SCHEDULE);
        statement.setInt(1, scheduleId);
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

    public static boolean updateUser(Connection connection, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_USER);
        statement.setString(1, password);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean insertTrainComposition(Connection connection, int trainId, int carriageId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_INSERT_TRAIN_COMPOSITION);
        statement.setInt(1, trainId);
        statement.setInt(2, carriageId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean updateTrainCompositionByTrain(Connection connection, int trainId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_TRAIN_COMPOSITION_BY_TRAIN);
        statement.setInt(1, trainId);
        statement.setInt(2, trainId);


        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean updateTrainCompositionByCarriage(Connection connection, int trainId, int carriageId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_TRAIN_COMPOSITION_BY_CARRIAGE);
        statement.setInt(1, carriageId);
        statement.setInt(2, trainId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean deleteTrainCompositionByTrain(Connection connection, int trainId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_DELETE_TRAIN_COMPOSITION_BY_TRAIN);
        statement.setInt(1, trainId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean deleteTrainCompositionByCarriage(Connection connection, int carriageId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_DELETE_TRAIN_COMPOSITION_BY_CARRIAGE);
        statement.setInt(1, carriageId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
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

    public static boolean insertUser(Connection connection, Seats seats) throws SQLException {
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

    public static boolean updateUser(Connection connection, int seatsId, boolean availableSeats)
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

    public static boolean deleteUser(Connection connection, int seatsId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_DELETE_SEATS);
        statement.setInt(1, seatsId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
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

    public static boolean updateTrain(Connection connection, int trainId, int typeId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_TRAIN);
        statement.setInt(1, typeId);
        statement.setInt(2, trainId);

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

    public static boolean deleteTicket(Connection connection, int ticketId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_DELETE_TICKET);
        statement.setInt(1, ticketId);

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

    public static boolean updateIntermediateStation(Connection connection, int routeId, int scheduleId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_INTERMEDIATE_STATION);
        statement.setInt(1, scheduleId);
        statement.setInt(2, routeId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean deleteIntermediateStation(Connection connection, int routeId, int scheduleId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_DELETE_INTERMEDIATE_STATION);
        statement.setInt(1, routeId);
        statement.setInt(2, scheduleId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean insertTicketsList(Connection connection, int userId, int ticketId, int ticketStatusId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_INSERT_TICKETS_LIST);
        statement.setInt(1, userId);
        statement.setInt(2, ticketStatusId);
        statement.setInt(3, ticketId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean updateTicketsList(Connection connection, int userId, int ticketStatusId)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_UPDATE_TICKETS_LIST);
        statement.setInt(1, ticketStatusId);
        statement.setInt(2, userId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean deleteTicketsList(Connection connection, int userId, int ticketId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_DELETE_TICKETS_LIST);
        statement.setInt(1, userId);
        statement.setInt(2, ticketId);

        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static Map<String, Entity> findRouteByStation
            (Connection connection, String departureStation, String arrivalStation, String startDate)
            throws SQLException {
        Map<String, Entity> map = new LinkedHashMap<String, Entity>();

        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_FIND_ROUTE_BY_STATION);
        statement.setString(1, departureStation);
        statement.setString(2, arrivalStation);
        statement.setString(3, startDate);

        ResultSet result = statement.executeQuery();
        if (result.next()) {
            Station depStation = new Station(departureStation);
            Station arrStation = new Station(arrivalStation);
            Schedule schedule = new Schedule(result.getString(Fields.SCHEDULE_DEPARTURE_TIME),
                    result.getString(Fields.SCHEDULE_ARRIVAL_TIME),
                    result.getInt(Fields.SCHEDULE_TRAVEL_TIME));
            map.put("Station1", depStation);
            map.put("Station2", arrStation);
            map.put("Schedule", schedule);
            return map;
        }
        return null;
    }

//    public static Map<String, Entity> findRouteByStation
//            (Connection connection, String station, String startDate)
//            throws SQLException {
//
//    }

    public static Map<String, Entity> findIntermediateStation
            (Connection connection, String stationName, String startDate)
            throws SQLException {
        Map<String, Entity> map = new LinkedHashMap<String, Entity>();


        PreparedStatement statement = connection.prepareStatement(SQLQuery.SQL_FIND_INTERMEDIATE_STATION);
        statement.setString(1, stationName);
        statement.setString(2, startDate);

        ResultSet result = statement.executeQuery();
        if (result.next()) {
            Station station = new Station(stationName);
            Schedule schedule = new Schedule(result.getString(Fields.SCHEDULE_DEPARTURE_TIME),
                    result.getString(Fields.SCHEDULE_ARRIVAL_TIME),
                    result.getInt(Fields.SCHEDULE_TRAVEL_TIME));
            Route route = new Route();
            route.setId(result.getInt(Fields.IS_ROUTE_ID));
            map.put(Fields.ROUTE, route);
            map.put(Fields.STATION, station);
            map.put(Fields.SCHEDULE, schedule);
        return map;
        }
        return null;
    }
}
