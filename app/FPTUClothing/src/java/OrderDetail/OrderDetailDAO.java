/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OrderDetail;

import DBUtils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class OrderDetailDAO {

    public OrderDetailDTO load(int orderId) throws SQLException {
        OrderDetailDTO result = null;

        Connection cn = DBUtils.getConnection();
        if (cn != null) {
            String sql = "select orderID, productID, orderQuantity, unitprice, total\n"
                    + "from tableOrderDetail\n"
                    + "where orderID = ?";

            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, orderId);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int orderID = rs.getInt("orderID");
                int productID = rs.getInt("productID");
                int orderQuantity = rs.getInt("orderQuantity");
                int unitprice = rs.getInt("unitprice");
                int total = rs.getInt("total");
                result = new OrderDetailDTO(orderID, productID, orderQuantity, total, unitprice);

            }
            cn.close();
        }
        return result;

    }
    
    public boolean insert(OrderDetailDTO orderD) throws SQLException {
        String sql = "insert into tableOrderDetail(orderID, productID, orderQuantity, unitprice, total)\n"
                + " values (?, ?, ?, ?, ?)";
        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, orderD.getOrderId());
            ps.setInt(2, orderD.getProductId());
            ps.setInt(3, orderD.getOrderedQuatity());
            ps.setInt(4, orderD.getUnitPrice());
            ps.setInt(5, orderD.getTotalPrice());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Insert order details error!" + ex.getMessage());
        }
        return false;
    }
    
    public List<OrderDetailDTO> getListByOrderId(int orderID) throws SQLException {
        List<OrderDetailDTO> list = new ArrayList<>();
        Connection cn = DBUtils.getConnection();
        if (cn != null) {
            String sql = "select orderID, productID, orderQuantity, unitprice, total\n"
                    + "from tableOrderDetail\n"
                    + "where orderID = ?";

            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, orderID);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                int productID = rs.getInt("productID");
                int orderQuantity = rs.getInt("orderQuantity");
                int unitprice = rs.getInt("unitprice");
                int total = rs.getInt("total");

                
                list.add(new OrderDetailDTO(orderID, productID, orderQuantity, unitprice, total));

            }
            cn.close();
        }
        return list;
    }
}
