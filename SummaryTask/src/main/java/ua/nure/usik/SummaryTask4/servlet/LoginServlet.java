package ua.nure.usik.SummaryTask4.servlet;

import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.db.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = MyUtils.getStoredLanguage(req);

        if (language == null) {
            language = "en";
        }

        ResourceBundle bundle = ResourceBundle.getBundle("warnings", new Locale(language));

        req.setAttribute("errorString", bundle.getString("login.first"));
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");

        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        boolean remember = "Y".equals(req.getParameter("rememberMe"));

        User user = null;
        boolean hasError = false;
        String errorString = null;

        String language = MyUtils.getStoredLanguage(req);

        if (language == null) {
            language = "en";
        }

        ResourceBundle bundle = ResourceBundle.getBundle("warnings", new Locale(language));

        if (email == null || email.length() < 4
            /*|| !email.matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)" +
                "*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")*/) {
            hasError = true;
            errorString = bundle.getString("login.error.email");
        } else if (password == null || password.length() < 4) {
            hasError = true;
            errorString = bundle.getString("login.error.pass");
        } else {
            Connection conn = MyUtils.getStoredConnection(req);
            try {
                // Найти user в DB.
                user = DBManager.findUser(conn, email, password);

                if (user == null) {
                    hasError = true;
                    errorString = bundle.getString("login.error.ur_pass");
                }
            } catch (SQLException e) {
                hasError = true;
                errorString = bundle.getString("error.query");
            }
        }
        // В случае, если есть ошибка,
        // forward (перенаправить) к /WEB-INF/views/login.jsp
        if (hasError) {
            user = new User();
            user.setEmail(email);
            user.setPassword(password);


            // Сохранить информацию в request attribute перед forward.
            req.setAttribute("errorString", errorString);
            req.setAttribute("user", user);

            // Forward (перенаправить) к странице /WEB-INF/views/login.jsp

            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");

            dispatcher.forward(req, resp);
        } else {
            // В случае, если нет ошибки.
            // Сохранить информацию пользователя в Session.
            // И перенаправить к странице userInfo.
            HttpSession session = req.getSession();
            MyUtils.storeLoginedUser(session, user);

            // Если пользователь выбирает функцию "Remember me".
            if (remember) {
                MyUtils.storeUserCookie(resp, user);
            }
            // Наоборот, удалить Cookie
            else {
                MyUtils.deleteUserCookie(resp);
            }

            // Redirect (Перенаправить) на страницу /userInfo.

            int redirectId;
            String loginUrl = null;
            try {
                redirectId = (Integer) req.getSession().getAttribute("redirectId");
                loginUrl = MyUtils.getRedirectAfterLoginUrl(req.getSession(), redirectId);
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }

            if (loginUrl != null) {
                resp.sendRedirect(loginUrl);
            } else {
                // По умолчанию после успешного входа в систему
                // перенаправить на страницу /userInfo
                resp.sendRedirect(req.getContextPath() + "/userInfo");
            }

        }
    }
}
