package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;

public class ConnectionManager {

    Connection conn;

    public Connection establishConn(String className, String username, String password, String url,
            String hostname, String dbport, String dbname) {
        try {
            Class.forName(className);
            StringBuffer dburl = new StringBuffer(url)
                    .append("://")
                    .append(hostname)
                    .append(":")
                    .append(dbport)
                    .append("/")
                    .append(dbname);
            conn = DriverManager.getConnection(dburl.toString(), username, password);
        } catch (SQLException sqle) {
            System.out.println("SQLException error occured - "
                    + sqle.getMessage());
            sqle.printStackTrace();
        } catch (ClassNotFoundException nfe) {
            System.out.println("ClassNotFoundException error occured - "
                    + nfe.getMessage());
            nfe.printStackTrace();
        }
        return conn;

    }

    public String checklgn(String username, Connection conn) {

        String pw = "";
        try {
            String lgn = "SELECT password FROM WelcomeDB WHERE username=?";
            PreparedStatement ps = conn.prepareStatement(lgn);
            ps.setString(1, username);
//          ps.setString(2, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pw = rs.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pw;
    }

    public int createacc(String username, String password, String role, Connection conn) {
        try {

            System.out.println(conn);
            if (conn != null) {
                System.out.println("Hi po");
                String query = "INSERT INTO WelcomeDB VALUES (?, ?, ?)";

                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, role);
                ps.executeUpdate();
                //int i = ps.executeUpdate();
                //System.out.println("IIIIIIIIII" + i);
                return 1;
            }
        } catch (SQLException sqle) {
            System.out.println("create account");
            System.out.println("SQLException error occured - "
                    + sqle.getMessage());
        }
        return 0;

    }

    public Boolean checkinfo(String username, Connection conn) {

        try {

            String query = "SELECT role FROM WelcomeDB where username=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            Boolean b = rs.next();
            return b;
        } catch (SQLException sqle) {
            System.out.println("None");
        }
        return false;

    }

    public ResultSet getData(Connection conn) {
        try {
            if (conn != null) {
                String query = "SELECT * FROM WelcomeDB";
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet records = ps.executeQuery();
                return records;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return null;

    }

    public ResultSet getSingleData(String username, Connection conn) {
        ResultSet records = null;

        try {
            if (conn != null) {
                String query = "SELECT * FROM WelcomeDB WHERE username=?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, username);
                records = ps.executeQuery();
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return records;

    }

    public String getRole(String username, Connection conn) {
        String role = "";
        ResultSet rs = null;
        
        try {
            String query = "SELECT role FROM WelcomeDB where username=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            
            while (rs.next()){
                role = rs.getString("role");
            }
            
        } catch (SQLException sqle) {
            System.out.println("create account");
            System.out.println("SQLException error occured - "
                    + sqle.getMessage());
        }
        return role;

    }

}
