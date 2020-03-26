package ua.nure.usik.SummaryTask4.servlet;

import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.db.entity.Entity;
import ua.nure.usik.SummaryTask4.db.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/routeSearch")
public class RouteSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/routeSearchView.jsp");

        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(req);

        String stationFromName = req.getParameter("stationFrom");
        String stationToName = req.getParameter("stationTo");
        String startDate = req.getParameter("startDate");

        Map<String, Entity> map = null;
        String errorString = null;

        try{
            map = DBManager.findRouteByStation(connection, stationFromName, stationToName, startDate);
        } catch (SQLException e){
            errorString = e.getMessage();
        }

        req.setAttribute("errorString", errorString);
        req.setAttribute("routeList", map);


        User user = MyUtils.getLoginedUser(req.getSession());

        if(user != null){
            req.setAttribute("user", user);
        }

        RequestDispatcher dispatcher = req.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/routeListView.jsp");
        dispatcher.forward(req, resp);
    }
}
