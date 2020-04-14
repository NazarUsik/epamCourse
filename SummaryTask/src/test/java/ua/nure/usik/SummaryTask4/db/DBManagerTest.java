package ua.nure.usik.SummaryTask4.db;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import ua.nure.usik.SummaryTask4.db.connection.ConnectionUtils;
import ua.nure.usik.SummaryTask4.db.entity.Entity;
import ua.nure.usik.SummaryTask4.db.entity.Station;
import ua.nure.usik.SummaryTask4.db.entity.Train;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class DBManagerTest {

    private static Connection connection;

    static {
        try {
            connection = ConnectionUtils.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void findRouteRyStation() throws SQLException {
        String depS = "Vinnitsa";
        String arrS = "Kiev";
        String date = "2020-01-12";

        Map<String, Entity> map1 = DBManager.findRouteByStation(connection, depS, arrS, date);
        Map<String, Entity> map2 = null;

//        if (map1 != null) {
//            for (Entity entity : map1.values()) {
//                System.out.println(entity);
//            }
//        } else {
//            map1 = DBManager.findIntermediateStation(connection, depS, date);
//            map2 = DBManager.findIntermediateStation(connection, arrS, date);
//
//            if (map1 != null && map2 != null) {
//                int routeId1 = map1.get("Route").getId();
//                int routeId2 = map2.get("Route").getId();
//
//                Schedule schedule1 = (Schedule) map1.get("Schedule");
//                Schedule schedule2 = (Schedule) map2.get("Schedule");
//
//
//                if (routeId1 == routeId2  && schedule1.getTravelTime() < schedule2.getTravelTime()) {
//
//                    for (Entity entity : map1.values()) {
//                        System.out.println(entity);
//                    }
//
//                    for (Entity entity : map2.values()) {
//                        System.out.println(entity);
//                    }
//                } else {
//                    System.out.println("Route no found");
//                }
//            } else {
//                System.out.println("Route not found");
//            }
//        }


    }

    @Test
    public void findUser() {
    }

    @Test
    public void testFindUser() {
    }

    @Test
    public void insertStation() {
    }

    @Test
    public void updateStation() {
    }

    @Test
    public void deleteStation() {
    }

    @Test
    public void insertSchedule() {
    }

    @Test
    public void updateDepartureTimeInSchedule() {
    }

    @Test
    public void updateArrivalTimeInSchedule() {
    }

    @Test
    public void deleteSchedule() {
    }

    @Test
    public void insertRoute() {
    }

    @Test
    public void updateDepartureStationInRoute() {
    }

    @Test
    public void updateArrivalStationInRoute() {
    }

    @Test
    public void updateScheduleInRoute() {
    }

    @Test
    public void deleteRoute() {
    }

    @Test
    public void insertUser() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void insertTrainComposition() {
    }

    @Test
    public void updateTrainCompositionByTrain() {
    }

    @Test
    public void updateTrainCompositionByCarriage() {
    }

    @Test
    public void deleteTrainCompositionByTrain() {
    }

    @Test
    public void deleteTrainCompositionByCarriage() {
    }

    @Test
    public void insertCarriage() {
    }

    @Test
    public void updateCarriage() {
    }

    @Test
    public void deleteCarriage() {
    }

    @Test
    public void testInsertUser() {
    }

    @Test
    public void testUpdateUser() {
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void insertTrain() {
    }

    @Test
    public void updateTrain() {
    }

    @Test
    public void deleteTrain() {
    }

    @Test
    public void insertTicket() {
    }

    @Test
    public void updateTicket() {
    }

    @Test
    public void deleteTicket() {
    }

    @Test
    public void insertIntermediateStation() {
    }

    @Test
    public void updateIntermediateStation() {
    }

    @Test
    public void deleteIntermediateStation() {
    }

    @Test
    public void insertTicketsList() {
    }

    @Test
    public void updateTicketsList() {
    }

    @Test
    public void deleteTicketsList() {
    }

    @Test
    public void findRouteByStation() {
    }

    @Test
    public void findIntermediateStation() {
    }

    @Test
    public void getAllTrain() throws SQLException {
        for (Pair<String, Train> pair : DBManager.getAllTrain(connection)) {
            System.out.println(pair.getKey());
            System.out.println(pair.getValue());
        }
    }

    @Test
    public void getAllCarriage() {
    }

    @Test
    public void insertSeat() {
    }

    @Test
    public void getCarriageIdLastAdd() {
    }

    @Test
    public void getTrainIdLastAdd() {
    }

    @Test
    public void getLastCarriageNumberByTrain() {
    }

    @Test
    public void getAllStation() {

    }

    @Test
    public void getAllRoute() throws SQLException {
        Map<String, Entity> map = DBManager.getAllRoute(connection);

        for (Map.Entry<String, Entity> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("_________________________");
        }
    }

    @Test
    public void getAllIntermediateStation() throws SQLException {
        Map<String, Entity> map = DBManager.getAllIntermediateStation(connection);

        for (Map.Entry<String, Entity> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("_________________________");
        }
    }

    @Test
    public void findStationByName() throws SQLException {
        Station kharkov = DBManager.findStationByName(connection, "Kharkov");

        Assert.assertEquals(1, kharkov.getId());

        Station stationNull = DBManager.findStationByName(connection, "ThisStationNotFount");

        Assert.assertNull(stationNull);
    }

    @Test
    public void getLastScheduleId() throws SQLException {
        int lastScheduleId = DBManager.getLastScheduleId(connection);

        Assert.assertEquals(6, lastScheduleId);
    }

    @Test
    public void getLastRouteId() throws SQLException {
        int lastRouteId = DBManager.getLastRouteId(connection);

        Assert.assertEquals(2, lastRouteId);
    }

    @Test
    public void findCarriagesSeatsByTrainId() {

    }

    @Test
    public void findRoute() {
    }

    @Test
    public void updateSchedule() {
    }

    @Test
    public void updateRouteByTrainId() {
    }

    @Test
    public void updateIntermediateStationByStation() {
    }

    @Test
    public void updateIntermediateStationBySchedule() {
    }

    @Test
    public void getAmountSeatsFromTrain() {
    }

    @Test
    public void availableRoute() {
    }

    @Test
    public void getAllAvailableTicketsByRoute() throws SQLException {
        Map<Pair<Integer, Float>, Pair<Integer, Integer>> map = DBManager.getAllTickets(connection);
        for(Map.Entry<Pair<Integer, Float>, Pair<Integer, Integer>> entry: map.entrySet()){
            System.out.println(entry.getKey().getKey());
            System.out.println(entry.getKey().getValue());
            System.out.println(entry.getValue().getKey());
            System.out.println(entry.getValue().getValue());
        }
    }

    @Test
    public void getAllNotAvailableTicketsByRoute() {
    }
}