package ua.nure.usik.SummaryTask4.servlet;

import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.ConnectionUtils;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.db.entity.IntermediateStation;
import ua.nure.usik.SummaryTask4.db.entity.Route;
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
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@WebServlet("/editIntermediateStation")
public class EditInterStationServlet extends HttpServlet {


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
        int stationId = Integer.parseInt(req.getParameter("station_id"));
        long trvTime = Long.parseLong(req.getParameter("trv_time"));
        String stationName = req.getParameter("station_name");

        LocalDateTime depTime;
        LocalDateTime arrTime;

        String language = MyUtils.getStoredLanguage(req);

        if (language == null) {
            language = "en";
        }

//        if (language.equals("ru")) {
//            stationName = TranslatorUtils.translate(Language.RUSSIAN, Language.ENGLISH, stationName);
//        }

        ResourceBundle bundle = ResourceBundle.getBundle("warnings", new Locale(language));

        try {
            depTime = LocalDateTime.parse(req.getParameter("dep_time").replace(' ', 'T'));
            arrTime = LocalDateTime.parse(req.getParameter("arr_time").replace(' ', 'T'));
        } catch (DateTimeParseException ex) {
            edStatus += bundle.getString("edit.incorrect.date");
            req.getSession().setAttribute("editIntStatus", edStatus);
            resp.sendRedirect(req.getContextPath() + "/adminPage");
            return;
        }

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }

        try {
            IntermediateStation intermediateStation = DBManager.findIntermediateStation(connection, routeId, stationId);

            if (intermediateStation != null) {
                boolean updateStation = false;

                Station station = language.equals("en") ? DBManager.findStationByName(connection, stationName) :
                        DBManager.findStationByNameRu(connection, stationName);

                if (station != null) {
                    updateStation = DBManager.updateIntermediateStationByStation
                            (connection, routeId, station.getId(), intermediateStation.getScheduleId());
                }


                boolean updateSchedule = DBManager.updateSchedule(connection, intermediateStation.getScheduleId(),
                        depTime.toString(), arrTime.toString(), trvTime);

                if (updateStation && updateSchedule) {
                    edStatus += bundle.getString("edit.successful");
                    connection.commit();
                } else {
                    ConnectionUtils.rollbackQuietly(connection);
                    edStatus += getEditStatus(updateStation, updateSchedule, bundle);
                }

            }

        } catch (SQLException e) {
            edStatus += e.getMessage();
        }

        req.getSession().setAttribute("editIntStatus", edStatus);
        resp.sendRedirect(req.getContextPath() + "/adminPage");
    }

    private String getEditStatus(boolean updateStation, boolean updateSchedule, ResourceBundle bundle) {
        StringBuilder retStr = new StringBuilder(bundle.getString("edit.incorrect"));

        if (!updateStation) {
            retStr.append(bundle.getString("edit.incorrect.rout.stat"));
        }
        if (!updateSchedule) {
            retStr.append(bundle.getString("edit.incorrect.rout.value"));
        }

        retStr.append(bundle.getString("edit.incorrect.rout.enter"));

        return retStr.toString();
    }

}
