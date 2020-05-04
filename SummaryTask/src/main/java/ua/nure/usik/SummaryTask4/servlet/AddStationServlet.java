package ua.nure.usik.SummaryTask4.servlet;

import javafx.util.Pair;
import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.ConnectionUtils;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.db.entity.*;
import ua.nure.usik.SummaryTask4.db.entity.enums.CarriageType;
import ua.nure.usik.SummaryTask4.db.entity.enums.TrainType;
import ua.nure.usik.SummaryTask4.utils.TranslatorUtils;
import ua.nure.usik.SummaryTask4.utils.constants.Language;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/addStation")
public class AddStationServlet extends HttpServlet {
    public AddStationServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(request.getContextPath() + "/adminPage");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection connection = MyUtils.getStoredConnection(request);

        String stationName = request.getParameter("stationName");
        String stationNameRu;
        String addStation = "";

        String language = MyUtils.getStoredLanguage(request);

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
            if (DBManager.insertStation(connection, stationName, stationNameRu)) {
                addStation +=  bundle.getString("add.successful");
            } else {
                addStation +=  bundle.getString("add.error");
            }
        } catch (SQLException e) {
            addStation += bundle.getString("add.error.station");
        }


        request.getSession().setAttribute("addStationStatus", addStation);
        response.sendRedirect(request.getContextPath() + "/adminPage");
    }
}
