package com.report;

import com.model.ConnectionManager;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Report {

    String username = "";
    String role = "";

    public void Report(String username, String role, Connection conn) throws DocumentException {

        this.username = username;
        this.role = role;

        System.out.println("Report.java");
        System.out.println("Username: " + this.username);
        System.out.println("Role: " + this.role);
        

        try {
            Document docs = new Document();
            PdfWriter.getInstance(docs, new FileOutputStream("C:\\Users\\LENINA\\Downloads\\TestReport.pdf"));
            
            docs.open();
            PdfPTable table = new PdfPTable(2);

            PdfPCell h1 = new PdfPCell(new Phrase("Username"));
            PdfPCell h2 = new PdfPCell(new Phrase("Role"));

            table.addCell(h1);
            table.addCell(h2);

            if (role.equals("Admin")) {

                String query = "SELECT * FROM WelcomeDB";
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet result = ps.executeQuery();

                while (result.next()) {

                    PdfPCell c1 = new PdfPCell(new Phrase(result.getString("username")));
                    PdfPCell c2 = new PdfPCell(new Phrase(result.getString("role")));

                    table.addCell(c1);
                    table.addCell(c2);

                }
            } else if (role.equals("Guest")) {

                PdfPCell c1 = new PdfPCell(new Phrase(username));
                PdfPCell c2 = new PdfPCell(new Phrase(role));

                table.addCell(c1);
                table.addCell(c2);

            }

            docs.add(table);
            docs.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}