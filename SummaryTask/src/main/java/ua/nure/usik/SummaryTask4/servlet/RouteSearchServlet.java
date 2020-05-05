package ua.nure.usik.SummaryTask4.servlet;

import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.db.constats.Fields;
import ua.nure.usik.SummaryTask4.db.entity.*;
import ua.nure.usik.SummaryTask4.utils.RouteUtils;
import ua.nure.usik.SummaryTask4.utils.constants.RouteType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/routeSearch")
public class RouteSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");

        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = MyUtils.getStoredLanguage(req);

        if (language == null) {
            language = "en";
        }

        ResourceBundle bundle = ResourceBundle.getBundle("warnings", new Locale(language));

        Connection connection = MyUtils.getStoredConnection(req);

        String dep_station = req.getParameter("dep_station");
        String arr_station = req.getParameter("arr_station");
        String date = req.getParameter("date");

        Map<String, Entity> map;
        String errorString = "";

        try {
            map = RouteUtils.convertIdToEntity(connection,
                    RouteUtils.getRoutesByStation(connection, dep_station, arr_station, date, language),
                    dep_station, arr_station, language);
        } catch (SQLException e) {
            errorString = bundle.getString("error.query");
            req.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = req.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/routeListView.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        if (map.isEmpty()) {
            errorString = bundle.getString("not_found.route");
            req.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = req.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/routeListView.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        req.setAttribute("errorString", errorString);
        req.setAttribute("routeMap", map);

        try {
            MyUtils.storeDepartureFoundStation(req.getSession(),
                    language.equals("en") ? DBManager.findStationByName(connection, dep_station) :
                            DBManager.findStationByNameRu(connection, dep_station));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            MyUtils.storeArrivalFoundStation(req.getSession(),
                    language.equals("en") ? DBManager.findStationByName(connection, arr_station) :
                            DBManager.findStationByNameRu(connection, arr_station));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        User user = MyUtils.getLoginedUser(req.getSession());

        if (user != null) {
            req.setAttribute("user", user);
        }

        RequestDispatcher dispatcher = req.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/routeListView.jsp");
        dispatcher.forward(req, resp);
    }
}
