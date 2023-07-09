/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.view;

import demo.app.connection.DBConnection;
import demo.app.controller.PositionController;
import demo.app.dto.PositionDTO;
import demo.app.model.Position;
import demo.app.model.SalaryGrade;
import java.util.List;

/**
 *
 * @author Philip
 */
public class PositionForm {

    public static void positionInit(DBConnection connection) {
        Position position = new Position();
        SalaryGrade salaryGrade = new SalaryGrade();
        PositionController positionController = new PositionController(connection);

        List<PositionDTO> positionList = positionController.viewPosition(position, salaryGrade);

        System.out.println("************************************** CHOOSE POSITION **************************************");
        System.out.printf("%s\t\t%-40s\t%-5s\n",
                "Position ID",
                "Position",
                "Salary");

        for (PositionDTO positionDTO : positionList) {
            System.out.printf("%s\t\t%-40s\t%,-5.2f\n",
                    positionDTO.getPositionId(),
                    positionDTO.getPosition(),
                    positionDTO.getSalary());
        }
    }

}
