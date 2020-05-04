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
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@WebServlet("/editCarriage")
public class EditCarriageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/adminPage");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(req);

        req.setCharacterEncoding("UTF-8");

        int carriageId = Integer.parseInt(req.getParameter("carriageId"));
        int trainId = Integer.parseInt(req.getParameter("trainId"));

        String editStatus = "";

        System.out.println(Arrays.toString(req.getParameterValues("carriageId")));
        System.out.println(Arrays.toString(req.getParameterValues("trainId")));

        String language = MyUtils.getStoredLanguage(req);

        if (language == null) {
            language = "en";
        }

        ResourceBundle bundle = ResourceBundle.getBundle("warnings", new Locale(language));

        try {
            if (DBManager.updateTrainCompositionByCarriage(connection, trainId, carriageId)) {
                editStatus += bundle.getString("edit.successful");
            } else {
                bundle.getString("edit.incorrect.value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            editStatus += bundle.getString("edit.error");
        }

        req.getSession().setAttribute("editCarriageStatus", editStatus);
        resp.sendRedirect(req.getContextPath() + "/adminPage");
    }

}
