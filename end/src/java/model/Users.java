/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ngoct
 */
public class Users {
    private int User_id;
    private String User_name;
    private String User_password;
    private String User_tel;
    private String User_role;

    public Users() {
    }
    
    public Users(int User_id, String User_name, String User_password, String User_tel, String User_role) {
        this.User_id = User_id;
        this.User_name = User_name;
        this.User_password = User_password;
        this.User_tel = User_tel;
        this.User_role = User_role;
    }
    public Users(String User_name, String User_password, String User_tel, String User_role) {
        this(0,User_name,User_password,User_tel,User_role);
    }
    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int User_id) {
        this.User_id = User_id;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String User_name) {
        this.User_name = User_name;
    }

    public String getUser_password() {
        return User_password;
    }

    public void setUser_password(String User_password) {
        this.User_password = User_password;
    }

    public String getUser_tel() {
        return User_tel;
    }

    public void setUser_tel(String User_tel) {
        this.User_tel = User_tel;
    }

    public String getUser_role() {
        return User_role;
    }

    public void setUser_role(String User_role) {
        this.User_role = User_role;
    }

    @Override
    public String toString() {
        return "Users{" + "User_id=" + User_id + ", User_name=" + User_name + ", User_password=" + User_password + ", User_tel=" + User_tel + ", User_role=" + User_role + '}';
    }

    
   
    
    
}
