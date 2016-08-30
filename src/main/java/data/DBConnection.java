package data;

import java.sql.*;


/**
 * Created by mahsa on 25/08/2016.
 */
public class DBConnection {

    private static DBConnection instance;
    private static Connection connection;

    private DBConnection() {
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public static Connection getConnection() {
        if (connection == null) {
            String username = "massi";
            String password = "mah";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("MySQL JDBC Driver Registered!");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb", username, password);
                DatabaseMetaData dataBaseMetaData = connection.getMetaData();
                ResultSet customerResultSet = dataBaseMetaData.getTables(null, null, "customer", null);
                ResultSet realResultSet = dataBaseMetaData.getTables(null, null, "real_customer", null);
                ResultSet legalResultSet = dataBaseMetaData.getTables(null, null, "legal_customer", null);
                if (!customerResultSet.next()) {
                    createCustomerTable(connection);
                }
                if (!realResultSet.next()) {
                    createRealTable(connection);
                }
                if (!legalResultSet.next()) {
                    createLegalTable(connection);
                }


                if (connection != null) {
                    System.out.println("You made it, take control your database now!");
                } else {
                    System.out.println("Failed to make connection!");
                }
            } catch (
                    ClassNotFoundException e) {
                e.printStackTrace();
            } catch (
                    SQLException e)

            {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void createCustomerTable(Connection connection) {
        String createCustomerTable = "CREATE TABLE customer" +
                "(customer_id int(255) not NULL  AUTO_INCREMENT, " +
                " customer_number INTEGER (255), " +
                " PRIMARY KEY ( customer_id))";
        try {
            PreparedStatement customerPreparedStatement = connection.prepareStatement(createCustomerTable);
            customerPreparedStatement.executeUpdate();
            customerPreparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void createRealTable(Connection connection) {

        String createRealCustomerTable = "CREATE TABLE real_customer" +
                "(realCustomer_id INTEGER NOT NULL AUTO_INCREMENT," +
                " firstName VARCHAR(255), " +
                " lastName VARCHAR(255), " +
                " fatherName VARCHAR(255), " +
                " birthDate VARCHAR(255), " +
                " nationalCode VARCHAR(255)," +
                " PRIMARY KEY(realCustomer_id)," +
                " FOREIGN KEY (realCustomer_id) REFERENCES customer(customer_id))";

        try {

            PreparedStatement realPreparedStatement = connection.prepareStatement(createRealCustomerTable);
            realPreparedStatement.executeUpdate();
            realPreparedStatement.close();
            System.out.println("Table \"dbuser\" is created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createLegalTable(Connection connection) {

        String createLegalCustomerTable = "CREATE TABLE legal_customer" +
                "(legalCustomer_id INTEGER NOT NULL AUTO_INCREMENT, " +
                " customer_name VARCHAR(255), " +
                " customer_date VARCHAR (255)," +
                "customer_code VARCHAR(255)," +
                " PRIMARY KEY (legalCustomer_id)," +
                "FOREIGN KEY (legalCustomer_id) REFERENCES customer(customer_id))";
        try {
            PreparedStatement legalPreparedStatement = connection.prepareStatement(createLegalCustomerTable);
            legalPreparedStatement.executeUpdate();
            legalPreparedStatement.close();
            System.out.println("Table \"dbuser\" is created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}