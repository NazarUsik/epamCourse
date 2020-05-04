package ua.nure.usik.SummaryTask4.utils;

import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.constats.Fields;
import ua.nure.usik.SummaryTask4.db.entity.*;
import ua.nure.usik.SummaryTask4.utils.constants.RouteType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RouteUtils {
    private static List<String> routeTypes;

    public static List<Route> getRoutesByStation(Connection connection, String departureStation,
                                                 String arrivalStation, String startDate, String language)
            throws SQLException {
        List<Route> foundRoutes = new LinkedList<>();
        routeTypes = new LinkedList<>();

        Route route = DBManager.findRouteByStation(connection, departureStation, arrivalStation, startDate, language);
        List<IntermediateStation> isDep;
        List<IntermediateStation> isArr;

        if (route != null) {
            foundRoutes.add(route);
            routeTypes.add(RouteType.ONLY_ROUTE);
        } else {
            List<Route> listRoute = DBManager.findRouteByStation(connection, departureStation, startDate, language);

            if (listRoute.isEmpty()) {
                if (!(listRoute = DBManager.findRouteByStation(connection, arrivalStation, startDate, language)).isEmpty()) {
                    isDep = DBManager.findIntermediateStation(connection, departureStation, startDate, language);
                    for (Route rt : listRoute) {
                        for (IntermediateStation isd : isDep) {
                            if (isd.getRouteId() == rt.getId()) {
                                foundRoutes.add(rt);
                                routeTypes.add(RouteType.ARR_ROUTE_DEP_INTER);
                            }
                        }
                    }
                }
            } else {
                isArr = DBManager.findIntermediateStation(connection, arrivalStation, startDate, language);
                for (Route rt : listRoute) {
                    for (IntermediateStation isa : isArr) {
                        if (isa.getRouteId() == rt.getId()) {
                            foundRoutes.add(rt);
                            routeTypes.add(RouteType.DEP_ROUTE_ARR_INTER);
                        }
                    }
                }
            }

            isDep = DBManager.findIntermediateStation(connection, departureStation, startDate, language);
            isArr = DBManager.findIntermediateStation(connection, arrivalStation, startDate, language);

            for (IntermediateStation isd : isDep) {
                for (IntermediateStation isa : isArr) {
                    Schedule depSc = DBManager.getScheduleById(connection, isd.getScheduleId());
                    Schedule arrSc = DBManager.getScheduleById(connection, isa.getScheduleId());
                    if (depSc != null && arrSc != null) {
                        if (isd.getRouteId() == isa.getRouteId() && depSc.getTravelTime() < arrSc.getTravelTime()) {
                            foundRoutes.add(DBManager.findRoute(connection, isa.getRouteId()));
                            routeTypes.add(RouteType.ONLY_INTER);
                        }
                    }
                }
            }
        }

        return foundRoutes;
    }


    public static List<String> getRouteTypes() {
        return RouteUtils.routeTypes;
    }


    public static Map<String, Entity> convertIdToEntity(Connection connection, List<Route> routes,
                                                        String depStat, String arrStat, String lang)
            throws SQLException {
        Map<String, Entity> map = new LinkedHashMap<>();

        Station dep = lang.equals("en") ? DBManager.findStationByName(connection, depStat) :
                DBManager.findStationByNameRu(connection, depStat);
        Station arr = lang.equals("en") ? DBManager.findStationByName(connection, arrStat) :
                DBManager.findStationByNameRu(connection, arrStat);

        List<String> routeTypes = RouteUtils.getRouteTypes();

        for (int j = 0; j < routes.size(); j++) {
            Route route = routes.get(j);

            if (routeTypes.get(j).equals(RouteType.ONLY_ROUTE)) {
                map.put(Fields.SCHEDULE + (j + 1), DBManager.getScheduleById(connection, route.getScheduleId()));
                map.put(Fields.STATION + "_dep" + (j + 1), DBManager.getStationById(connection,
                        route.getDepartureId()));
                map.put(Fields.STATION + "_arr" + (j + 1), DBManager.getStationById(connection, route.getArrivalId()));
                map.put(Fields.ROUTE + (j + 1), route);
            }

            if (routeTypes.get(j).equals(RouteType.DEP_ROUTE_ARR_INTER)) {
                IntermediateStation intStat = DBManager.findIntermediateStation(connection, route.getId(), arr.getId());

                map.put(Fields.SCHEDULE + (j + 1), DBManager.getScheduleById(connection, intStat.getScheduleId()));
                map.put(Fields.STATION + "_dep" + (j + 1), DBManager.getStationById(connection,
                        route.getDepartureId()));
                map.put(Fields.STATION + "_arr" + (j + 1), DBManager.getStationById(connection,
                        intStat.getStationId()));
                map.put(Fields.ROUTE + (j + 1), route);
            }

            if (routeTypes.get(j).equals(RouteType.ARR_ROUTE_DEP_INTER)) {
                IntermediateStation intStat = DBManager.findIntermediateStation(connection, route.getId(), dep.getId());
                Schedule depSch = DBManager.getScheduleById(connection, intStat.getScheduleId());
                Schedule routeSch = DBManager.getScheduleById(connection, route.getScheduleId());


                map.put(Fields.SCHEDULE + (j + 1), new Schedule(depSch.getDepartureTime(), routeSch.getArrivalTime(),
                        routeSch.getTravelTime() - depSch.getTravelTime()));
                map.put(Fields.STATION + "_dep" + (j + 1), DBManager.getStationById(connection,
                        intStat.getStationId()));
                map.put(Fields.STATION + "_arr" + (j + 1), DBManager.getStationById(connection, route.getArrivalId()));
                map.put(Fields.ROUTE + (j + 1), route);
            }

            if (routeTypes.get(j).equals(RouteType.ONLY_INTER)) {
                IntermediateStation depS = DBManager.findIntermediateStation(connection, route.getId(), dep.getId());
                IntermediateStation arrS = DBManager.findIntermediateStation(connection, route.getId(), arr.getId());
                Schedule depSch = DBManager.getScheduleById(connection, depS.getScheduleId());
                Schedule arrSch = DBManager.getScheduleById(connection, arrS.getScheduleId());


                map.put(Fields.SCHEDULE + (j + 1), new Schedule(depSch.getDepartureTime(), arrSch.getArrivalTime(),
                        arrSch.getTravelTime() - depSch.getTravelTime()));
                map.put(Fields.STATION + "_dep" + (j + 1), DBManager.getStationById(connection, dep.getId()));
                map.put(Fields.STATION + "_arr" + (j + 1), DBManager.getStationById(connection, arr.getId()));
                map.put(Fields.ROUTE + (j + 1), route);
            }
        }

        return map;
    }


    public static float calculateTicketPrice(Connection connection, int routeId,
                                             Station departureStation, Station arrivalStation) throws SQLException {

        Route route = DBManager.findRoute(connection, routeId);

        if (route == null) {
            return 0;
        }

        float price = DBManager.getTicketsInfoByRoute(connection, routeId).getValue();

        if (route.getDepartureId() == departureStation.getId() && route.getArrivalId() == arrivalStation.getId()) {
            return price;
        }

        List<IntermediateStation> intermediateStations = DBManager.getIntermediateStationsByRoute(connection, routeId);

        int indexDep = 0;
        int indexArr = 0;

        if (route.getDepartureId() == departureStation.getId() && route.getArrivalId() != arrivalStation.getId()) {
            for (IntermediateStation ins : intermediateStations) {
                if (ins.getStationId() == arrivalStation.getId()) {
                    indexArr = intermediateStations.indexOf(ins) + 1;
                }
            }
        }

        if (route.getDepartureId() != departureStation.getId() && route.getArrivalId() == arrivalStation.getId()) {
            indexArr = intermediateStations.size() + 1;

            for (IntermediateStation ins : intermediateStations) {
                if (ins.getStationId() == departureStation.getId()) {
                    indexDep = intermediateStations.indexOf(ins) + 1;
                }
            }
        }

        if (route.getDepartureId() != departureStation.getId() && route.getArrivalId() != arrivalStation.getId()) {
            for (IntermediateStation ins : intermediateStations) {
                if (ins.getStationId() == departureStation.getId()) {
                    indexDep = intermediateStations.indexOf(ins) + 1;
                }
                if (ins.getStationId() == arrivalStation.getId()) {
                    indexArr = intermediateStations.indexOf(ins) + 1;
                }
            }
        }

        int amountAllStation = intermediateStations.size() + 2;
        int amountStation = (indexArr - indexDep);

        return Math.round(price * amountStation / amountAllStation);
    }

}
