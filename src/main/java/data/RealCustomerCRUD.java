package data;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by mahsa on 19/08/2016.
 */
public class RealCustomerCRUD {

    static PreparedStatement realPreparedStatement;
    static Connection connection = null;

    public static void insert(RealCustomer realCustomer) {
        connection = DBConnection.getInstance().getConnection();
        //todo call CustomerCRUD.insertCustomer() method
        CustomerCRUD.insertCustomer();
        String insertToRealCustomerTable = "INSERT INTO real_customer" +
                "(firstname,lastname,fathername,birthDate,nationalCode)" +
                " VALUES(?,?,?,?,?)";
        try {
            realPreparedStatement = connection.prepareStatement(insertToRealCustomerTable);
            realPreparedStatement.setString(1, realCustomer.getFirstName());
            realPreparedStatement.setString(2, realCustomer.getLastName());
            realPreparedStatement.setString(3, realCustomer.getFatherName());
            realPreparedStatement.setString(4, realCustomer.getBirthDate());
            realPreparedStatement.setString(5, realCustomer.getnationalCode());
            realPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static RealCustomer loadRealCustomerByCode(String nationalCode) {

        connection = DBConnection.getInstance().getConnection();
        RealCustomer realCustomer = null;
        String check = "SELECT * FROM real_customer WHERE nationalCode =? ";
        try {
            realPreparedStatement = connection.prepareStatement(check);
            realPreparedStatement.setString(1, nationalCode);
            ResultSet resultSet = realPreparedStatement.executeQuery();
            //hallati ke ye result set mikhaym begirim az DB:exquery
            if (resultSet.next()) {
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String fatherName = resultSet.getString("fathername");
                String birthDate = resultSet.getString("birthDate");
                realCustomer = new RealCustomer(firstName, lastName, fatherName, birthDate, nationalCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return realCustomer;
    }

    public static ArrayList<String> search(String firstName, String lastName, String fatherName, String birthDay, String nationalCode) {
        ArrayList<String> customerFound = new ArrayList<String>();
        int counter = 1;
        connection = DBConnection.getInstance().getConnection();
        String select = "SELECT * FROM real_customer INNER JOIN customer ON real_customer.realCustomer_id = " +
                "customer.customer_id WHERE ";
        int andStation = 0;
        if (!firstName.equals("")) {
            select = select + "real_customer.firstname = ? AND";
        }
        if (!lastName.equals("")) {
            select = select + "real_customer.lastname = ? AND ";
        }
        if (!fatherName.equals("")) {

            select = select + "real_customer.fathername = ? AND ";
        }
        if (!birthDay.equals("")) {

            select = select + "real_customer.birthDate= ? َAND ";
        }
        if (!nationalCode.equals("")) {

            select = select + "real_customer.nationalCode= ? AND ";
        }
        select += "1 = 1";

        try {
            realPreparedStatement = connection.prepareStatement(select);
            if (!firstName.equals("")) {
                realPreparedStatement.setString(counter, firstName);
                counter++;
            }
            if (!lastName.equals("")) {
                realPreparedStatement.setString(counter, lastName);
                counter++;
            }
            if (!fatherName.equals("")) {
                realPreparedStatement.setString(counter, fatherName);
                counter++;
            }
            if (!birthDay.equals("")) {
                realPreparedStatement.setString(counter, birthDay);
                counter++;
            }
            if (!nationalCode.equals("")) {
                realPreparedStatement.setString(counter, nationalCode);
            }
            ResultSet resultSet = realPreparedStatement.executeQuery();
            while (resultSet.next()) {
                String selectedFirstName = resultSet.getString("firstname");
                String selectedLastName = resultSet.getString("lastname");
                String selectedFatherName = resultSet.getString("fathername");
                String selectedBirthDate = resultSet.getString("birthDate");
                String selectedNationalCode = resultSet.getString("nationalCode");
                int selectedcustomerNumber = resultSet.getInt("customer_number");
                String result = selectedFirstName + "#" + selectedLastName + "#" + selectedFatherName + "#" + selectedBirthDate + "#"
                        + selectedNationalCode + "#" + String.valueOf(selectedcustomerNumber);
                customerFound.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerFound;
    }

    public static String edit(String firstName, String lastName, String fatherName, String birthDay, String nationalCode, String number, int id) {
        connection = DBConnection.getInstance().getConnection();
//        String update = "UPDATE real_customer AS r INNER JOIN customer AS c ON r.realCustomer_id = c.customer_id SET  " +
//                "r.firstname =? , r.lastname = ?," +
//                "rc.fathername = ?,rc.birthDate= ?, rc.nationalCode= ?, cu.customer_number = ?  WHERE " +
//                "realCustomer_id = ?";
        String updateRealCustomer = "UPDATE real_customer SET  " +
                "firstname = ?,lastname = ?," +
                "fathername = ?,birthDate= ?,nationalCode= ? WHERE realCustomer_id = ?";

        String updateCustomer = "UPDATE customer SET  " +
                "customer_number = ? WHERE customer_id = ?";
        try {
            PreparedStatement editRealPrepareStatment = connection.prepareStatement(updateRealCustomer);
            PreparedStatement editCustomerPrepareStatment = connection.prepareStatement(updateCustomer);
            editRealPrepareStatment.setString(1, firstName);
            editRealPrepareStatment.setString(2, lastName);
            editRealPrepareStatment.setString(3, fatherName);
            editRealPrepareStatment.setString(4, birthDay);
            editRealPrepareStatment.setString(5, nationalCode);
            editRealPrepareStatment.setInt(6, id);
            editCustomerPrepareStatment.setString(1, number);
            editCustomerPrepareStatment.setInt(2, id);
            editRealPrepareStatment.executeUpdate();
            editCustomerPrepareStatment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Edit successfully :)";
    }

    public static String delete(int id) {
        connection = DBConnection.getInstance().getConnection();
        String deleteRealCustomer = "DELETE FROM real_customer WHERE realCustomer_id = ?";
        String deleteCustomer = "DELETE FROM customer WHERE Customer_id = ?";

        try {
            PreparedStatement deleteRealCustomerPreparedStatement = connection.prepareStatement(deleteRealCustomer);
            PreparedStatement deleteCustomerPreparedStatement = connection.prepareStatement(deleteCustomer);
            deleteRealCustomerPreparedStatement.setInt(1, id);
            deleteCustomerPreparedStatement.setInt(1, id);
            deleteRealCustomerPreparedStatement.executeUpdate();
            deleteCustomerPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Delete successfully :D";
    }
}
