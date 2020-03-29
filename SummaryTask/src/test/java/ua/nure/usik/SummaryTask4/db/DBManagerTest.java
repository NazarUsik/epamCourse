package ua.nure.usik.SummaryTask4.db;

import javafx.util.Pair;
import org.junit.Test;
import ua.nure.usik.SummaryTask4.db.connection.MySQLConnectionUtils;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.db.entity.Entity;
import ua.nure.usik.SummaryTask4.db.entity.Schedule;
import ua.nure.usik.SummaryTask4.db.entity.Train;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class DBManagerTest {

    @Test
    public void findRouteRyStation() throws SQLException, ClassNotFoundException {
        String depS = "Vinnitsa";
        String arrS = "Kiev";
        String date = "2020-01-12";
        Connection connection = MySQLConnectionUtils.getMySQLConnection();
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
    public void getAllTrain() throws SQLException, ClassNotFoundException {
        Connection connection = MySQLConnectionUtils.getMySQLConnection();
        for (Pair<String, Train> pair: DBManager.getAllTrain(connection)){
            System.out.println(pair.getKey());
            System.out.println(pair.getValue());
        }
    }

    @Test
    public void getAllCarriage() {
    }
}