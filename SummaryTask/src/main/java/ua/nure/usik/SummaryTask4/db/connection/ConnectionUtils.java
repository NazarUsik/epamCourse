package ua.nure.usik.SummaryTask4.db.connection;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {

        // Здесь я подключаюсь к MySQL Database.
        return MySQLConnectionUtils.getMySQLConnection();

    }

    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}