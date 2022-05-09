package it.formarete;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetEmployeeById extends GenericServlet {
    private static final long serialVersionUID = 1L;


    public final Logger logger = Logger.getLogger(GetEmployeeById.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int empId = Integer.parseInt(request.getParameter("empId"));
        logger.info("Request Param empId=" + empId);

        SessionFactory sessionFactory = (SessionFactory) request.getServletContext().getAttribute("SessionFactory");

        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Employee emp = session.get(Employee.class,
                empId);
        tx.commit();
        StringBuilder message = new StringBuilder();
        if (emp != null) {
            message.append("StringBuilder message = new StringBuilder")
                    .append("<table border=\"1\" cellspacing=10 cellpadding=5>")
                    .append("<th>Employee ID</th>")
                    .append("<th>Employee Name</th>")
                    .append("<th>Employee Surname</th>")
                    .append("<tr>")
                    .append("<td>").append(empId).append("</td>")
                    .append("<td>").append(emp.getFirst()).append("</td>")
                    .append("<td>").append(emp.getLast()).append("</td>")
                    .append("</tr>");
        } else {
            message.append("<html><body><h2>No Employee Found with ID=" + empId + "</h2></body></html>");
        }

        this.writeHtmlResponse(response,
                message.toString());

    }

}
