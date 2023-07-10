/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.repository;

import demo.app.model.Status;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public interface StatusRepository {
    
    public List<Status> viewStatus(Status status);
}