
package com.controller.action;

import com.controller.common.ResAccessMethod;
import com.controller.common.ResAccessUrl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;


public class BS_DeleteBookPrinters extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            if (request.getSession().getAttribute("user") != null) {
                int id = Integer.parseInt(request.getParameter("id"));

                String URL = ResAccessUrl.BOOK_SERVICE + "BookPrinter/" +id;
                String METHOD = "DELETE";
                String res = new ResAccessMethod().callMethod(URL, METHOD);

                if (res.isEmpty()) {
                    response.sendRedirect("type_management.jsp?error=Book printer delete failed");
                } else {
                    JSONObject obj2 = new JSONObject(res);
                    if (obj2.getBoolean("result")) {
                        response.sendRedirect("type_management.jsp?error=Successfully deleted");
                    } else {
                        response.sendRedirect("type_management.jsp?error=" + obj2.getString("msg"));
                    }

                }

//            PrintWriter out = response.getWriter();
//            out.println(res);
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("type_management.jsp?error=Book printer delete failed");
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
