package ua.nure.usik.SummaryTask4.servlet;

import ua.nure.usik.SummaryTask4.db.DBManager;
import ua.nure.usik.SummaryTask4.db.connection.ConnectionUtils;
import ua.nure.usik.SummaryTask4.db.connection.MyUtils;
import ua.nure.usik.SummaryTask4.db.entity.Carriage;
import ua.nure.usik.SummaryTask4.db.entity.Seats;
import ua.nure.usik.SummaryTask4.db.entity.Train;
import ua.nure.usik.SummaryTask4.db.entity.enums.CarriageType;
import ua.nure.usik.SummaryTask4.db.entity.enums.TrainType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/addCarriage")
public class AddCarriageServlet extends HttpServlet {
    public AddCarriageServlet() {
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

        int trainId = Integer.parseInt(request.getParameter("trainId"));
        String carriageType = request.getParameter("type");
        int countSeats = Integer.parseInt(request.getParameter("countSeats"));
        boolean rest = "Y".equals(request.getParameter("rest"));
        String addCarriage = "";

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String language = MyUtils.getStoredLanguage(request);

        if (language == null) {
            language = "en";
        }

        ResourceBundle bundle = ResourceBundle.getBundle("warnings", new Locale(language));

        try {
            if (DBManager.insertCarriage(connection,
                    new Carriage(CarriageType.getCarriageTypeId(carriageType), countSeats, countSeats, rest))) {

                int carriageId = DBManager.getCarriageIdLastAdd(connection);
                int lastNumberCarriage = DBManager.getLastCarriageNumberByTrain(connection, trainId);

                if (!DBManager.insertTrainComposition(connection, trainId, carriageId, (lastNumberCarriage + 1))) {
                    ConnectionUtils.rollbackQuietly(connection);
                }

                for (int i = 0; i < countSeats; i++) {
                    if (!DBManager.insertSeat(connection, new Seats(carriageId, (i + 1), true))) {
                        ConnectionUtils.rollbackQuietly(connection);
                    }
                }

                addCarriage += bundle.getString("add.successful");
            } else {
                addCarriage += bundle.getString("error.query");
            }
        } catch (SQLException e) {
            addCarriage += bundle.getString("add.error.carriage");
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
        request.getSession().setAttribute("addCarriageStatus", addCarriage);
        response.sendRedirect(request.getContextPath() + "/adminPage");


    }
}
