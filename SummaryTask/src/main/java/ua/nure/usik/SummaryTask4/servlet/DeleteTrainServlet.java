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
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/deleteTrain")
public class DeleteTrainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/adminPage");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(request);

        request.setCharacterEncoding("UTF-8");

        int trainId = Integer.parseInt(request.getParameter("trainId"));
        String delStatus = "";

        String language = MyUtils.getStoredLanguage(request);

        if (language == null) {
            language = "en";
        }

        ResourceBundle bundle = ResourceBundle.getBundle("warnings", new Locale(language));

        try {
            if (DBManager.deleteTrain(connection, trainId)) {
                delStatus += bundle.getString("del.successful");
            } else {
                delStatus += bundle.getString("del.not");
            }
        } catch (SQLException e) {
            delStatus += bundle.getString("del.error");
        }

        request.getSession().setAttribute("delTrainStatus", delStatus);
        response.sendRedirect(request.getContextPath() + "/adminPage");

    }
}
