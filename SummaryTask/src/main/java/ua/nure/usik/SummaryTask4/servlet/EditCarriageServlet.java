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
import java.util.Map;

@WebServlet("/editCarriage")
public class EditCarriageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/adminPage");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(req);

        int carriageId = Integer.parseInt(req.getParameter("carriageId"));
        int trainId = Integer.parseInt(req.getParameter("trainId"));

        String editStatus = "";

        System.out.println(Arrays.toString(req.getParameterValues("carriageId")));
        System.out.println(Arrays.toString(req.getParameterValues("trainId")));

        try {
            if (DBManager.updateTrainCompositionByCarriage(connection, trainId, carriageId)) {
                editStatus += "Edit successful!";
            } else {
                editStatus += "Incorrect values indicated!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            editStatus += e.getMessage();
        }

        resp.sendRedirect(req.getContextPath() + "/adminPage?editCarriageStatus=" + editStatus);
    }

}
