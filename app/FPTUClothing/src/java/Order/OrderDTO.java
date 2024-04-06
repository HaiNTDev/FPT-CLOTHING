/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order;

import java.util.Date;

/**
 *
 * @author Acer
 */
public class OrderDTO {
    private int orderID;
    private int accountID;
    private Date orderDate;
    private int totalPrice;
    private String status;

    public OrderDTO() {
    }

    public OrderDTO(int orderID, int accountID, Date orderDate, int totalPrice) {
        this.orderID = orderID;
        this.accountID = accountID;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.status = "ongoing";
    }

    public OrderDTO(Date orderDate, int totalPrice, String status) {
        
        this.status = "ongoing";
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    

    public OrderDTO(OrderDTO another) {
        this.orderID = another.getOrderID();
        this.accountID = another.getAccountID();
        this.orderDate = another.getOrderDate();
        this.totalPrice = another.getTotalPrice();
    }

   

   

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
