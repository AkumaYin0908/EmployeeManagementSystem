/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.dto;

import java.math.BigDecimal;

/**
 *
 * @author Philip
 */
public class PositionDTO {
    private int positionId;
    private String position;
    private BigDecimal salary;

    public PositionDTO() {
    }

    
    public PositionDTO(int positionId, String position, BigDecimal salary) {
        this.positionId = positionId;
        this.position = position;
        this.salary = salary;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    
    
    
}
