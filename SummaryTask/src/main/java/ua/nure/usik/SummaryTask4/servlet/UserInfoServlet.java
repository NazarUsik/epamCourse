package ua.nure.usik.SummaryTask4.servlet;

import javafx.util.Pair;
import sun.security.krb5.internal.PAData;
import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.db.entity.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/userInfo")
public class UserInfoServlet extends HttpServlet {
    public UserInfoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(request);

        User user = MyUtils.getLoginedUser(request.getSession());

        List<Ticket> ticketsByUser = null;
        try {
            ticketsByUser = DBManager.getTicketsByUser(connection, user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Pair<Pair<Station, Station>, Pair<Schedule, Float>>> list = new LinkedList<>();

        try {
            for (Ticket t : ticketsByUser) {
                Route route = DBManager.findRoute(connection, t.getRoutId());
                Station dep = DBManager.getStationById(connection, route.getDepartureId());
                Station arr = DBManager.getStationById(connection, route.getArrivalId());
                Schedule schedule = DBManager.getScheduleById(connection, route.getScheduleId());

                list.add(new Pair<>(new Pair<>(dep, arr), new Pair<>(schedule, t.getPrice())));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("ticketsInfo", list);
        request.setAttribute("user", user);

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
