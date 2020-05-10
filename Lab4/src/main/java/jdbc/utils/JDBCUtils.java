package jdbc.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Scanner;
import java.util.stream.Stream;

public class JDBCUtils {

    public static Connection getNewConnection() throws SQLException {
        String dbURL = "jdbc:h2:C:\\Users\\79006\\test";
        Connection connection = DriverManager.getConnection(dbURL, "root", "");
        if (connection.isValid(1)) {
            System.out.println("Connection successful");
        }
        return connection;
    }

    private static void deleteTable(Connection connection) throws SQLException {
        String sql = "DROP TABLE PRODUCTS";
        try(Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public static void createTable(Connection connection, int size) throws SQLException, IOException {
        String sql = "CREATE TABLE PRODUCTS (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "title VARCHAR, " +
                "cost INT);";
        try(Statement statement = connection.createStatement()) {
            if (tableExists(connection)) {
                deleteTable(connection);
            }
            statement.execute(sql);
            File file = new File("Products.txt");
            Scanner scanner = new Scanner(file);
            for (int i = 0; i < size; i++) {
                if (scanner.hasNext()) {
                    String[] lineArg = scanner.nextLine().split(" ");
                    add(connection, lineArg[0], Integer.valueOf(lineArg[1]));
                }
            }
            scanner.close();
            System.out.println("Table created");
        }
    }

    public static void add(
            Connection connection,
            String title,
            int cost
    ) throws SQLException {
        if (titleNotExists(connection, title)) {
            String sql = "INSERT INTO PRODUCTS (title, cost) VALUES (?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, title);
                statement.setInt(2, cost);
                statement.executeUpdate();
            }
        } else {
            System.out.println(title + " already exists.");
        }
    }

    public static void delete(Connection connection, String title) throws SQLException {
        String sql = "DELETE FROM PRODUCTS WHERE TITLE = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.executeUpdate();
        }
    }

    public static void show(Connection connection) throws SQLException {
        String sql = "SELECT * FROM PRODUCTS";
        try(PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next())
            {
                int id = resultSet.getInt("ID");
                String title = resultSet.getString("TITLE");
                int cost = resultSet.getInt("COST");
                System.out.println(id + ".  " + title + " | " + cost);
            }
        }
    }

    public static void price(Connection connection, String title) throws SQLException {
        String sql = "SELECT COST FROM PRODUCTS WHERE TITLE = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int cost = resultSet.getInt(1);
                    System.out.println(cost);
                }
            }
        }
    }

    public static void changePrice(Connection connection, String title, int cost) throws SQLException {
        String sql = "UPDATE PRODUCTS SET COST = ? WHERE TITLE = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, cost);
            statement.setString(2, title);
            statement.executeUpdate();
        }
    }

    public static void filterByPrice(Connection connection, int range1, int range2) throws SQLException {
        String sql = "SELECT * FROM PRODUCTS WHERE COST >= ? AND COST <= ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, range1);
            statement.setInt(2, range2);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String title = resultSet.getString("TITLE");
                    int cost = resultSet.getInt("COST");
                    System.out.println(id + ".  " + title + " | " + cost);
                }
            }
        }
    }

    private static boolean titleNotExists(Connection connection, String title) throws SQLException {
        String sql = "SELECT * FROM PRODUCTS WHERE TITLE = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean tableExists(Connection connection) throws SQLException {
        ResultSet resultSet = connection.getMetaData().getTables(null, null, "PRODUCTS", null);
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }
}

