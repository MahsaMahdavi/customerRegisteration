package data;

/**
 * Created by mahsa on 19/08/2016.
 */
public class LegalCustomer extends Customer{
    String name;
    String registerationDate;
    String barCode;

    public LegalCustomer(String name, String registerationDate, String barCode) {
        this.name = name;
        this.registerationDate = registerationDate;
        this.barCode = barCode;
    }

    public void setName(String customerName) {
        name = customerName;
    }

    public String getName() {
        return name;
    }

    public void setRegisterationDate(String customerRegisterationDate) {
        registerationDate = customerRegisterationDate;
    }

    public String getRegisterationDate() {
        return registerationDate;
    }

    public void setBarCode(String customerbarCode) {
        barCode = customerbarCode;
    }

    public String getBarCode() {
        return barCode;
    }
}
