/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.query;

/**
 *
 * @author Philip
 */
public interface PositionQuery {
    
    String VIEW_POSITION="SELECT "
            + "position_id"
            + ", position"
            + ", salary_grade.salary"
            + " FROM position"
            + " LEFT JOIN salary_grade ON position.salary_grade=salary_grade.salary_grade";
    
    String SEARCH_POSITION="SELECT "
            + "position_id FROM position WHERE position_id=?";
}
