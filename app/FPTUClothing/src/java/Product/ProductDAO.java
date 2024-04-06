package Product;

import DBUtils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // create CRUD
    public boolean insert(ProductDTO product) throws SQLException {
        String sql = "insert into tableProduct(nameProduct, description, price, productQuantity, category)\n"
                + " values (?, ?, ?, ?, ?)";
        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, product.getNameProduct());
            ps.setString(2, product.getDescription());
            ps.setInt(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setString(5, product.getCategory());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Insert product error!" + ex.getMessage());
        }
        return false;
    }

    public List<ProductDTO> listProduct() throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection cn = DBUtils.getConnection();
        if (cn != null) {
            String sql = "select productID, nameProduct, description, price, productQuantity, category, urlimage\n"
                    + "from tableProduct\n";

            PreparedStatement pst = cn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int pdid = rs.getInt("productID");
                String name = rs.getString("nameProduct");
                String des = rs.getString("description");
                int price = rs.getInt("price");
                int quantity = rs.getInt("productQuantity");
                String category = rs.getString("category");
                String urlimage = rs.getString("urlimage");

                list.add(new ProductDTO(pdid, name, des, category, price, quantity, urlimage));

            }
            cn.close();
        }
        return list;
    }

    public boolean update(ProductDTO productID) throws SQLException {
        String sql = "update tableProduct\n"
                + " set description = ?, price = ?, quantity = ?\n"
                + "where productID = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, productID.getDescription());
            ps.setInt(2, productID.getPrice());
            ps.setInt(3, productID.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) throws SQLException {
        String sql = "delete from tableProduct\n"
                + " where productID = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ProductDTO load(int id) throws SQLException {
        ProductDTO result = null;
        Connection cn = DBUtils.getConnection();
        if (cn != null) {
            String sql = "select productID, nameProduct, description, price, productQuantity, category, urlimage\n"
                    + "from tableProduct\n"
                    + "where productID = ?";

            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int pdid = rs.getInt("productID");
                String name = rs.getString("nameProduct");
                String des = rs.getString("description");
                int price = rs.getInt("price");
                int quantity = rs.getInt("productQuantity");
                String category = rs.getString("category");
                String urlimage = rs.getString("urlimage");
                result = new ProductDTO(pdid, name, des, category, price, quantity, urlimage);
            }
            cn.close();

        }
        return result;
    }

    public int addProductImg(String url, int productId) {
        String sql = "update  tableProduct set urlimage = ? where productID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, url);
            ps.setInt(2, productId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productId;
    }
}
