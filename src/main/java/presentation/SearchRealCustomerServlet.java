package presentation;

import business.RealCustomerBusiness;
import data.RealCustomer;
import sun.plugin.com.Dispatcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by mahsa on 27/08/2016.
 */
@WebServlet(name = "SearchRealCustomerServlet")
public class SearchRealCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<String> customerFound;
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String fatherName = request.getParameter("fathername");
        String birthDay = request.getParameter("birthday");
        String nationalCode = request.getParameter("nationalCode");
        customerFound = RealCustomerBusiness.searchRealCustomer(firstName, lastName, fatherName, birthDay, nationalCode);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html lang=fa>");
        out.println("<head>");
        out.print("<script language=\"JavaScript\" type=text/javascript src =tableScript.js> </script>");
        out.println("<title>search</title> <style>table, td, th {border: 1px solid black;}table { border-collapse: collapse;width: 100%;" +
                "}th {height: 50px;}body {direction:rtl;}</style>");
        out.println("</head>");
        out.println("<body lang = fa>");
        out.println("<table><tr>" +
                " <th> نام</th>" +
                " <th> نام خانوادگی</th>" +
                " <th> نام پدر</th>" +
                " <th> تاریخ تولد</th>" +
                " <th> کد ملی</th>" +
                "<th> شماره مشتری</th>");

        int rowNumber = 1;
        for (String customerInf : customerFound) {
            String[] infos = customerInf.split("#");
            String customerFirstName = infos[0];
            String customerLastName = infos[1];
            String customerFatherName = infos[2];
            String customerBirthDate = infos[3];
            String customerNationalCode = infos[4];
            String customerNumber = infos[5];
            out.println("<tr contenteditable=\"true\">+" +
                    "<td id = firstName" + rowNumber + ">" + customerFirstName + "</td>" +
                    "<td id = lastName" + rowNumber + ">" + customerLastName + "</td>" +
                    "<td id = fatherName" + rowNumber + ">" + customerFatherName + "</td>" +
                    "<td id = birthDate" + rowNumber + ">" + customerBirthDate + "</td>" +
                    "<td id = nationalCode" + rowNumber + ">" + customerNationalCode + "</td>" +
                    "<td id = number" + rowNumber + ">" + customerNumber + "</td>" +
                    "<td><button id = editButton" + rowNumber + " onclick = 'editRow(" + rowNumber + ");'>اصلاح</button></td>" +
                    "<td><button id = deleteButton" + rowNumber + " onclick = deleteRow(" + rowNumber + ");>حذف</button></td></tr>");
            rowNumber++;
        }
        out.println("</body>");
        out.println("</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }


}
