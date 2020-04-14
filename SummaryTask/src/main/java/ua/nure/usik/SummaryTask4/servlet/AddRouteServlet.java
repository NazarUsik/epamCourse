package ua.nure.usik.SummaryTask4.servlet;

import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.ConnectionUtils;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.db.entity.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

@WebServlet("/addRoute")
public class AddRouteServlet extends HttpServlet {

    public AddRouteServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.sendRedirect(request.getContextPath() + "/adminPage");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Connection connection = MyUtils.getStoredConnection(request);

        String addRoute = "";

        int trainId = Integer.parseInt(request.getParameter("trainId"));
        int price = Integer.parseInt(request.getParameter("price"));
        String depStationName = request.getParameter("depStation");
        String arrStationName = request.getParameter("arrStation");

        LocalDateTime depTime = LocalDateTime.parse(request.getParameter("depTime"));
        LocalDateTime arrTime = LocalDateTime.parse(request.getParameter("arrTime"));

        Duration duration = Duration.between(depTime, arrTime);


        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Station depStation = DBManager.findStationByName(connection, depStationName);
            Station arrStation = DBManager.findStationByName(connection, arrStationName);
            DBManager.insertSchedule(connection, new Schedule(depTime.toString(), arrTime.toString(), (int) duration.toMinutes()));

            int scheduleId = DBManager.getLastScheduleId(connection);

            if (depStation != null && arrStation != null && scheduleId != 0) {

                if (DBManager.insertRoute(connection,
                        new Route(trainId, depStation.getId(), arrStation.getId(), scheduleId))) {

                    Map<Seats, Carriage> map = DBManager.findCarriagesSeatsByTrainId(connection, trainId);
                    int routeId = DBManager.getLastRouteId(connection);

                    for (Map.Entry<Seats, Carriage> entry : map.entrySet()) {
                        if (!DBManager.insertTicket(connection, new Ticket(routeId, price, entry.getValue().getId(),
                                entry.getKey().getId()))) {
                            ConnectionUtils.rollbackQuietly(connection);
                        }
                    }

                    addRoute += "Add successful";
                } else {
                    addRoute += "Error in query!";
                }
            } else {
                addRoute += "Station not fount!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            addRoute += e.getMessage();
        }

        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/adminPage?addRouteStatus=" + addRoute);


    }
}
