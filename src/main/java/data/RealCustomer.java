package data;

/**
 * Created by mahsa on 19/08/2016.
 */
public class RealCustomer extends Customer{
    private String firstName;
    private String lastName;
    private String fatherName;
    private String birthDate;
    private String nationalCode;

    public RealCustomer(String firstName, String lastName, String fatherName, String birthDate, String nationalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.nationalCode = nationalCode;
    }

    public void setFirstName(String customerFirstName) {
        firstName = customerFirstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String customerLastName) {
        lastName = customerLastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFatherName(String customerFatherName) {
        fatherName = customerFatherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setBirthDate(String customerBirthDate) {
        birthDate = customerBirthDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setnationalCode(String customernationalCode) {
        nationalCode = customernationalCode;
    }

    public String getnationalCode() {
        return nationalCode;
    }

}
