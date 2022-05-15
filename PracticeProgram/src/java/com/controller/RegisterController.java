package com.controller;

import com.model.ConnectionManager;
import com.security.Security;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import nl.captcha.Captcha;

public class RegisterController extends HttpServlet {

    //private static final long serialVersionUID = 1L;
    Connection conn;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String classname = config.getInitParameter("jdbcClassName");
        String username = config.getInitParameter("dbUserName");
        String password = config.getInitParameter("dbPassword");
        String url = config.getInitParameter("jdbcDriverURL");
        String hostname = config.getInitParameter("dbHostName");
        String dbport = config.getInitParameter("dbPort");
        String dbname = config.getInitParameter("databaseName");
        ConnectionManager lgn = new ConnectionManager();
        conn = lgn.establishConn(classname, username, password, url, hostname, dbport, dbname);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cpassword = request.getParameter("cpassword");
        String role = request.getParameter("role");
        //ConnectionManager lgn = new ConnectionManager();
        //String lgnn = lgn.checklgn(username,rol, conn);
        //String enpass = Security.encrypt(password);
        //boolean con = password.equals(depass);
        request.setCharacterEncoding("UTF-8");
        Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
        
        Security sec = new Security();
        String encryptpassword = sec.encrypt(password);
        
        String rcap = request.getParameter("rcaptcha");

        ConnectionManager ck = new ConnectionManager();
        Boolean a = password.equals(cpassword);
        Boolean b = ck.checkinfo(username, conn);

//        System.out.println(a.toString());
//        System.out.println(b.toString());
        
        if (captcha.isCorrect(rcap)) {
            //response.sendRedirect("register.jsp");
            
            
            

            if ((b != true) || (a)) {
                ConnectionManager re = new ConnectionManager();
                System.out.println(encryptpassword);
                int i = re.createacc(username, encryptpassword, role, conn);
                
                
                if (i == 1) {
                    System.out.println("AAAAAAAAAAAAAA");
                    response.sendRedirect("welcome.jsp");
                } else {
                    System.out.println("BBBBBBBBBBBB");
                    //response.sendRedirect("error.jsp");
                }
            } else {
                System.out.println("CCCCCCCCCCCCCCCCCCCCC");
                response.sendRedirect("error.jsp");
            }
        }
        else {        
            System.out.println("DDDDDDDDDDDDDDDDD");
            //response.sendRedirect("error.jsp");
        }
//        if ((b != true) || (a)) {
//            ConnectionManager re = new ConnectionManager();
//            int i = re.createacc(username, enpass, role, conn);
//            
//            if (i == 1){
//                System.out.println("AAAAAAAAAAAAAA");
//                response.sendRedirect("welcome.jsp");
//            }
//            
//            else {
//                System.out.println("BBBBBBBBBBBB");
//                response.sendRedirect("error.jsp");
//            }
//        }
//        
//        else {
//            System.out.println("CCCCCCCCCCCCCCCCCCCCC");
//            response.sendRedirect("error.jsp");
//        }
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
