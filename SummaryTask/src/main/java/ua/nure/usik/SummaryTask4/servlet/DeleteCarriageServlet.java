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

@WebServlet("/deleteCarriage")
public class DeleteCarriageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/adminPage");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(req);

        int carriageId = Integer.parseInt(req.getParameter("carriageId"));
        String delStatus = "";

        try {
            if (DBManager.deleteCarriage(connection, carriageId)) {
                delStatus += "Delete successful!";
            } else {
                delStatus += "Station not found!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            delStatus += e.getMessage();
        }

        resp.sendRedirect(req.getContextPath() + "/adminPage?delCarriageStatus=" + delStatus);

    }
}
