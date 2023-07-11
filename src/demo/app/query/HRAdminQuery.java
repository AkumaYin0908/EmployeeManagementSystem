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
public final class HRAdminQuery {

    private HRAdminQuery() {
    }

    public static final String SEARCH_USERNAME_PASSWORD = "SELECT "
            + "user_name"
            + ", password "
            + "FROM hr_admin WHERE "
            + "user_name = ? and "
            + "password=?";

    public static final String ADD_HRADMIN = "INSERT INTO hr_admin"
            + "(first_name"
            + ", mid_name"
            + ", last_name"
            + ", birth_date"
            + ", user_name"
            + ", password)"
            + " VALUES(?,?,?,?,?,?)";

    public static final String SEARCH_USERNAME = "SELECT user_name FROM hr_admin WHERE user_name=?";
}
