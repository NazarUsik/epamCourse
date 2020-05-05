package ua.nure.usik.SummaryTask4.servlet;

import javafx.util.Pair;
import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.db.entity.IntermediateStation;
import ua.nure.usik.SummaryTask4.db.entity.Route;
import ua.nure.usik.SummaryTask4.db.entity.Schedule;
import ua.nure.usik.SummaryTask4.db.entity.Station;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@WebServlet("/routePage")
public class RoutePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(req);
        String language = MyUtils.getStoredLanguage(req);

        int route_id = Integer.parseInt(req.getParameter("route_id"));

        Route route = null;

        try {
            route = DBManager.findRoute(connection, route_id);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }

        if (route == null || MyUtils.getStoredDepartureFoundStation(req.getSession()) == null ||
                MyUtils.getStoredArrivalFoundStation(req.getSession()) == null) {
            RequestDispatcher dispatcher = req.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");
            dispatcher.forward(req, resp);
            return;
        }


        List<IntermediateStation> intermediateStations = null;

        try {
            intermediateStations = DBManager.getIntermediateStationsByRoute(connection, route_id);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }

        Map<String, Integer> trainInfo = null;
        try {
            trainInfo = DBManager.getTrainInfo(connection, route.getTrainId(), language);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("trainInfo", trainInfo);
        req.setAttribute("route", route);
        try {
            req.setAttribute("listInterStation",
                    convertIntermediateStationIdToEntity(connection, intermediateStations));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        RequestDispatcher dispatcher = req.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/routePageView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private static List<Pair<Station, Schedule>>
    convertIntermediateStationIdToEntity(Connection connection, List<IntermediateStation> intermediateStationList)
            throws SQLException {
        List<Pair<Station, Schedule>> list = new LinkedList<>();


        for (IntermediateStation ins : intermediateStationList) {
            list.add(new Pair<>(DBManager.getStationById(connection, ins.getStationId()),
                    DBManager.getScheduleById(connection, ins.getScheduleId())));
        }

        return list;
    }
}
