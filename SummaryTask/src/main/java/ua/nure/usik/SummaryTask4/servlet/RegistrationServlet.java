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

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");

        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        String language = MyUtils.getStoredLanguage(request);

        if (language == null) {
            language = "en";
        }

        String f_name = request.getParameter("f_name");
        String l_name = request.getParameter("l_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String errorMassage;

        ResourceBundle bundle = ResourceBundle.getBundle("warnings", new Locale(language));

        if (!email.matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)" +
                "*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            errorMassage = bundle.getString("login.error.email");

            request.setAttribute("errorReg", errorMassage);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");

            dispatcher.forward(request, response);
            return;
        }

        if (password.length() < 8 || password.length() > 64) {
            errorMassage = bundle.getString("login.error.pass");

            request.setAttribute("errorReg", errorMassage);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");

            dispatcher.forward(request, response);
            return;

        }
        try {
            if (DBManager.findUser(conn, email) != null) {
                errorMassage = bundle.getString("email");
                request.setAttribute("errorMassage", errorMassage);
                request.setAttribute("errorId", "email");
                RequestDispatcher dispatcher //
                        = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");

                dispatcher.forward(request, response);
                return;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            User user = new User(f_name, l_name, email, password, 2);
            if (DBManager.insertUser(conn, user)) {
                HttpSession session = request.getSession();
                MyUtils.storeLoginedUser(session, user);
                MyUtils.storeUserCookie(response, user);
                response.sendRedirect(request.getContextPath() + "/userInfo");
                return;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/home");
    }
}
