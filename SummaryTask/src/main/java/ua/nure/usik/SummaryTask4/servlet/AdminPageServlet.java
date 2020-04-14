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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

        try {
            trains = DBManager.getAllTrain(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            errorTC += e.getMessage();
        }

        try {
            carriages = DBManager.getAllCarriage(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            errorTC += e.getMessage();
        }

        try {
            routeInfoMap = DBManager.getAllRoute(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            errorSR += e.getMessage();
        }

        try {
            stationList = DBManager.getAllStation(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            errorSR += e.getMessage();
        }

        try {
            interStationMap = DBManager.getAllIntermediateStation(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            errorSR += e.getMessage();
        }

        try {
            ticketInfoMap = DBManager.getAllTickets(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            errorTK += e.getMessage();
        }

        try {
            usersInfoList = DBManager.getAllUsers(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            errorUs += e.getMessage();
        }

//        System.out.println(Arrays.toString(trains.toArray()));
//        System.out.println(Arrays.toString(carriages.toArray()));

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
