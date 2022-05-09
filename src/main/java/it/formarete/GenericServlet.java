package it.formarete;

import org.hibernate.annotations.common.reflection.XMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GenericServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req,
                resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req,
                resp);
    }

    public void writeJsonResponse(HttpServletResponse response, String jsonResult) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setBufferSize(jsonResult.length());
        response.setContentLength(jsonResult.length());
        PrintWriter writer = response.getWriter();
        writer.write(jsonResult);
        writer.flush();
    }

    public void writeHtmlResponse(HttpServletResponse response, String htmlResult) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.write(htmlResult);
        writer.flush();
    }





}
