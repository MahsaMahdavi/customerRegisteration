package data;

import java.sql.*;

/**
 * Created by mahsa on 19/08/2016.
 */
public class LegalCustomerCRUD {

    static Connection connection = null;
    static PreparedStatement legalPreparedStatement;

    public static void insert(LegalCustomer legalCustomer) {
        connection = DBConnection.getInstance().getConnection();
        String insertToLegalCustomerTable = "INSERT INTO legal_customer (customer_name,customer_date,customer_code) " +
                "VALUES (?,?,?)";
        CustomerCRUD.insertCustomer();
        try {
            legalPreparedStatement = connection.prepareStatement(insertToLegalCustomerTable);
            legalPreparedStatement.setString(1, legalCustomer.getName());
            legalPreparedStatement.setString(2, legalCustomer.getRegisterationDate());
            legalPreparedStatement.setString(3, legalCustomer.getBarCode());
            legalPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static LegalCustomer loadRealCustomerByCode(String code) {
        connection = DBConnection.getInstance().getConnection();
        LegalCustomer legalCustomer = null;
        String check = "SELECT * FROM legal_customer WHERE customer_code =?";
        try {
            legalPreparedStatement = connection.prepareStatement(check);
            legalPreparedStatement.setString(1, code);
            ResultSet resultSet = legalPreparedStatement.executeQuery();
            //hallati ke ye result set mikhaym begirim az DB:exquery
            if (resultSet.next()) {
                String name = resultSet.getString("customer_name");
                String date = resultSet.getString("customer_date");
                legalCustomer = new LegalCustomer(name, date, code);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return legalCustomer;
    }
}
