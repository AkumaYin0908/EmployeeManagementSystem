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
public final class StatusQuery {

    private StatusQuery() {
    }

    public static final String VIEW_STATUS = "SELECT * FROM status";

    public static final String SEARCH_STATUS = "SELECT status_id "
            + "FROM status WHERE status_id=?";

}
