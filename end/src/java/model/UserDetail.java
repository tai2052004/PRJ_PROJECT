/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ngoct
 */
public class UserDetail {
    private int Users_id;
    private String fullname;
    private String DOB;

    public UserDetail() {
    }

    public UserDetail(int Users_id, String fullname, String DOB) {
        this.Users_id = Users_id;
        this.fullname = fullname;
        this.DOB = DOB;
    }

    public int getUsers_id() {
        return Users_id;
    }

    public void setUsers_id(int Users_id) {
        this.Users_id = Users_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return "UserDetail{" + "Users_id=" + Users_id + ", fullname=" + fullname + ", DOB=" + DOB + '}';
    }
    
    
    
}
