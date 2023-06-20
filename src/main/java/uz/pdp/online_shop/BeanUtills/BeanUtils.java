package uz.pdp.online_shop.BeanUtills;

import java.sql.Connection;
import java.sql.DriverManager;

public class BeanUtils {
    private static Connection connection;


    private static String DB_URL = "jdbc:postgresql://localhost:5432/";
    private static String DB_NAME = "online_shopping_data";
    private static String DB_USERNAME = "postgres";
    private static String DB_PASSWORD = "1106";

    public static Connection connection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                 connection = DriverManager.getConnection(DB_URL+DB_NAME, DB_USERNAME, DB_PASSWORD);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

}
