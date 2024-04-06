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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AdminController extends HttpServlet {

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

        //product management
        if(action == null ||action.equals("productlist")){
            try{
            
            List<ProductDTO> productList = productDao.listProduct();
            
            request.setAttribute("productlist", productList);
            
            RequestDispatcher rd = request.getRequestDispatcher("productmanagement.jsp");
            rd.forward(request, response);
            
            }catch(SQLException e){
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
            action="productdetailadmin";
            request.setAttribute("action", action);
            request.setAttribute("product", product);

            RequestDispatcher rd = request.getRequestDispatcher("productdetail.jsp");
            rd.forward(request, response);

        } else if (action.equals("productedit")) {

            int id = -1;

            try {
                id = Integer.parseInt(request.getParameter("productid"));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }

            request.setAttribute("productid", id);
            request.setAttribute("action", action);

            RequestDispatcher rd = request.getRequestDispatcher("productedit.jsp");
            rd.forward(request, response);

        } else if (action.equals("productupdate")) {

            int id = -1;
            try {
                id = Integer.parseInt(request.getParameter("productid"));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            String productName = request.getParameter("productname");
            String description = request.getParameter("description");
            String category = request.getParameter("category");

            int price = -1;

            try {
                price = Integer.parseInt(request.getParameter("productprice"));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }

            int quantity = -1;

            try {
                quantity = Integer.parseInt(request.getParameter("productquantity"));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }

            ProductDTO product = new ProductDTO(productName, description, category, price, quantity);
            product.setProductID(id);

            try {

                productDao.update(product);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("productid", id);

            RequestDispatcher rd = request.getRequestDispatcher("productdetail.jsp");
            rd.forward(request, response);

        } else if (action.equals("productinsert")) {

            String productName = request.getParameter("productname");
            String description = request.getParameter("description");
            String category = request.getParameter("category");

            int price = -1;

            try {
                price = Integer.parseInt(request.getParameter("productprice"));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }

            int quantity = -1;

            try {
                quantity = Integer.parseInt(request.getParameter("productquantity"));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }

            ProductDTO product = new ProductDTO(productName, description, category, price, quantity);

            try {

                productDao.insert(product);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            action = "productlist";
            request.setAttribute("action", action);
            RequestDispatcher rd = request.getRequestDispatcher("admin");
            rd.forward(request, response);

        } else if (action.equals("productcreate")) {

            request.setAttribute("action", action);

            RequestDispatcher rd = request.getRequestDispatcher("productdetail.jsp");
            rd.forward(request, response);

        } else if(action.equals("productdelete")){
            
            int id = -1;
            try {
                id = Integer.parseInt(request.getParameter("productid"));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            try {

                productDao.delete(id);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            action = "productlist";
            request.setAttribute("action", action);
            RequestDispatcher rd = request.getRequestDispatcher("admin");
            rd.forward(request, response);
            
        } else if(action.equals("productimage")){
            String image = request.getParameter("image");
            
            int id = -1;
            try {
                id = Integer.parseInt(request.getParameter("productid"));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            
            productDao.addProductImg(image, id);
            
            action = "productdetail";
            request.setAttribute("action", action);
            request.setAttribute("productid", id);
        }
        //-------------------------------------------------------------------------------
        //order management
        if (action.equals("orderlist")) {
            try {

                List<OrderDTO> orderlist = orderDao.listOrder();

                request.setAttribute("list", orderlist);

                RequestDispatcher rd = request.getRequestDispatcher("ordermanagement.jsp");
                rd.forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (action.equals("orderdetail")) {
            int id = -1;
            OrderDetailDAO orderdetailDao = new OrderDetailDAO();

            try {
                id = Integer.parseInt(request.getParameter("orderid"));

            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            OrderDetailDTO order = new OrderDetailDTO();
            List<OrderDetailDTO> orderdetaillist = orderdetailDao.getListByOrderId(id);
            action ="showorderdetail";
            request.setAttribute("action", action);
            request.setAttribute("order", order);
            request.setAttribute("orderdetaillist", orderdetaillist);

            RequestDispatcher rd = request.getRequestDispatcher("confirmorder.jsp");
            rd.forward(request, response);

        }else if (action.equals("orderdelete")) {
                int id = -1;
                try {
                    
                    id = Integer.parseInt(request.getParameter("id"));
                    if (id != -1) {
                        orderDao.delete(id);
                    }

                    List<OrderDTO> orderlist = orderDao.listOrder();

                    request.setAttribute("list", orderlist);

                    RequestDispatcher rd = request.getRequestDispatcher("ordermanagement.jsp");
                    rd.forward(request, response);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (action.equals("finishorder")) {
                try {
                    int id = -1;
                    id = Integer.parseInt(request.getParameter("id"));
                    if (id != -1) {
                        orderDao.finishorder(id);
                    }

                    List<OrderDTO> orderlist = orderDao.listOrder();

                    request.setAttribute("list", orderlist);

                    RequestDispatcher rd = request.getRequestDispatcher("ordermanagement.jsp");
                    rd.forward(request, response);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //-------------------------------------------------------------------------------
            //customer management
            if (action.equals("customerlist")) {
                try {
                    List<AccountDTO> customerlist = accountDao.listUser();

                    request.setAttribute("list", customerlist);

                    RequestDispatcher rd = request.getRequestDispatcher("customermanagement.jsp");
                    rd.forward(request, response);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (action.equals("customerdelete")) {
                int id = -1;
                
                try {
                    
                    
                    id = Integer.parseInt(request.getParameter("id"));
                    
                    if (id != -1) {
                        accountDao.delete(id);
                    }
                    
                    List<AccountDTO> customerlist = accountDao.listUser();

                    request.setAttribute("list", customerlist);

                    RequestDispatcher rd = request.getRequestDispatcher("customermanagement.jsp");
                    rd.forward(request, response);
                    
                } catch (Exception e) {
                    e.printStackTrace();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
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
    }

}
