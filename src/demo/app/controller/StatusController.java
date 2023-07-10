/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.controller;

import demo.app.connection.DBConnection;
import demo.app.model.Status;
import demo.app.query.StatusQuery;
import demo.app.repository.StatusRepository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class StatusController implements StatusRepository {
    
    private final DBConnection CONNECTION;
    private PreparedStatement statement;
    private ResultSet result;
    
    public StatusController(DBConnection CONNECTION) {
        this.CONNECTION = CONNECTION;
    }
    
    @Override
    public List<Status> viewStatus(Status status) {
        List<Status> statusList = new ArrayList();
        try {
            
            statement = CONNECTION.getSqlConnection().prepareStatement(StatusQuery.VIEW_STATUS);
            result = statement.executeQuery();
            
            while (result.next()) {
                status.setStatusId(result.getInt("status_id"));
                status.setStatus(result.getString("status"));
                
                statusList.add(status);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return statusList;
    }


}
