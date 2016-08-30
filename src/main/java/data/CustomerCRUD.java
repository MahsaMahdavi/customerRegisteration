package data;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mahsa on 19/08/2016.
 */
public class CustomerCRUD {

    static PreparedStatement customerPreparedStatement;
    static Connection connection = null;
    //    int customerNumber = 0;
   public static int customerNumber;

    //    public  int generateCode(Customer customer) {
    public static int generateCode() {
        //todo select max(customerNumber) from customer
        //todo for the first time this query returns 0
        //todo so you should check condition if customer number is 0 or not
        connection = DBConnection.getInstance().getConnection();
        String maxNumber = "Select COALESCE(MAX(customer_number),0) from customer";

        try {
            PreparedStatement max = connection.prepareStatement(maxNumber);
            ResultSet resultSet = max.executeQuery();
            if(resultSet.next()){
                customerNumber = resultSet.getInt(1)+1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerNumber;
    }


    public static void insertCustomer() {

        connection = DBConnection.getInstance().getConnection();
        String insertToCustomerTable = "INSERT INTO customer (customer_number) " +
                "VALUES (?)";
        try {
            customerPreparedStatement = connection.prepareStatement(insertToCustomerTable);
            customerPreparedStatement.setInt(1,generateCode());
            customerPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
