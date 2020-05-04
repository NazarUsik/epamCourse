package ua.nure.usik.SummaryTask4.servlet;

import javafx.util.Pair;
import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.ConnectionUtils;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.db.entity.Carriage;
import ua.nure.usik.SummaryTask4.db.entity.Seats;
import ua.nure.usik.SummaryTask4.db.entity.Train;
import ua.nure.usik.SummaryTask4.db.entity.User;
import ua.nure.usik.SummaryTask4.db.entity.enums.CarriageType;
import ua.nure.usik.SummaryTask4.db.entity.enums.TrainType;

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
import java.util.*;

@WebServlet("/addTrain")
public class AddTrainServlet extends HttpServlet {
    public AddTrainServlet() {
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

        request.setCharacterEncoding("UTF-8");

        String trainType = request.getParameter("trainType");
        String addTrain = "";

        String language = MyUtils.getStoredLanguage(request);

        if (language == null) {
            language = "en";
        }

        ResourceBundle bundle = ResourceBundle.getBundle("warnings", new Locale(language));

        int typeId = TrainType.getTrainTypeId(trainType);
        try {
            if (DBManager.insertTrain(connection, new Train(typeId))) {
                addTrain += bundle.getString("add.successful");
            } else {
                addTrain += bundle.getString("add.error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            addTrain += bundle.getString("add.error.train");
        }

        request.getSession().setAttribute("addTrainStatus", addTrain);
        response.sendRedirect(request.getContextPath() + "/adminPage");

    }
}
