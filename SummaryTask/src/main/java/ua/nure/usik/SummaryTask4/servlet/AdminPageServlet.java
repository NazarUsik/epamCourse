package ua.nure.usik.SummaryTask4.servlet;

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

@WebServlet("/adminPage")
public class AdminPageServlet extends HttpServlet {
    public AdminPageServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        User loginedUser = MyUtils.getLoginedUser(session);

        // Если еще не вошел в систему (login).
        if(loginedUser == null){
            // Redirect (Перенаправить) к странице login.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        //Если не админ
        if(loginedUser.getRoleId() != 1) {
            response.sendRedirect(request.getContextPath() + "/userInfo");
            return;
        }
            // Forward to /WEB-INF/views/homeView.jsp
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/adminPageView.jsp");

            dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
