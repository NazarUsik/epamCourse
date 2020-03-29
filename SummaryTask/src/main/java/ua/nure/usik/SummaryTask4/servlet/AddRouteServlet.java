package ua.nure.usik.SummaryTask4.servlet;

import javafx.util.Pair;
import ua.nure.usik.SummaryTask4.db.DBManager;
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

@WebServlet("/addRoute")
public class AddRouteServlet extends HttpServlet {
    public AddRouteServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection connection = MyUtils.getStoredConnection(request);
        List<Pair<String, Train>> trains = null;
        List<Pair<Integer, Pair<String, Carriage>>> carriages = null;
        String error = "";

        try {
            trains = DBManager.getAllTrain(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            error += e.getSQLState();
        }

        try {
            carriages = DBManager.getAllCarriage(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            error += e.getSQLState();
        }

//        System.out.println(Arrays.toString(trains.toArray()));
//        System.out.println(Arrays.toString(carriages.toArray()));

        request.setAttribute("trains", trains);
        request.setAttribute("carriages", carriages);
        request.setAttribute("error", error);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/addRouteView.jsp");

        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(request);

        String form = request.getParameter("form");
        if (form.equals("1")) {
            String trainType = request.getParameter("trainType");
            String addTrain = "";

            if (trainType != null) {
                int typeId = TrainType.getTrainTypeId(trainType);
                System.out.println(typeId);
                try {
                    if (DBManager.insertTrain(connection, new Train(typeId))) {
                        addTrain += "Add successful";
                    } else {
                        addTrain += "Not add train";
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    addTrain += e.getSQLState();
                }
            } else {
                addTrain += "Select train type!";
            }

            request.setAttribute("addTrain", addTrain);

        } else if (form.equals("2")) {
            String trainId = request.getParameter("trainId");
            String carriageType = request.getParameter("type");
            String countSeats = request.getParameter("countSeats");
            String rest = request.getParameter("rest");
            String addCarriage = "";

            if (trainId != null && carriageType != null && countSeats != null) {
                boolean ch = false;
                if (rest != null) {
                    ch = true;
                }
                int count = Integer.parseInt(countSeats);
                try {
                    if (DBManager.insertCarriage(connection,
                            new Carriage(CarriageType.getCarriageTypeId(carriageType), count, count, ch))) {

                        int carriageId = DBManager.getCarriageIdLastAdd(connection);

                        DBManager.insertTrainComposition(connection, Integer.parseInt(trainId), carriageId);

                        for (int i = 0; i < count; i++) {
                            DBManager.insertSeat(connection, new Seats(carriageId, (i + 1), true));
                        }

                        addCarriage += "Add successful";
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    addCarriage += e.getSQLState();
                }
            } else {
                addCarriage += "Fill all fields first!";
            }

            request.setAttribute("addCarriage", addCarriage);

        }
    
        doGet(request, response);
    }
}
