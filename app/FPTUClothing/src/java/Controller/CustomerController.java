/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Account.AccountDAO;
import Account.AccountDTO;
import Order.OrderDAO;
import Order.OrderDTO;
import OrderDetail.OrderDetailDAO;
import OrderDetail.OrderDetailDTO;
import Product.ProductDAO;
import Product.ProductDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Acer
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/customer"})
public class CustomerController extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        
        String action = request.getParameter("action");
        
        ProductDAO productDao = new ProductDAO();
        OrderDetailDAO orderDetailDao = new OrderDetailDAO();
        OrderDAO orderDao = new OrderDAO();
        AccountDAO accountDao = new AccountDAO();

        // Check security
        HttpSession session = request.getSession(false);
        AccountDTO currentUser = null;

        if (session != null) {
            
            currentUser = (AccountDTO) session.getAttribute("usersession");
        }

        log("Debug: " + currentUser);

        if (currentUser == null) {
            log("Debug: Redirect to login page" + currentUser);
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        //--------------------------------------------------------------------
        if (action == null || action.equals("productlist")) {
            try {

                List<ProductDTO> productList = productDao.listProduct();

                request.setAttribute("productlist", productList);

                RequestDispatcher rd = request.getRequestDispatcher("customerhomepage.jsp");
                rd.forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (action.equals("productdetail")) {
            int id = -1;
            
            try {
                id = Integer.parseInt(request.getParameter("productid"));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }

            ProductDTO product = new ProductDTO();

            try {
                product = productDao.load(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            action= "productdetailcustomer";
            request.setAttribute("action", action);
            request.setAttribute("product", product);

            RequestDispatcher rd = request.getRequestDispatcher("productdetail.jsp");
            rd.forward(request, response);

        } else if (action.equals("addtocart")) {

            int productId = -1;
            try {
                productId = Integer.parseInt(request.getParameter("productid"));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }

            int orderQuantity = -1;
            try {
                orderQuantity = Integer.parseInt(request.getParameter("orderquantity"));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            
            
            if (session.getAttribute("cart") == null) {
                //Map<productId,orderQuantity>
                Map<Integer, Integer> cart = new HashMap();

                cart.put(productId, orderQuantity);

                session.setAttribute("cart", cart);
            } else {
                Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

                cart.put(productId, orderQuantity);

                session.setAttribute("cart", cart);
            }
            
            response.sendRedirect("customer");
            
            
        } else if (action.equals("viewcart")) {

            if (session.getAttribute("cart") == null) {

                request.setAttribute("message", "there is no product");
                RequestDispatcher rd = request.getRequestDispatcher("viewcart.jsp");
                rd.forward(request, response);
            }
            
            Map<Integer, Integer> cart = (Map<Integer, Integer>)session.getAttribute("cart");
            List<ProductDTO> products = new ArrayList();
            ProductDTO product = new ProductDTO();
            
            for(Integer productId : cart.keySet()){
                product = productDao.load(productId);
                product.setOrderQuantity(cart.get(productId));
                products.add(product);
                
            }
            
            session.setAttribute("cart", cart);
            request.setAttribute("productlist", products);
            
            RequestDispatcher rd = request.getRequestDispatcher("viewcart.jsp");
            rd.forward(request, response);

        } else if (action.equals("removefromcart")) {

            int productId = -1;

            try {
                productId = Integer.parseInt(request.getParameter("productid"));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }

            Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

            cart.remove(productId);

            session.setAttribute("cart", cart);

            response.sendRedirect("customer");

        } else if (action.equals("vieworderdetail")) {

            if (session.getAttribute("cart") == null) {

                String message = "there is no product";
                request.setAttribute("message", message);

                RequestDispatcher rd = request.getRequestDispatcher("viewcart.jsp");
                rd.forward(request, response);
            }
            
            Map<Integer, Integer> cart = (Map<Integer,Integer>) session.getAttribute("cart");
            List<ProductDTO> productInCart = new ArrayList();
            int total = 0;
            try{
                
                for(int productId : cart.keySet()){
                    ProductDTO product = productDao.load(productId);
                    total = total + (cart.get(productId)* product.getPrice());
                    productInCart.add(product);
                }
                
            }catch(SQLException e){
                e.printStackTrace();
            }
            session.setAttribute("cart", cart);
            request.setAttribute("action", "confirmordercustomer");
            request.setAttribute("total", total);
            request.setAttribute("productincart", productInCart);
            request.setAttribute("orderdate", new Date());

            RequestDispatcher rd = request.getRequestDispatcher("confirmorder.jsp");
            rd.forward(request, response);

        } else if (action.equals("confirmorder")) {
            Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
            
            java.util.Date utilDate = new Date();
            java.sql.Date orderDate = new java.sql.Date(utilDate.getTime());
            
            OrderDTO order = new OrderDTO(orderDao.autogeneratedID(), currentUser.getAccountID(), orderDate, 0);
            int orderTotal = 0;
            
            try {

                for (int productId : cart.keySet()) {

                    ProductDTO product = productDao.load(productId);
                    int orderQuantity = cart.get(productId);
                    int total = orderQuantity * product.getPrice();
                    orderTotal = orderTotal + total;
                    
                    OrderDetailDTO orderDetail = new OrderDetailDTO(
                            order.getOrderID(),
                            productId,
                            orderQuantity,
                            total,
                            product.getPrice());

                    orderDetailDao.insert(orderDetail);
                    productDao.load(productId).setQuantity(product.getQuantity() - orderQuantity);
                    
                }
                
                order.setTotalPrice(orderTotal);
                orderDao.insert(order);

                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            cart.clear();
            session.setAttribute("cart", cart);

            response.sendRedirect("customer");

        } else if (action.equals("accountdetail")) {
            
            response.sendRedirect("accountdetail.jsp");
            
        } else if (action.equals("editaccount")) {
            request.setAttribute("action", "editaccount");
            
            RequestDispatcher rd = request.getRequestDispatcher("createaccount.jsp");
            rd.forward(request, response);

        } else if (action.equals("updateaccount")) {
            
            int id = ((AccountDTO) session.getAttribute("usersession")).getAccountID();
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String gmail = request.getParameter("gmail");
            String address = request.getParameter("address");
            String phonenum = request.getParameter("phonenumber"); 
            String role = "US";
            
            AccountDTO newaccount = new AccountDTO(id, username, password, gmail, address, role, phonenum);
            
            accountDao.update(newaccount);
            
            request.setAttribute("action", action);
            request.setAttribute("username", username);
            request.setAttribute("password", password);

            RequestDispatcher rd = request.getRequestDispatcher("login");
            rd.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
