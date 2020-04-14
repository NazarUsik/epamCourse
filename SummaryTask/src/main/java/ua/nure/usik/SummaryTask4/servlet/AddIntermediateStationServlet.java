package ua.nure.usik.SummaryTask4.servlet;

import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.db.entity.Schedule;
import ua.nure.usik.SummaryTask4.db.entity.Station;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

@WebServlet("/addIntermediateStation")
public class AddIntermediateStationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect(request.getContextPath() + "/adminPage");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(request);

        String addInterStation = "";

        int routeId = Integer.parseInt(request.getParameter("routeId"));
        int travelTime = Integer.parseInt(request.getParameter("travelTime"));
        String stationName = request.getParameter("stationName");

        LocalDateTime depTime = LocalDateTime.parse(request.getParameter("depTime"));
        LocalDateTime arrTime = LocalDateTime.parse(request.getParameter("arrTime"));

        try {
            Station station = DBManager.findStationByName(connection, stationName);

            if (DBManager.availableRoute(connection, routeId) && station != null) {
                DBManager.insertSchedule(connection, new Schedule(depTime.toString(), arrTime.toString(), travelTime));

                int scheduleId = DBManager.getLastScheduleId(connection);

                if (DBManager.insertIntermediateStation(connection, routeId, station.getId(), scheduleId)) {
                    addInterStation += "Add successful";
                } else {
                    addInterStation += "Not add!";
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
            addInterStation += e.getMessage();
        }

        response.sendRedirect(request.getContextPath() + "/adminPage?addInterStationStatus=" + addInterStation);
    }
}
