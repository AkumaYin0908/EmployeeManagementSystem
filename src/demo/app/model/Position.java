/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.model;

/**
 *
 * @author User
 */
public class Position {
    private int positionId;
    private String position;
    private int salaryGrade;

    public Position() {
    }

    public Position(int positionId, String position, int salaryGrade) {
        this.positionId = positionId;
        this.position = position;
        this.salaryGrade = salaryGrade;
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

    public int getSalaryGrade() {
        return salaryGrade;
    }

    public void setSalaryGrade(int salaryGrade) {
        this.salaryGrade = salaryGrade;
    }

 
    
}
