package com.amihaeseisergiu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author amiha
 */
public class ParameterizedServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ProjectWriter.getInstance().writeServerLog(request);
        
        List<String> parameters = Collections.list(request.getParameterNames());
        
        if(parameters.size() == 4 && parameters.containsAll(Arrays.asList("key", "value", "mock", "sync")))
        {
            String key = request.getParameter("key");
            int value = Integer.valueOf(request.getParameter("value"));
            boolean mock = Boolean.valueOf(request.getParameter("mock"));
            boolean sync = Boolean.valueOf(request.getParameter("sync"));
            
            
            if(mock)
            {
                if(request.getHeader("user-agent").contains("Java-http-client"))
                {
                    messageClientText(response, "Simple text");
                }
                else
                {
                    messageClientHtml(response, "<h1>Confirmed</h1>");
                }
            }
            else
            {
                if(sync)
                {
                    ProjectWriter.getInstance().writeSynchronous(key, value);
                }
                else
                {
                    ProjectWriter.getInstance().writeAsynchronous(key, value);
                }
                
                if(request.getHeader("user-agent").contains("Java-http-client"))
                {
                    messageClientText(response, "Simple text");
                }
                else
                {
                    messageClientHtml(response, ProjectReader.constructRepositoryHtml());
                }
            }
        }
        else
        {
            response.sendRedirect("errors/invalidParameters.html");
        }
    }
    
    private void messageClientText(HttpServletResponse response, String text) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(text);
        }
    }
    
    private void messageClientHtml(HttpServletResponse response, String text) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ParameterizedServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println(text);
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
