package ua.nure.usik.SummaryTask4.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionUtils {

    public static Connection getMySQLConnection()
            throws ClassNotFoundException, SQLException {
        // Примечание: Изменить параметры соединения соответствующе.
        String hostName = "localhost";
        String dbName = "final_project_db";
        String userName = "root";
        String password = "admin";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException,
            ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");

        // Структура URL Connection для MySQL:
        // Например:
        // jdbc:mysql://localhost:3306/simplehr
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?useUnicode=true&serverTimezone=UTC";

        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }
}
