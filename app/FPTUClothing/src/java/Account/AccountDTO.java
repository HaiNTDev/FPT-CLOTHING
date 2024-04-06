/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account;

/**
 *
 * @author Acer
 */
public class AccountDTO {
    private int accountID;
    private String username;
    private String password;
    private String gmail;
    private String address;
    private String role;
    private String phoneNum;

    public AccountDTO() {
    }

    public AccountDTO(String username, String password, String gmail, String address, String role, String phoneNum) {
        this.username = username;
        this.password = password;
        this.gmail = gmail;
        this.address = address;
        this.role = role;
        this.phoneNum = phoneNum;
    }

    public AccountDTO(String password, String gmail, String address, String phoneNum) {
        this.password = password;
        this.gmail = gmail;
        this.address = address;
        this.phoneNum = phoneNum;
    }

    public AccountDTO(int accountID, String username, String gmail, String address, String phoneNum) {
        this.accountID = accountID;
        this.username = username;
        this.gmail = gmail;
        this.address = address;
        this.phoneNum = phoneNum;
    }

    public AccountDTO(int accountID, String username, String gmail, String address, String role, String phoneNum) {
        this.accountID = accountID;
        this.username = username;
        this.gmail = gmail;
        this.address = address;
        this.role = role;
        this.phoneNum = phoneNum;
    }

    public AccountDTO(int accountID, String username, String password, String gmail, String address, String role, String phoneNum) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.gmail = gmail;
        this.address = address;
        this.role = role;
        this.phoneNum = phoneNum;
    }

    public AccountDTO(AccountDTO another) {
        this.accountID = another.getAccountID();
        this.username = another.getUsername();
        this.password = another.getPassword();
        this.gmail = another.getGmail();
        this.address = another.getAddress();
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    
    
}
