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
public interface HRAdminQuery {
    
    String FIND_HRADMIN="SELECT "
            + "user_name"
            + ", password "
            + "FROM hr_admin WHERE "
            + "user_name = ? and "
            + "password=?";
    
    String ADD_HRADMIN="INSERT INTO hr_admin"
            + "(first_name"
            + ", mid_name"
            + ", last_name"
            + ", birth_date"
            + ", user_name"
            + ", password)"
            + " VALUES(?,?,?,?,?,?)";
    
    
    String SEARCH_USERNAME="SELECT user_name FROM hr_admin WHERE user_name=?";
}