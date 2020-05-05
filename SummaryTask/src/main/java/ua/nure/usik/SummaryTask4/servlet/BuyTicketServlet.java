package ua.nure.usik.SummaryTask4.servlet;

import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.ConnectionUtils;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.db.entity.Carriage;
import ua.nure.usik.SummaryTask4.db.entity.Ticket;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@WebServlet("/buyTicket")
public class BuyTicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/routeListView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(request);

        float price = Float.parseFloat(request.getParameter("price"));
        String carrType = request.getParameter("carr_type");
        int routeId = Integer.parseInt(request.getParameter("routeId"));

        User loginedUser = MyUtils.getLoginedUser(request.getSession());


        int ticketId = 0;
        try {
            ticketId = DBManager.getTicketIdByRoute(connection, routeId, carrType);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (ticketId == 0) {
            //error
            System.out.println("Error");
            return;
        }

        int coefficient = carrType.equals("lux") || carrType.equals("Люкс") ? 3 :
                carrType.equals("coupe") || carrType.equals("Купе") ? 2 : 1;

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (DBManager.updateTicket(connection, ticketId, price * coefficient)) {
                if (DBManager.insertTicketsList(connection, loginedUser.getId(), ticketId, 1)) {
                    connection.commit();
                    Ticket ticket = DBManager.getTicketById(connection, ticketId);
                    if (DBManager.updateSeat(connection, ticket.getSeatId(), false)) {
                        Carriage carriage = DBManager.getCarriage(connection, ticket.getCarriageId());
                        DBManager.updateCarriage(connection, ticket.getCarriageId(),
                                carriage.getCountAvailableSeats() - 1);
                        System.out.println("commit");
                    } else {
                        ConnectionUtils.rollbackQuietly(connection);
                    }
                } else {
                    ConnectionUtils.rollbackQuietly(connection);
                }
            } else {
                ConnectionUtils.rollbackQuietly(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/routePage?route_id=" + routeId);
    }
}
