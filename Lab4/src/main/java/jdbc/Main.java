package jdbc;

import jdbc.utils.ConsoleUtils;
import jdbc.utils.JDBCUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            ConsoleUtils.exec(connection);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
}
