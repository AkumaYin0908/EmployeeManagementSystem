/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.constants;

/**
 *
 * @author User
 */
public interface EmployeeQuery {
    
    
   String SEARCH_EMPLOYEE="SELECT * from employee where employee_id=?";
   
   String ADD_EMPLOYEE="INSERT INTO employee"
           + "(first_name"
           + ", mid_name"
           + ", last_name"
           + ", birth_date"
           + ", position"
           + ", emp_status) values "
           + "(?, ?, ?, ?, ?, ?);";
}
