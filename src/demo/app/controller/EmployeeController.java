/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.controller;

import com.mysql.jdbc.Statement;
import demo.app.connection.DBConnection;
import demo.app.query.EmployeeQuery;
import demo.app.dto.EmployeeDTO;
import demo.app.dto.PositionDTO;
import demo.app.model.Employee;
import demo.app.model.Position;
import demo.app.model.SalaryGrade;
import demo.app.model.Status;
import demo.app.repository.EmployeeRepository;
import demo.app.utils.BirthDateValidator;
import demo.app.view.MainForm;
import demo.app.view.PositionForm;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class EmployeeController implements EmployeeRepository {

    private final DBConnection CONNECTION;
    private PreparedStatement statement;
    private ResultSet result;
    private Scanner scanner;

    public EmployeeController(DBConnection CONNECTION) {
        this.CONNECTION = CONNECTION;
        CONNECTION.connect();
    }

    @Override
    public List<EmployeeDTO> viewEmployee(Employee employee, Position position, SalaryGrade salaryGrade) {
        List<EmployeeDTO> employeeList = new ArrayList<>();

        try {
            String chosenOption = chooseViewOption();
            if (chosenOption == null) {
                MainForm.mainInit(CONNECTION);
            } else {
                statement = CONNECTION.getSqlConnection().prepareStatement(EmployeeQuery.VIEW_EMPLOYEE);
                statement.setString(1, chosenOption);
                result = statement.executeQuery();

                while (result.next()) {

                    employee.setEmployeeId(result.getInt("employee_id"));
                    employee.setFirstName(result.getString("first_name"));
                    employee.setMidName(result.getString("mid_name"));
                    employee.setLastName(result.getString("last_name"));
                    employee.setBirthDate(result.getDate("birth_date").toLocalDate());
                    position.setPosition(result.getString("position"));
                    salaryGrade.setSalary(result.getBigDecimal("salary"));

                    employeeList.add(convertToEmployeeDTO(employee, position, salaryGrade));

                }

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            MainForm.mainInit(CONNECTION);
        }
        return employeeList;
    }

    @Override
    public void addEmployee(Employee employee) {

        try {
            employee = getInput();
            statement = CONNECTION.getSqlConnection().prepareStatement(EmployeeQuery.ADD_EMPLOYEE, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getMidName());
            statement.setString(3, employee.getLastName());
            statement.setDate(4, Date.valueOf(employee.getBirthDate()));
            statement.setInt(5, employee.getPosition());
            statement.executeUpdate();

            ResultSet id = statement.getGeneratedKeys();
            if (id.next()) {
                employee.setEmployeeId(id.getInt(1));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
            MainForm.mainInit(CONNECTION);
        }

    }

    @Override
    public void updateEmployee(Employee employee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteEmployee(Employee employee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String chooseViewOption() {
        scanner = new Scanner(System.in);
        try {
            System.out.println("Choose Option: "
                    + "[1] Employed Employees"
                    + "\n[2] Resigned Employees"
                    + "\n[3] Retired Employees"
                    + "\n[0] Cancel");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    return "EMPLOYED";

                case 2:
                    return "RESIGNED";

                case 3:
                    return "RETIRED";

                case 0:
                    MainForm.mainInit(CONNECTION);
                    return null;

                default:
                    System.out.println("Invalid input. Try again");
                    chooseViewOption();
            }
        } catch (InputMismatchException ex) {
            System.err.println(ex.getMessage() + " Try again: ");
            chooseViewOption();
        }
        return null;
    }

    private Employee getInput() {
        Employee employee = new Employee();
        scanner = new Scanner(System.in);
        try {
            System.out.println("Enter first name: ");
            employee.setFirstName(scanner.nextLine());

            System.out.println("Enter middle name: ");
            employee.setMidName(scanner.nextLine());

            System.out.println("Enter last name: ");
            employee.setLastName(scanner.nextLine());

            System.out.println("Enter birth date(MM/dd/yyyy): ");
            employee.setBirthDate(BirthDateValidator.validate(scanner));

            PositionForm.positionInit(CONNECTION);

            System.out.println("Enter selected position id: ");
            employee.setPosition(scanner.nextInt());

        } catch (InputMismatchException ex) {
            System.err.println(ex.getMessage());
            getInput();
        }
        return employee;
    }

    private EmployeeDTO convertToEmployeeDTO(Employee employee, Position position, SalaryGrade salaryGrade) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setMidName(employee.getLastName());
        employeeDTO.setBirthDate(employee.getBirthDate());
        employeeDTO.setAge(employee.getAge());
        employeeDTO.setPosition(position.getPosition());
        employeeDTO.setSalary(salaryGrade.getSalary());

        return employeeDTO;
    }

}
