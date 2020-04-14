package ua.nure.usik.SummaryTask4.servlet;

import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet("/editStation")
public class EditStationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/adminPage");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(req);

        int stationId = Integer.parseInt(req.getParameter("stationId"));
        String stationName = req.getParameter("stationName");
        String editStatus = "";

        try {
            if (DBManager.updateStation(connection, stationId, stationName)) {
                editStatus += "Update successful!";
            } else {
                editStatus += "Station not found!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            editStatus += e.getMessage();
        }

        resp.sendRedirect(req.getContextPath() + "/adminPage?editStationStatus=" + editStatus);

    }
}
