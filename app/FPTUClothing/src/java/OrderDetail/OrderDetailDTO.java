/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OrderDetail;

/**
 *
 * @author Acer
 */
public class OrderDetailDTO {
    private int orderId;
    private int productId;
    private int orderedQuatity;
    private int totalPrice;
    private int unitPrice;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderId, int productId, int orderedQuatity, int totalPrice, int unitPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.orderedQuatity = orderedQuatity;
        this.totalPrice = totalPrice;
        this.unitPrice = unitPrice;
    }

    public int getOrderedQuatity() {
        return orderedQuatity;
    }

    public void setOrderedQuatity(int orderedQuatity) {
        this.orderedQuatity = orderedQuatity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    
}
