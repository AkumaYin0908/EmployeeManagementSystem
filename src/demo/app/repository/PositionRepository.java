/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.repository;

import demo.app.dto.PositionDTO;
import demo.app.model.Position;
import demo.app.model.SalaryGrade;
import java.util.List;

/**
 *
 * @author User
 */
public interface PositionRepository {

    public List<PositionDTO> viewPosition(Position position, SalaryGrade salaryGrade);

    public void addPosition(Position position);

    public void updatePosition(Position position);

    public void deletePosition(Position position);
}
