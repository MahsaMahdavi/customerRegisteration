package business;

import data.*;
import data.CustomerCRUD;
import exception.DuplicateCodeException;

import java.util.ArrayList;

/**
 * Created by mahsa on 23/08/2016.
 */
public class RealCustomerBusiness {

    public static String insertToDB(RealCustomer realCustomer) {
        String message;
        try {
            checkDuplicateCode(realCustomer);
            RealCustomerCRUD.insert(realCustomer);
            message = "Your Customer Number :" + CustomerCRUD.customerNumber;
        } catch (DuplicateCodeException duplicateCodeException) {
            message = duplicateCodeException.getMessage();
        }
        return message;
    }

    public static void checkDuplicateCode(RealCustomer realCustomer) throws DuplicateCodeException {
        String message = null;
        RealCustomer customerExist = RealCustomerCRUD.loadRealCustomerByCode(realCustomer.getnationalCode());
        if (customerExist != null) {
            throw new DuplicateCodeException("This National Code Exist!");
        }
    }

    public static ArrayList<String> searchRealCustomer(String firstName, String lastName, String fatherName, String birthDay, String nationalCode) {
    return RealCustomerCRUD.search(firstName,lastName,fatherName,birthDay,nationalCode);
    }


    public static String editRealCustomer(String firstName, String lastName, String fatherName, String birthDay, String nationalCode, String number, int id) {
    return RealCustomerCRUD.edit(firstName,lastName,fatherName,birthDay,nationalCode,number,id);
    }

    public static String deleteRealCustomer(int id) {
        return RealCustomerCRUD.delete(id);
    }
}
