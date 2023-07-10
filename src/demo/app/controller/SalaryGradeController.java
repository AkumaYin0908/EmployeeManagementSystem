/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.controller;

import demo.app.connection.DBConnection;
import demo.app.model.SalaryGrade;
import demo.app.query.SalaryGradeQuery;
import demo.app.view.MainView;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SalaryGradeController {

    private final DBConnection CONNECTION;

    private PreparedStatement statement;
    private ResultSet result;
    private Scanner scanner;

    public SalaryGradeController(DBConnection CONNECTION) {
        this.CONNECTION = CONNECTION;
    }

    public void increaseSalaryGrade(SalaryGrade salaryGrade) {

        BigDecimal increase = getInput();
        scanner = new Scanner(System.in);
        System.out.println("Are you sure you want to increase the salary?\n[1]Yes\n[2]No");
        int choice = scanner.nextInt();
        if (choice == 1) {
            try {
                statement = CONNECTION.getSqlConnection().prepareStatement(SalaryGradeQuery.VIEW_SALARY);
                result = statement.executeQuery();

                if (result.next()) {
                    salaryGrade.setSalary(result.getBigDecimal("salary"));
                    statement = CONNECTION.getSqlConnection().prepareStatement(SalaryGradeQuery.INCREASE_SALARY);
                    statement.setBigDecimal(1, salaryGrade.getSalary().add(increase));
                    statement.executeUpdate();
                    System.out.println("Salary of all positions has been increased");
                    System.out.println("***************************************************************************************\n");
                    MainView.mainInit(CONNECTION);
                } else {
                    System.out.println("No record of salary grades have been found.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.err.println(ex);
                MainView.mainInit(CONNECTION);
            } finally {
                scanner.close();
                try {
                    CONNECTION.getSqlConnection().close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                    MainView.mainInit(CONNECTION);
                }

            }
        }

    }

    private BigDecimal getInput() {
        BigDecimal increase = new BigDecimal(0);
        try {
            scanner = new Scanner(System.in);
            System.out.println("Enter amount increased per salary: ");
            increase = scanner.nextBigDecimal();
            System.out.println(increase);

        } catch (InputMismatchException ex) {
            System.out.println("Invalid input. Please try again: ");
            getInput();
        }
        return increase;
    }
}
