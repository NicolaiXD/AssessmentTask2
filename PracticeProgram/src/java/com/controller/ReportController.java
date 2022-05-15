package com.controller;

//import com.itextpdf.text.DocumentException;
import com.itextpdf.text.DocumentException;
import com.model.ConnectionManager;
import com.report.Report;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReportController extends HttpServlet {

    private Connection conn;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            
            ServletContext ctr = getServletContext();
            conn = (Connection) ctr.getAttribute("conn");
            //System.out.println("AAAAAAAAAAAAA");

            HttpSession session = request.getSession();
            Report report = new Report();
            ConnectionManager mm = new ConnectionManager();

            String username = (String) session.getAttribute("username");
            String role = (String) session.getAttribute("role");
            System.out.println(role);
            System.out.println(conn);
            //ResultSet result;

            //System.out.println("LOLOLOLOLOLOLOLOLOLOL");
            //if (role == null){
            if (role.equals("Admin")) {
                System.out.println("ALPHABRAVOCHARLIE");
                try {
                    //result = mm.getData(conn);
                    //report.Reported(username, role, result);
                    report.Report(username, role, conn);
                } catch (DocumentException ex) {
                    Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("errorMessage", "Admin test report is done, check mo Download folder mo");
                response.sendRedirect("home.jsp");
            } //}
            else if (role.equals("Guest")) {
                try {
                    //result = mm.getSingleData(username, conn);
                    report.Report(username, role, conn);
                } catch (DocumentException ex) {
                    Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("errorMessage", "Guest test report is done, check mo Download folder mo");
                response.sendRedirect("home.jsp");
            }

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
