package presentation;

import business.LegalCustomerBusiness;
import data.LegalCustomer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by mahsa on 19/08/2016.
 */
public class CreateLegalCustomerServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String companyName = request.getParameter("companyName");
        String date = request.getParameter("registerationDate");
        String code = request.getParameter("barCode");
        LegalCustomer legalCustomer = new LegalCustomer(companyName, date, code);
        String result = LegalCustomerBusiness.insertToDB(legalCustomer);
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

}
