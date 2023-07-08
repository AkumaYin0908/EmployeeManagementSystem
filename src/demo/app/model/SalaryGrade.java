/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.model;

import java.math.BigDecimal;

/**
 *
 * @author User
 */
public class SalaryGrade {
    private int salaryGrade;
    private BigDecimal salary;

    public SalaryGrade() {
    }

    public SalaryGrade(int salaryGrade, BigDecimal salary) {
        this.salaryGrade = salaryGrade;
        this.salary = salary;
    }

    public int getSalaryGrade() {
        return salaryGrade;
    }

    public void setSalaryGrade(int salaryGrade) {
        this.salaryGrade = salaryGrade;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    
    
}
