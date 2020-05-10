package jdbc.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUtils {

    public static void exec(Connection connection) throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter table size (number from 0 to 12): ");
        int size = getSizeTable();
        JDBCUtils.createTable(connection, size);
        int status = 1;
        while (status != 0) {
            System.out.print(">>> ");
            status = runCommand(connection, scanner);
        }
        scanner.close();
    }

    private static int getSizeTable() {
        Scanner scanner = new Scanner(System.in);
        int size = 0;
        try {
            size = scanner.nextInt();
            if (size < 0 || size > 12) {
                System.out.print("Incorrect input. Please, try again: ");
                return getSizeTable();
            }
        } catch (InputMismatchException e) {
            System.out.print("Incorrect input. Please, try again: ");
            return getSizeTable();
        }
        return size;
    }

    private static int runCommand(Connection connection, Scanner scanner) throws SQLException {
        String command = scanner.next();
        switch (command) {
            case "add":
                String title = scanner.next();
                try {
                    int cost = scanner.nextInt();
                    if (cost < 0) {
                        System.out.println("Incorrect command. Cost < 0");
                        break;
                    }
                    JDBCUtils.add(connection, title, cost);
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect command.");
                }
                break;
            case "delete":
                title = scanner.next();
                JDBCUtils.delete(connection, title);
                break;
            case "show_all":
                JDBCUtils.show(connection);
                break;
            case "price":
                title = scanner.next();
                JDBCUtils.price(connection, title);
                break;
            case "change_price":
                title = scanner.next();
                try {
                    int cost = scanner.nextInt();
                    if (cost < 0) {
                        System.out.println("Incorrect command. Cost < 0.");
                        break;
                    }
                    JDBCUtils.changePrice(connection, title, cost);
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect command.");
                }
                break;
            case "filter_by_price":
                try {
                    int range1 = scanner.nextInt();
                    int range2 = scanner.nextInt();
                    if (range1 < 0 || range2 < 0) {
                        System.out.println("Incorrect command. Range < 0.");
                    } else {
                        if (range1 < range2) {
                            JDBCUtils.filterByPrice(connection, range1, range2);
                        } else {
                            JDBCUtils.filterByPrice(connection, range2, range1);
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect command.");
                }
                break;
            case "exit":
                return 0;
            default:
                System.out.println("command not found.");
                break;
        }
        scanner.nextLine();
        return 1;
    }
}
