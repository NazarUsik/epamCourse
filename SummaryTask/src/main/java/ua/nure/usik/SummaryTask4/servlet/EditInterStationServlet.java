package ua.nure.usik.SummaryTask4.servlet;

import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.ConnectionUtils;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.db.entity.IntermediateStation;
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
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/editIntermediateStation")
public class EditInterStationServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/adminPage");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection connection = MyUtils.getStoredConnection(req);

        String edStatus = "";

        int routeId = Integer.parseInt(req.getParameter("route_id"));
        int stationId = Integer.parseInt(req.getParameter("station_id"));
        long trvTime = Long.parseLong(req.getParameter("trv_time"));
        String stationName = req.getParameter("station_name");

        LocalDateTime depTime = null;
        LocalDateTime arrTime = null;

        try {
            depTime = LocalDateTime.parse(req.getParameter("dep_time").replace(' ', 'T'));
            arrTime = LocalDateTime.parse(req.getParameter("arr_time").replace(' ', 'T'));
        } catch (DateTimeParseException ex) {
            edStatus += "Incorrect date!";
            resp.sendRedirect(req.getContextPath() + "/adminPage?editIntStatus=" + edStatus);
            return;
        }

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            IntermediateStation intermediateStation = DBManager.findIntermediateStation(connection, routeId, stationId);

            if (intermediateStation != null) {
                boolean updateStation = false;

                Station station;

                if ((station = DBManager.findStationByName(connection, stationName)) != null) {
                    updateStation = DBManager.updateIntermediateStationByStation
                            (connection, routeId, station.getId(), intermediateStation.getScheduleId());
                }


                boolean updateSchedule = DBManager.updateSchedule(connection, intermediateStation.getScheduleId(),
                        depTime.toString(), arrTime.toString(), trvTime);

                if (updateStation && updateSchedule) {
                    edStatus += "Edit successful!";
                    connection.commit();
                } else {
                    ConnectionUtils.rollbackQuietly(connection);
                    edStatus += getEditStatus(updateStation, updateSchedule);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
            edStatus += e.getMessage();
        }

        resp.sendRedirect(req.getContextPath() + "/adminPage?editIntStatus=" + edStatus);
    }

    private String getEditStatus(boolean updateStation, boolean updateSchedule) {
        StringBuilder retStr = new StringBuilder("Incorrect ");

        if (!updateStation) {
            retStr.append("departure station, ");
        }
        if (!updateSchedule) {
            retStr.append("date, ");
        }

        retStr.append("enter the correct values!");

        return retStr.toString();
    }

}
