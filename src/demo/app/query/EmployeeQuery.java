/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.query;

/**
 *
 * @author User
 */
public interface EmployeeQuery {
    
   
   String VIEW_EMPLOYEE="SELECT "
           + "employee_id"
           + ", first_name"
           + ", mid_name"
           + ", last_name"
           + ", birth_date"
           + ", position.position"
           + ", salary_grade.salary"
           + ", status.status"
           + " FROM employee "
           + "LEFT JOIN position ON employee.position=position.position "
           + "LEFT JOIN salary_grade ON position.salary_grade=salary_grade.salary_grade "
           + "LEFT JOIN status ON employee.status=status.status where employee.status=?;";
   
   String ADD_EMPLOYEE="INSERT INTO employee"
           + "(first_name"
           + ", mid_name"
           + ", last_name"
           + ", birth_date"
           + ", position) VALUES "
           + "(?, ?, ?, ?, ?);";
}
