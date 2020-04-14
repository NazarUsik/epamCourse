package ua.nure.usik.SummaryTask4.servlet;

import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.ConnectionUtils;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/editTickets")
public class EditTicketsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/adminPage");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection connection = MyUtils.getStoredConnection(req);

        String edStatus = "";

        int routeId = Integer.parseInt(req.getParameter("route_id"));
        float price = Float.parseFloat(req.getParameter("price"));

        List<Integer> ticketsId = null;

        try {
            ticketsId = DBManager.getAllTicketIdByRoute(connection, routeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (ticketsId != null) {
            try {

                for (int id : ticketsId) {

                    if (!DBManager.updateTicket(connection, id, price)) {
                        ConnectionUtils.rollbackQuietly(connection);
                        edStatus = "Price not change!";
                    } else {
                        edStatus = "Edit successful!";
                    }
                }

                connection.commit();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            edStatus += "Error route_id!";
        }

        resp.sendRedirect(req.getContextPath() + "/adminPage?editTicketsStatus=" + edStatus);
    }

}
