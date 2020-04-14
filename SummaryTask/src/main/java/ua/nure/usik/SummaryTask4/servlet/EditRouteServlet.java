package ua.nure.usik.SummaryTask4.servlet;

import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.ConnectionUtils;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.db.entity.Route;
import ua.nure.usik.SummaryTask4.db.entity.Station;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/editRoute")
public class EditRouteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/adminPage");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection connection = MyUtils.getStoredConnection(req);

        String edStatus = "";

        int routeId = Integer.parseInt(req.getParameter("route_id"));
        int trainId = Integer.parseInt(req.getParameter("train_id"));
        String stationDep = req.getParameter("station_dep");
        String stationArr = req.getParameter("station_arr");

        LocalDateTime depTime = null;
        LocalDateTime arrTime = null;

        try {
            depTime = LocalDateTime.parse(req.getParameter("dep_time").replace(' ', 'T'));
            arrTime = LocalDateTime.parse(req.getParameter("arr_time").replace(' ', 'T'));
        } catch (DateTimeParseException ex) {
            edStatus += "Incorrect date!";
            resp.sendRedirect(req.getContextPath() + "/adminPage?editRouteStatus=" + edStatus);
            return;
        }

        Duration duration = Duration.between(depTime, arrTime);

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Route route = DBManager.findRoute(connection, routeId);

            if (route != null) {
                boolean updateByTrainId = DBManager.updateRouteByTrainId(connection, routeId, trainId);
                boolean updateDepStation = false;

                Station station;

                if ((station = DBManager.findStationByName(connection, stationDep)) != null) {
                    updateDepStation = DBManager.updateDepartureStationInRoute
                            (connection, route.getDepartureId(), station.getId());
                }

                boolean updateArrStation = false;

                if ((station = DBManager.findStationByName(connection, stationArr)) != null) {
                    updateArrStation = DBManager.updateArrivalStationInRoute
                            (connection, route.getDepartureId(), station.getId());
                }

                boolean updateSchedule = DBManager.updateSchedule(connection, route.getScheduleId(),
                        depTime.toString(), arrTime.toString(), duration.toMinutes());

                if (updateByTrainId && updateDepStation && updateArrStation && updateSchedule) {
                    edStatus += "Edit successful!";
                    connection.commit();
                } else {
                    ConnectionUtils.rollbackQuietly(connection);
                    edStatus += getEditStatus(updateByTrainId, updateDepStation, updateArrStation, updateSchedule);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
            edStatus += e.getMessage();
        }

        resp.sendRedirect(req.getContextPath() + "/adminPage?editRouteStatus=" + edStatus);
    }

    private String getEditStatus(boolean updateByTrainId, boolean updateDepStation,
                                 boolean updateArrStation, boolean updateSchedule) {
        StringBuilder retStr = new StringBuilder("Incorrect ");

        if (!updateByTrainId) {
            retStr.append("train id, ");
        }
        if (!updateDepStation) {
            retStr.append("departure station, ");
        }
        if (!updateArrStation) {
            retStr.append("arrival station, ");
        }
        if (!updateSchedule) {
            retStr.append("date, ");
        }

        retStr.append("enter the correct values!");

        return retStr.toString();
    }
}
