/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.view;

import demo.app.connection.DBConnection;
import demo.app.controller.EmployeeController;
import demo.app.controller.HRAdminController;
import demo.app.controller.PositionController;
import demo.app.dto.EmployeeDTO;
import demo.app.dto.PositionDTO;
import demo.app.model.Employee;
import demo.app.model.HRAdmin;
import demo.app.model.Position;
import demo.app.model.SalaryGrade;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Philip
 */
public class MainForm {
    
    public static String userName;
    
    public static void mainInit(DBConnection connection) {
        EmployeeController employeeController = new EmployeeController(connection);
        Employee employee = new Employee();
        Position position = new Position();
        SalaryGrade salaryGrade = new SalaryGrade();
        System.out.printf("EMPLOYEE MANAGEMENT SYSTEM V 1.0.0  %50s%n", "User: " + userName);
        System.out.println("[1] View Employee"
                + "\n[2] Add Employee"
                + "\n[3] Update Employee"
                + "\n[4] Delete Employee"
                + "\n[5] Increase Positions' Salary"
                + "\n[6] Update Position"
                + "\n[7] Remove Position"
                + "\n[8] Logout");
        Scanner scanner = new Scanner(System.in);
        try {
            
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    
                    List<EmployeeDTO> employeeList = employeeController.viewEmployee(employee, position, salaryGrade);
                    System.out.println("************************************** VIEW EMPLOYEE **************************************");
                    System.out.printf("%s\t%-10s %-15s %-10s\t\t%-20s\t%-10s\t%-40s\t%-5s\n\n",
                            "ID",
                            "First Name",
                            "Middle Name",
                            "Last Name",
                            "Birth Date",
                            "Age",
                            "Position",
                            "Salary");
                    
                    for (EmployeeDTO employeeDTO : employeeList) {
                        System.out.printf("%s\t%-10s %-15s %-10s\t\t%-20s\t%-10s\t%-40s\t%-5s\n\n",
                                employeeDTO.getEmployeeId(),
                                employeeDTO.getFirstName(),
                                employeeDTO.getMidName(),
                                employeeDTO.getLastName(),
                                employeeDTO.getBirthDate(),
                                employeeDTO.getAge(),
                                employeeDTO.getPosition(),
                                employeeDTO.getSalary());
                    }
                    System.out.println("***************************************************************************************\n");
                    MainForm.mainInit(connection);
                    
                    break;
                case 2:
                    
                    System.out.println("************************************** ADD EMPLOYEE **************************************");
                    employeeController.addEmployee(employee);
                    System.out.println("***************************************************************************************\n");
                    MainForm.mainInit(connection);
                    
                    break;
                case 3:
                    System.out.println("************************************** UPDATE EMPLOYEE **************************************");
                    employeeController.updateEmployee(employee);
                    System.out.println("***************************************************************************************\n");
                    break;
                case 4:
                    System.out.println("************************************** DELETE EMPLOYEE **************************************");
                    employeeController.deleteEmployee(employee);
                    System.out.println("***************************************************************************************\n");
                    MainForm.mainInit(connection);
                    
                    break;
                default:
                    System.out.println("Invalid input. Try again");
                    LoginForm.accountInit(connection);
            }
        } catch (InputMismatchException ex) {
            System.err.println("Only numbers can be inputted!");
            LoginForm.accountInit(connection);
        } finally {
            scanner.close();
        }
    }
    
}
