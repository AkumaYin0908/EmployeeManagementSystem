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
public class Employee extends Person {

    private int employeeId;
    private int position;
    private int employmentStatus;

    public Employee() {}

    public Employee(int employeeId, int position, int employmentStatus, String firstName, String midName, String lastName, LocalDate birthDate) {
        super(firstName, midName, lastName, birthDate);
        this.employeeId = employeeId;
        this.position = position;
        this.employmentStatus = employmentStatus;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(int employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    
    

    
    
    

}
