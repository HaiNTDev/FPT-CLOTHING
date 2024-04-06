/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Account.AccountDAO;
import Account.AccountDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Acer
 */
public class CreateAccountController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private boolean isEmpty(String string){
        if(string.isEmpty() || string == null || string == ""){
            return true;
        }
        return false;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String gmail = request.getParameter("gmail");
        String address = request.getParameter("address");
        String role = "US";
        String phoneNumber = request.getParameter("phonenumber");
        
        if(isEmpty(username) || isEmpty(password) || isEmpty(gmail) || isEmpty(address) || isEmpty(phoneNumber)){
            String message = "There is a null field, pls fill all information to create account";
            
            request.setAttribute("message", message);
            
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
        
        AccountDTO user = new AccountDTO(username, password, gmail, address, role, phoneNumber);
        AccountDAO dao = new AccountDAO();

        try{
            dao.insert(user);

        }catch(SQLException e){
            e.printStackTrace();
        }
        
        response.sendRedirect("login");
        
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
