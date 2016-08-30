package presentation;

import business.RealCustomerBusiness;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by mahsa on 30/08/2016.
 */
@WebServlet(name = "UpdateRealCustomerServlet")
public class UpdateRealCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String fatherName = request.getParameter("fatherName");
        String birthDay = request.getParameter("birthDate");
        String nationalCode = request.getParameter("nationalCode");
        String number = request.getParameter("number");
        String id = request.getParameter("rowNumber");
        String result = RealCustomerBusiness.editRealCustomer(firstName, lastName, fatherName, birthDay, nationalCode, number, Integer.parseInt(id));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>اصلاح</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + result + "</h2>");
        out.println("</body>");
        out.println("</html>");
    }
}
