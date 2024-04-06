/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Account.AccountDAO;
import Account.AccountDTO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Acer
 */
public class LoginController extends HttpServlet {

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
        
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (action != null && action.equals("logout")){
            HttpSession session = request.getSession(false);

            if (session != null)
                session.invalidate();  
                response.sendRedirect("login.jsp");
        }else{

            log("Debug user : " + username + " " + password);

            if (username == null && password == null ){

                log("Debug user : Go to login " + username + " " + password);
                RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }else{

                log("Debug user : Go to here " + username + " " + password);
                
                AccountDAO dao = new AccountDAO();
                AccountDTO user = new AccountDTO();
                

                user = dao.checkLogin(username, password);

                

                if (user != null){                        
                    HttpSession session = request.getSession(true);
                    session.setAttribute("usersession", user);
                    
                    if(user.getRole().equals("AD")){
                        response.sendRedirect("admin");
                        
                    }else{
                        
                        response.sendRedirect("customer");
                    }
                    
                }else{      
                    request.setAttribute("error", "Wrong username or password");            
                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                    rd.forward(request, response);
                }              
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
