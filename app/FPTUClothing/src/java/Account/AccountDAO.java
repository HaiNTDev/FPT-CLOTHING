package Account;

import DBUtils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public AccountDTO checkLogin(String username, String password){
        
        String sql = "SELECT accountID, username, password, gmail, address, role, phoneNumber "
                + " FROM tableAccount WHERE username = ? AND password = ? ";
        
        try{
            Connection cn = DBUtils.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                AccountDTO account = new AccountDTO(
                        rs.getInt("accountID")
                        , rs.getString("username")
                        , rs.getString("password")
                        , rs.getString("gmail")
                        , rs.getString("address")
                        , rs.getString("role")
                        , rs.getString("phoneNumber"));

                return account;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return null;
    }

    public boolean insert(AccountDTO username) throws SQLException {
        String sql = "insert into tableAccount(username, password, gmail, address, role, phoneNumber)\n"
                + " values (?, ?, ?, ?, ?, ?)";
        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username.getUsername());
            ps.setString(2, username.getPassword());
            ps.setString(3, username.getGmail());
            ps.setString(4, username.getAddress());
            ps.setString(5, username.getRole());
            ps.setString(6, username.getPhoneNum());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Insert account error!" + ex.getMessage());
        }
        return false;
    }

    public List<AccountDTO> listUser() throws SQLException {
        List<AccountDTO> list = new ArrayList<>();

        Connection cn = DBUtils.getConnection();
        if (cn != null) {
            String sql = "select accountID, username, gmail, address, phoneNumber\n"
                    + "from tableAccount\n"
                    + "where role like 'US' ";

            PreparedStatement pst = cn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int acc = rs.getInt("accountID");
                String name = rs.getString("username");
                String gmail = rs.getString("gmail");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");

                list.add(new AccountDTO(acc, name, gmail, address, phoneNumber));
            }
            cn.close();
        }
        return list;
    }
    
    

    public int update(AccountDTO account) throws SQLException {
        String sql = "UPDATE tableAccount \n"
                + " SET username = ?, password = ?, gmail = ?, address = ?, role = ?, phoneNumber = ? \n"
                + " WHERE accountID = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getGmail());
            ps.setString(4, account.getAddress());
            ps.setString(5, account.getRole());
            ps.setString(6, account.getPhoneNum());            
            ps.setInt(7, account.getAccountID());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account.getAccountID();
    }

    public boolean delete(int id) throws SQLException {
        String sql = "delete from tableAccount\n"
                + " where accountID = ?";
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
    
    public AccountDTO load(int id) throws SQLException {
        AccountDTO result = null;

        Connection cn = DBUtils.getConnection();
        if (cn != null) {
            String sql = "select accountID, username, password, gmail, address, role, phoneNumber\n"
                    + "from tableAccount\n"
                    + "where accountID = ?";

            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int acc = rs.getInt("accountID");
                String name = rs.getString("username");
                String pwd = rs.getString("password");
                String role = rs.getString("role");
                String gmail = rs.getString("gmail");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");

                result = new AccountDTO(acc, name, pwd, gmail, address, role, phoneNumber);
            }
            cn.close();
        }
        return result;
    }
}

