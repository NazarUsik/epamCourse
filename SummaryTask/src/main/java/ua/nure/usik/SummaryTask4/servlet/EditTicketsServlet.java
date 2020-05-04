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
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/editTickets")
public class EditTicketsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/adminPage");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection connection = MyUtils.getStoredConnection(req);

        req.setCharacterEncoding("UTF-8");

        String edStatus = "";

        int routeId = Integer.parseInt(req.getParameter("route_id"));
        float price = Float.parseFloat(req.getParameter("price"));

        List<Integer> ticketsId = null;

        String language = MyUtils.getStoredLanguage(req);

        if (language == null) {
            language = "en";
        }

        try {
            ticketsId = DBManager.getAllTicketIdByRoute(connection, routeId);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }

        ResourceBundle bundle = ResourceBundle.getBundle("warnings", new Locale(language));

        if (ticketsId != null) {
            try {

                for (int id : ticketsId) {

                    if (!DBManager.updateTicket(connection, id, price)) {
                        ConnectionUtils.rollbackQuietly(connection);
                        edStatus = bundle.getString("edit.incorrect.value");
                    } else {
                        edStatus = bundle.getString("edit.successful");
                    }
                }

                connection.commit();

            } catch (SQLException e) {
                System.out.println(e.getSQLState());
            }
        } else {
            edStatus += bundle.getString("edit.error");
        }

        req.getSession().setAttribute("editTicketsStatus", edStatus);
        resp.sendRedirect(req.getContextPath() + "/adminPage");
    }

}
