/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.repository;

import demo.app.model.HRAdmin;

/**
 *
 * @author User
 */
public interface HRAdminRepository {
    
    public HRAdmin findById(int hrAdminId);
    
    public void login(HRAdmin hrAdmin);
    
    public void logout(HRAdmin hrAdmin);
    
    public void register(HRAdmin hrAdmin);
    
    
}
