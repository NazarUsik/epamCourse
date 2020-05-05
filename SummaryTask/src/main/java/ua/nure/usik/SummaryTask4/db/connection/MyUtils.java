package ua.nure.usik.SummaryTask4.db.connection;

import com.sun.deploy.net.HttpResponse;
import ua.nure.usik.SummaryTask4.db.entity.Station;
import ua.nure.usik.SummaryTask4.db.entity.User;

import javax.servlet.ServletRequest;
import javax.servlet.http.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class MyUtils {
    private static int REDIRECT_ID = 0;

    public static final String ATT_NAME_CONNECTION = "connection";

    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

    private static final String ATT_NAME_LANG = "lang";

    private static final String ATT_NAME_DEP_STATION = "dep_station";
    private static final String ATT_NAME_ARR_STATION = "arr_station";


    private static final Map<Integer, String> id_uri_map = new HashMap<Integer, String>();

    private static final Map<String, Integer> uri_id_map = new HashMap<String, Integer>();

    // Сохранить Connection в attribute в request.
    // Данная информация хранения существует только во время запроса (request)
    // до тех пор, пока данные возвращаются приложению пользователя.
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }

    // Получить объект Connection сохраненный в attribute в request.
    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return conn;
    }

    public static void storeLanguage(HttpServletResponse response, String language) {
//        session.setAttribute(ATT_NAME_LANG, language);
        Cookie cookie = new Cookie(ATT_NAME_LANG, language);
        // 7 дней (Конвертированные в секунды)
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
    }

    public static String getStoredLanguage(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_LANG.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


    public static void storeDepartureFoundStation(HttpSession session, Station station) {
        session.setAttribute(ATT_NAME_DEP_STATION, station);
    }

    public static Station getStoredDepartureFoundStation(HttpSession session) {
        return (Station) session.getAttribute(ATT_NAME_DEP_STATION);
    }

    public static void storeArrivalFoundStation(HttpSession session, Station station) {
        session.setAttribute(ATT_NAME_ARR_STATION, station);
    }

    public static Station getStoredArrivalFoundStation(HttpSession session) {
        return (Station) session.getAttribute(ATT_NAME_ARR_STATION);
    }

    // Сохранить информацию пользователя, который вошел в систему (login) в Session.
    public static void storeLoginedUser(HttpSession session, User loginedUser) {
        // В JSP можно получить доступ через ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }

    // Получить информацию пользователя, сохраненная в Session.
    public static User getLoginedUser(HttpSession session) {
        return (User) session.getAttribute("loginedUser");
    }

    // Сохранить информацию пользователя в Cookie.
    public static void storeUserCookie(HttpServletResponse response, User user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getEmail());
        // 7 дней (Конвертированные в секунды)
        cookieUserName.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookieUserName);
    }

    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    // Удалить Cookie пользователя
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        // 0 секунд. (Данный Cookie будет сразу недействителен)
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }

    public static int storeRedirectAfterLoginUrl(HttpSession session, String requestUri) {
        Integer id = uri_id_map.get(requestUri);

        if (id == null) {
            id = REDIRECT_ID++;

            uri_id_map.put(requestUri, id);
            id_uri_map.put(id, requestUri);
            return id;
        }

        return id;
    }

    public static String getRedirectAfterLoginUrl(HttpSession session, int redirectId) {
        return id_uri_map.get(redirectId);
    }
}
