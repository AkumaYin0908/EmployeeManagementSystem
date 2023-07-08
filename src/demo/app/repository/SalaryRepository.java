/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.repository;

import demo.app.model.SalaryGrade;

/**
 *
 * @author User
 */
public interface SalaryRepository {
    
    public SalaryGrade findById(int salaryGrade);
    
    public void increaseSalary(SalaryGrade salary);
}
