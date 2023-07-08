/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.model;

import java.time.LocalDate;

/**
 *
 * @author User
 */
public class HRAdmin extends Person {

    private int hrAdminId;
    private String userName;
    private String password;

    public HRAdmin() {
    }

    public HRAdmin(int hrAdminId, 
            String userName, 
            String password, 
            String firstName, 
            String midName, 
            String lastName, 
            LocalDate birthDate) {
        
        super(firstName, midName, lastName, birthDate);
        this.hrAdminId = hrAdminId;
        this.userName = userName;
        this.password = password;
    }

    public int getHrAdminId() {
        return hrAdminId;
    }

    public void setHrAdminId(int hrAdminId) {
        this.hrAdminId = hrAdminId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

}
