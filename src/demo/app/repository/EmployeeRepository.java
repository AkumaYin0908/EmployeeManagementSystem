/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.repository;

import demo.app.dto.EmployeeDTO;
import demo.app.model.Employee;
import demo.app.model.Position;
import demo.app.model.SalaryGrade;
import demo.app.model.Status;
import java.util.List;



public interface EmployeeRepository {
    

    public List<EmployeeDTO> viewEmployee(Employee employee,Position position,SalaryGrade salaryGrade);
    
    public void addEmployee(Employee employee);
    
    public void updateEmployee(Employee employee);
    
    public void deleteEmployee(Employee employee);
    
    
    
    
}
