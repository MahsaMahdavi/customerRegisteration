package business;


import data.*;
import exception.DuplicateCodeException;

/**
 * Created by mahsa on 23/08/2016.
 */
public class LegalCustomerBusiness {

    public static String insertToDB(LegalCustomer legalCustomer) {
        String message;
        try {
            checkDuplicateCode(legalCustomer);
            LegalCustomerCRUD.insert(legalCustomer);
            message = "Your Customer Number :" + CustomerCRUD.customerNumber;
        } catch (DuplicateCodeException duplicateCodeException) {
            message = duplicateCodeException.getMessage();
        }
        return message;
    }

    public static void checkDuplicateCode(LegalCustomer legalCustomer) throws DuplicateCodeException {
        String message = null;
        LegalCustomer customerExist = LegalCustomerCRUD.loadRealCustomerByCode(legalCustomer.getBarCode());
        if (customerExist != null) {
            throw new DuplicateCodeException("This National Code Exist!");
        }
    }



}
