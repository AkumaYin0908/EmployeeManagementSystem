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

    String VIEW_EMPLOYEE = "SELECT "
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

    String ADD_EMPLOYEE = "INSERT INTO employee"
            + "(first_name"
            + ", mid_name"
            + ", last_name"
            + ", birth_date"
            + ", position) VALUES "
            + "(?, ?, ?, ?, ?);";

    String SEARCH_EMPLOYEE = "SELECT employee_id "
            + "FROM employee "
            + "WHERE employee_id=?";

    String UPDATE_FIRST_NAME = "UPDATE employee SET first_name=? WHERE employee_id=?";
    
    String UPDATE_LAST_NAME = "UPDATE employee SET last_name=? WHERE employee_id=?";
    
    String UPDATE_BIRTH_DATE="UPDATE employee SET birth_date=? WHERE employee_id=?";
    
    String UPDATE_POSITION="UPDATE employee SET position=? WHERE employee_id=?";
    
    String UPDATE_STATUS="UPDATE employee SET status=? WHERE employee_id=?";
    
    String DELETE_EMPLOYEE="DELETE FROM employee where employee_id=?";
}
