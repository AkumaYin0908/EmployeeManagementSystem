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
public class EmploymentStatus {
    private int  employmentId;
    private LocalDate date;
    private int status;

    public EmploymentStatus() {
    }

    public EmploymentStatus(int employmentId, LocalDate date, int status) {
        this.employmentId = employmentId;
        this.date = date;
        this.status = status;
    }

    public int getEmploymentId() {
        return employmentId;
    }

    public void setEmploymentId(int employmentId) {
        this.employmentId = employmentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

   
            
}
