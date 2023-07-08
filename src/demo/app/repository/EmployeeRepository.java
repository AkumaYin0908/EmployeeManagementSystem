/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.repository;

import demo.app.model.Employee;
import demo.app.model.EmploymentStatus;
import demo.app.model.Position;
import demo.app.model.SalaryGrade;
import demo.app.model.Status;



public interface EmployeeRepository {
    
    public Employee findById(int employeeId);
    
    public void viewEmployee(Employee employee,Position position,SalaryGrade salaryGrade, EmploymentStatus employmentStatus);
    
    public void addEmployee(Employee employee);
    
    public void updateEmployee(Employee employee);
    
    public void deleteEmployee(Employee employee);
    
    
    
    
}
