package ua.nure.usik.SummaryTask4.servlet;

import ua.nure.usik.SummaryTask4.db.connection.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/translate")
public class TranslateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = MyUtils.getStoredLanguage(request);

        if (language == null) {
            language = "en";
        }

        if ("ru".equals(language)) {
            MyUtils.storeLanguage(response, "en");
        } else if ("en".equals(language)) {
            MyUtils.storeLanguage(response, "ru");
        }

        response.sendRedirect(request.getContextPath() + "/home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("language");

        if ("ru".equals(language)) {
            MyUtils.storeLanguage(response, "en");
        } else if ("en".equals(language)) {
            MyUtils.storeLanguage(response, "ru");
        }

        response.sendRedirect(request.getHeader("referer"));
    }
}
