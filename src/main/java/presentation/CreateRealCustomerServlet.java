package presentation;

import business.RealCustomerBusiness;
import data.RealCustomer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by mahsa on 21/08/2016.
 */
@WebServlet(name = "Real")
public class CreateRealCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String fatherName = request.getParameter("fathername");
        String birthDay = request.getParameter("birthday");
        String code = request.getParameter("nationalCode");
        RealCustomer realCustomer = new RealCustomer(firstName, lastName, fatherName, birthDay, code);
        String result = RealCustomerBusiness.insertToDB(realCustomer);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>register</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + result + "</h2>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
