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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

        String trainType = request.getParameter("trainType");
        String addTrain = "";


        int typeId = TrainType.getTrainTypeId(trainType);
        try {
            if (DBManager.insertTrain(connection, new Train(typeId))) {
                addTrain += "Add successful";
            } else {
                addTrain += "Not add train";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            addTrain += e.getMessage();
        }


        response.sendRedirect(request.getContextPath() + "/adminPage?addTrainStatus=" + addTrain);

    }
}
