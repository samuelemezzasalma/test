package it.formarete;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

public class SessionTrack1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession(true);
        Date sessionDate = new Date(session.getCreationTime());
        if (session.isNew()) {
            session.setAttribute("sessionID", "12345");
        } else {


        }
        session.getId();

        // Recupero i nomi degli oggetti presenti in sesssione
        Enumeration<String> attributeNames = session.getAttributeNames();

        // ciclo sui nomi e stampo i valori ad essi associati
        while (attributeNames.hasMoreElements()) {
            String nextElement = attributeNames.nextElement();
            Object attributeValue = session.getAttribute(nextElement);
            out.println(nextElement);
            out.println(attributeValue);
        }
    }
}
