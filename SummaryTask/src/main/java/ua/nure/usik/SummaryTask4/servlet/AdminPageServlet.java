package ua.nure.usik.SummaryTask4.servlet;

import javafx.util.Pair;
import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.db.entity.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@WebServlet("/adminPage")
public class AdminPageServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(request);

        List<Pair<String, Train>> trains = null;
        List<Pair<Integer, Pair<String, Carriage>>> carriages = null;
        String errorTC = "";

        List<Station> stationList = null;
        Map<String, Entity> routeInfoMap = null;
        Map<String, Entity> interStationMap = null;
        String errorSR = "";

        Map<Pair<Integer, Float>, Pair<Integer, Integer>> ticketInfoMap = null;
        String errorTK = "";

        List<Pair<User, String>> usersInfoList = null;
        String errorUs = "";

        String lang = MyUtils.getStoredLanguage(request);

        if (lang == null) {
            lang = "en";
        }

        ResourceBundle bundle = ResourceBundle.getBundle("warnings", new Locale(lang));

        try {
            trains = DBManager.getAllTrain(connection, lang);
        } catch (SQLException e) {
            errorTC += bundle.getString("view.error.train");
        }

        try {
            carriages = DBManager.getAllCarriage(connection, lang);
        } catch (SQLException e) {
            errorTC +=  bundle.getString("view.error.carriage");;
        }

        try {
            routeInfoMap = DBManager.getAllRoute(connection);
        } catch (SQLException e) {
            errorSR +=  bundle.getString("view.error.route");;
        }

        try {
            stationList = DBManager.getAllStation(connection);
        } catch (SQLException e) {
            errorSR +=  bundle.getString("view.error.station");;
        }

        try {
            interStationMap = DBManager.getAllIntermediateStation(connection);
        } catch (SQLException e) {
            errorSR +=  bundle.getString("view.error.inter_stat");
        }

        try {
            ticketInfoMap = DBManager.getAllTickets(connection);
        } catch (SQLException e) {
            errorTK +=  bundle.getString("view.error.tick");
        }

        try {
            usersInfoList = DBManager.getAllUsers(connection);
        } catch (SQLException e) {
            errorUs +=  bundle.getString("view.error.users");;
        }

        request.setAttribute("trains", trains);
        request.setAttribute("carriages", carriages);
        request.setAttribute("errorTC", errorTC);

        request.setAttribute("routeInfoMap", routeInfoMap);
        request.setAttribute("interStationMap", interStationMap);
        request.setAttribute("stationList", stationList);
        request.setAttribute("errorSR", errorSR);

        request.setAttribute("ticketInfoMap", ticketInfoMap);
        request.setAttribute("errorTK", errorTK);

        request.setAttribute("usersInfoList", usersInfoList);
        request.setAttribute("errorUs", errorUs);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/adminPageView.jsp");

        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
