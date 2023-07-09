/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.controller;

import demo.app.connection.DBConnection;
import demo.app.dto.PositionDTO;
import demo.app.model.Position;
import demo.app.model.SalaryGrade;
import demo.app.query.PositionQuery;
import demo.app.repository.PositionRepository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Philip
 */
public class PositionController implements PositionRepository {

    private final DBConnection CONNECTION;
    private PreparedStatement statement;
    private ResultSet result;
    private Scanner scanner;

    public PositionController(DBConnection CONNECTION) {
        this.CONNECTION = CONNECTION;
        
    }

    @Override
    public List<PositionDTO> viewPosition(Position position, SalaryGrade salaryGrade) {
        List<PositionDTO> positionList = new ArrayList<>();
        try {

            statement = CONNECTION.getSqlConnection().prepareStatement(PositionQuery.VIEW_POSITION);
            result = statement.executeQuery();
            while (result.next()) {
                position.setPositionId(result.getInt("position_id"));
                position.setPosition(result.getString("position"));
                salaryGrade.setSalary(result.getBigDecimal("salary"));
                
                positionList.add(convertToPositionDTO(position,salaryGrade));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }finally {
            try{
                CONNECTION.getSqlConnection().close();
            }catch(SQLException e){
                e.printStackTrace();
            System.out.println(e.getMessage());
            }
        }
        return positionList;
    }

    @Override
    public void addPosition(Position position) {
        
    }

    @Override
    public void updatePosition(Position position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePosition(Position position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private PositionDTO convertToPositionDTO(Position position, SalaryGrade salaryGrade) {
        PositionDTO positionDTO = new PositionDTO();

        positionDTO.setPositionId(position.getPositionId());
        positionDTO.setPosition(position.getPosition());
        positionDTO.setSalary(salaryGrade.getSalary());

        return positionDTO;
    }

}
