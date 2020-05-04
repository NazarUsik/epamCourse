package ua.nure.usik.SummaryTask4.servlet;

import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.utils.TranslatorUtils;
import ua.nure.usik.SummaryTask4.utils.constants.Language;

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
import java.util.ResourceBundle;

@WebServlet("/editStation")
public class EditStationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/adminPage");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(req);

        req.setCharacterEncoding("UTF-8");


        int stationId = Integer.parseInt(req.getParameter("stationId"));
        String stationName = req.getParameter("stationName");
        String stationNameRu;
        String editStatus = "";

        String language = MyUtils.getStoredLanguage(req);

        if (language == null) {
            language = "en";
        }

        if (language.equals("ru")) {
            stationNameRu = stationName;
            stationName = TranslatorUtils.translate(Language.RUSSIAN, Language.ENGLISH, stationName);
        } else {
            stationNameRu = TranslatorUtils.translate(Language.ENGLISH, Language.RUSSIAN, stationName);
        }

        ResourceBundle bundle = ResourceBundle.getBundle("warnings", new Locale(language));

        try {
            if (DBManager.updateStation(connection, stationId, stationName, stationNameRu)) {
                editStatus += bundle.getString("edit.successful");
            } else {
                editStatus += bundle.getString("edit.incorrect.value");
            }
        } catch (SQLException e) {
            editStatus += bundle.getString("edit.error");
        }

        req.getSession().setAttribute("editStationStatus", editStatus);
        resp.sendRedirect(req.getContextPath() + "/adminPage");

    }
}
