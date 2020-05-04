package ua.nure.usik.SummaryTask4.servlet;

import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.ConnectionUtils;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
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
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@WebServlet("/editRoute")
public class EditRouteServlet extends HttpServlet {

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
        int trainId = Integer.parseInt(req.getParameter("train_id"));
        String stationDep = req.getParameter("station_dep");
        String stationArr = req.getParameter("station_arr");

        LocalDateTime depTime = null;
        LocalDateTime arrTime = null;

        String language = MyUtils.getStoredLanguage(req);

        if (language == null) {
            language = "en";
        }

//        if (language.equals("ru")) {
//            stationDep = TranslatorUtils.translate(Language.RUSSIAN, Language.ENGLISH, stationDep);
//            stationArr = TranslatorUtils.translate(Language.RUSSIAN, Language.ENGLISH, stationArr);
//        }

        ResourceBundle bundle = ResourceBundle.getBundle("warnings", new Locale(language));

        try {
            depTime = LocalDateTime.parse(req.getParameter("dep_time").replace(' ', 'T'));
            arrTime = LocalDateTime.parse(req.getParameter("arr_time").replace(' ', 'T'));
        } catch (DateTimeParseException ex) {
            edStatus += bundle.getString("edit.incorrect.date");
            req.getSession().setAttribute("editRouteStatus", edStatus);
            resp.sendRedirect(req.getContextPath() + "/adminPage");
            return;
        }

        Duration duration = Duration.between(depTime, arrTime);

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }

        try {
            Route route = DBManager.findRoute(connection, routeId);

            if (route != null) {
                boolean updateByTrainId = DBManager.updateRouteByTrainId(connection, routeId, trainId);
                boolean updateDepStation = false;

                Station station = language.equals("en") ? DBManager.findStationByName(connection, stationDep) :
                        DBManager.findStationByNameRu(connection, stationDep);

                if (station != null) {
                    updateDepStation = DBManager.updateDepartureStationInRoute
                            (connection, route.getDepartureId(), station.getId());
                }

                boolean updateArrStation = false;

                station = language.equals("en") ? DBManager.findStationByName(connection, stationArr) :
                        DBManager.findStationByNameRu(connection, stationArr);

                if (station != null) {
                    updateArrStation = DBManager.updateArrivalStationInRoute
                            (connection, route.getDepartureId(), station.getId());
                }

                boolean updateSchedule = DBManager.updateSchedule(connection, route.getScheduleId(),
                        depTime.toString(), arrTime.toString(), duration.toMinutes());

                if (updateByTrainId && updateDepStation && updateArrStation && updateSchedule) {
                    edStatus += bundle.getString("edit.successful");
                    connection.commit();
                } else {
                    ConnectionUtils.rollbackQuietly(connection);
                    edStatus += getEditStatus(updateByTrainId, updateDepStation, updateArrStation,
                            updateSchedule, bundle);
                }

            }

        } catch (SQLException e) {
            edStatus += bundle.getString("edit.error");
        }

        req.getSession().setAttribute("editRouteStatus", edStatus);
        resp.sendRedirect(req.getContextPath() + "/adminPage");
    }

    private String getEditStatus(boolean updateByTrainId, boolean updateDepStation,
                                 boolean updateArrStation, boolean updateSchedule, ResourceBundle bundle) {
        StringBuilder retStr = new StringBuilder(bundle.getString("edit.incorrect"));

        if (!updateByTrainId) {
            retStr.append(bundle.getString("edit.incorrect.rout.train"));
        }
        if (!updateDepStation) {
            retStr.append(bundle.getString("edit.incorrect.rout.dep_stat"));
        }
        if (!updateArrStation) {
            retStr.append(bundle.getString("edit.incorrect.rout.arr_stat"));
        }
        if (!updateSchedule) {
            retStr.append(bundle.getString("edit.incorrect.rout.date"));
        }

        retStr.append(bundle.getString("edit.incorrect.rout.enter"));

        return retStr.toString();
    }
}
