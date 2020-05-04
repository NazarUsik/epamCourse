package ua.nure.usik.SummaryTask4.servlet;

import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.db.entity.Schedule;
import ua.nure.usik.SummaryTask4.db.entity.Station;
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
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/addIntermediateStation")
public class AddIntermediateStationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect(request.getContextPath() + "/adminPage");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(request);

        request.setCharacterEncoding("UTF-8");

        String addInterStation = "";

        int routeId = Integer.parseInt(request.getParameter("routeId"));
        int travelTime = Integer.parseInt(request.getParameter("travelTime"));
        String stationName = request.getParameter("stationName");

        LocalDateTime depTime = LocalDateTime.parse(request.getParameter("depTime"));
        LocalDateTime arrTime = LocalDateTime.parse(request.getParameter("arrTime"));


        String language = MyUtils.getStoredLanguage(request);

        if (language == null) {
            language = "en";
        }

        if (language.equals("ru")) {
            stationName = TranslatorUtils.translate(Language.RUSSIAN, Language.ENGLISH, stationName);
        }

        ResourceBundle bundle = ResourceBundle.getBundle("warnings", new Locale(language));

        try {
            Station station = DBManager.findStationByName(connection, stationName);

            if (DBManager.availableRoute(connection, routeId) && station != null) {
                DBManager.insertSchedule(connection, new Schedule(depTime.toString(), arrTime.toString(), travelTime));

                int scheduleId = DBManager.getLastScheduleId(connection);

                if (DBManager.insertIntermediateStation(connection, routeId, station.getId(), scheduleId)) {
                    addInterStation += bundle.getString("add.successful");
                } else {
                    addInterStation += bundle.getString("add.error");
                }


            }
        } catch (SQLException e) {
            addInterStation += bundle.getString("add.error.inter_stat");

        }

        request.getSession().setAttribute("addInterStationStatus", addInterStation);
        response.sendRedirect(request.getContextPath() + "/adminPage");
    }
}
