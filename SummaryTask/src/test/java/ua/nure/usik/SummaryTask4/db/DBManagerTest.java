package ua.nure.usik.SummaryTask4.db;

import org.junit.Test;
import ua.nure.usik.SummaryTask4.db.connection.MySQLConnectionUtils;
import ua.nure.usik.SummaryTask4.db.entity.Entity;
import ua.nure.usik.SummaryTask4.db.entity.Schedule;

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
}