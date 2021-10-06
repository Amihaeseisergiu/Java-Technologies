package com.amihaeseisergiu.controllers;

import com.amihaeseisergiu.models.Record;
import com.amihaeseisergiu.services.RecordService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InputController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String category = request.getParameter("category");
        String word = request.getParameter("word");
        String definition = request.getParameter("definition");
        
        boolean checkParams = word != null && !word.isEmpty() && definition != null && !definition.isEmpty();
        String nextPage = (checkParams ? "/result.jsp" : "/input.jsp");
        
        if(checkParams)
        {
            RecordService.writeRecord(new Record(category, word, definition));
            List<Record> records = RecordService.readRecords();
            
            request.setAttribute("records", records);
        }
        
        getServletContext().getRequestDispatcher(nextPage).forward(request, response);
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
