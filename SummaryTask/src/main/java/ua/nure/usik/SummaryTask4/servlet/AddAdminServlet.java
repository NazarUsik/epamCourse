package ua.nure.usik.SummaryTask4.servlet;

import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.db.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/addAdmin")
public class AddAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(request.getContextPath() + "/adminPage");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(req);

        String firstName = req.getParameter("f_name");
        String lastName = req.getParameter("l_name");
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        String addStatus = "";

        try {
            if(DBManager.insertUser(connection, new User(firstName, lastName, login, pass, 1))){
                addStatus += "Add successful!";
            } else {
                addStatus += "Admin not add!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            addStatus += e.getMessage();
        }

        resp.sendRedirect(req.getContextPath() + "/adminPage?addAdminStatus=" + addStatus);
    }
}
