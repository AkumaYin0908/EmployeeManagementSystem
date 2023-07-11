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
import demo.app.query.PositionQuery;
import demo.app.query.StatusQuery;
import demo.app.repository.EmployeeRepository;
import demo.app.utils.BirthDateValidator;
import demo.app.view.MainView;
import demo.app.view.PositionView;
import demo.app.view.StatusView;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    }

    @Override
    public List<EmployeeDTO> viewEmployee(Employee employee, Position position, SalaryGrade salaryGrade) {
        List<EmployeeDTO> employeeList = new ArrayList<>();

        try {
            String chosenOption = chooseViewOption();
            if (chosenOption == null) {
                MainView.mainInit(CONNECTION);
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
            MainView.mainInit(CONNECTION);
        } finally {
            try {
                CONNECTION.getSqlConnection().close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                MainView.mainInit(CONNECTION);
            }
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
                int employeeId = id.getInt(1);
                System.out.println("Employee ID No. " + employeeId + " has been added to the database.");
            }

        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
            MainView.mainInit(CONNECTION);
        } finally {
            try {
                CONNECTION.getSqlConnection().close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                MainView.mainInit(CONNECTION);
            }
        }
    }

    @Override
    public void updateEmployee(Employee employee) {

        try {
            scanner = new Scanner(System.in);
            employee.setEmployeeId(verifyEmployeeId(scanner));
            chooseUpdateOption(employee);

            System.out.println("***************************************************************************************\n");
            MainView.mainInit(CONNECTION);

        } catch (SQLException ex) {
            System.err.println(ex);
            MainView.mainInit(CONNECTION);
        }finally {
            try {
                CONNECTION.getSqlConnection().close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                MainView.mainInit(CONNECTION);
            }
        }
    }

    @Override
    public void deleteEmployee(Employee employee) {

        try {
            scanner = new Scanner(System.in);
            employee.setEmployeeId(verifyEmployeeId(scanner));

            System.out.println("Are you sure you want to delete this employee?\n[1]Yes\n[2]No");
            int choice = scanner.nextInt();
            if (choice == 1) {

                statement = CONNECTION.getSqlConnection().prepareStatement(EmployeeQuery.DELETE_EMPLOYEE);
                statement.setInt(1, employee.getEmployeeId());
                statement.executeUpdate();
                System.out.println("Employee ID No." + employee.getEmployeeId() + " has been deleted");

            } else {
                System.out.println("Deletion canceled...");
                MainView.mainInit(CONNECTION);
            }

            System.out.println("***************************************************************************************\n");
            MainView.mainInit(CONNECTION);

        } catch (SQLException ex) {
            System.err.println(ex);
        } finally {
            try {
                CONNECTION.getSqlConnection().close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                MainView.mainInit(CONNECTION);
            }
        }
    }

    private void chooseUpdateOption(Employee employee) throws SQLException {
        System.out.println("Choose information to update:"
                + "\n[1] First Name"
                + "\n[2] Middle Name"
                + "\n[3] Last Name"
                + "\n[4] Birth Date"
                + "\n[5] Position"
                + "\n[6] Employment Status"
                + "\n[0] Cancel"
        );

        int choice = scanner.nextInt();

        switch (choice) {

            case 1:
                updateFirstName(employee.getEmployeeId());
                break;
            case 2:
                updateMidName(employee.getEmployeeId());
                break;
            case 3:
                updateLastName(employee.getEmployeeId());
                break;
            case 4:
                updateBirthDate(employee.getEmployeeId());
                break;
            case 5:
                updatePosition(employee.getEmployeeId());
                break;
            case 6:
                updateStatus(employee.getEmployeeId());
                break;
            case 0:
                MainView.mainInit(CONNECTION);
                break;
            default:
                System.out.println("Invalid choice");
                updateEmployee(employee);
                break;

        }
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
                    MainView.mainInit(CONNECTION);
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

            PositionView.positionInit(CONNECTION);

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

    private void updateFirstName(int employeeId) throws SQLException {
        scanner = new Scanner(System.in);
        
            System.out.println("Enter new first name: ");
            String firstName = scanner.nextLine();

            statement = CONNECTION.getSqlConnection().prepareStatement(EmployeeQuery.UPDATE_FIRST_NAME);
            statement.setString(1, firstName);
            statement.setInt(2, employeeId);
            statement.executeUpdate();

            System.out.println("Employee ID No." + employeeId + "'s first name has been updated.");

            MainView.mainInit(CONNECTION);
        
    }

    private void updateMidName(int employeeId) throws SQLException {
        scanner = new Scanner(System.in);
       
            System.out.println("Enter new middle name: ");
            String midName = scanner.nextLine();

            statement = CONNECTION.getSqlConnection().prepareStatement(EmployeeQuery.UPDATE_FIRST_NAME);
            statement.setString(1, midName);
            statement.setInt(2, employeeId);
            statement.executeUpdate();
            System.out.println("Employee ID No." + employeeId + "'s middle name has been updated.");

            MainView.mainInit(CONNECTION);
        
    }

    private void updateLastName(int employeeId) throws SQLException {
        scanner = new Scanner(System.in);
       
            System.out.println("Enter new last name: ");

            String lastName = scanner.nextLine();

            statement = CONNECTION.getSqlConnection().prepareStatement(EmployeeQuery.UPDATE_LAST_NAME);
            statement.setString(1, lastName);
            statement.setInt(2, employeeId);
            statement.executeUpdate();
            System.out.println("Employee ID No." + employeeId + "'s last name has been updated.");
            MainView.mainInit(CONNECTION);

        
    }

    private void updateBirthDate(int employeeId) throws SQLException {
       
            scanner = new Scanner(System.in);

            System.out.println("Enter new birth date(MM/dd/yyyy): ");
            LocalDate birthDate = BirthDateValidator.validate(scanner);

            statement = CONNECTION.getSqlConnection().prepareStatement(EmployeeQuery.UPDATE_BIRTH_DATE);
            statement.setDate(1, Date.valueOf(birthDate));
            statement.setInt(2, employeeId);
            statement.executeUpdate();
            System.out.println("Employee ID No." + employeeId + "'s birth date has been updated.");

            MainView.mainInit(CONNECTION);

        
    }

    private void updatePosition(int employeeId) throws SQLException {
       
            PositionView.positionInit(CONNECTION);
            scanner = new Scanner(System.in);
            int positionId = scanner.nextInt();

            statement = CONNECTION.getSqlConnection().prepareStatement(PositionQuery.SEARCH_POSITION);
            statement.setInt(1, positionId);
            result = statement.executeQuery();

            while (!result.next()) {
                System.out.println("Position Id entered does not exist. Try again: ");
                positionId = scanner.nextInt();
            }

            statement = CONNECTION.getSqlConnection().prepareStatement(EmployeeQuery.UPDATE_POSITION);
            statement.setInt(1, positionId);
            statement.setInt(2, employeeId);
            statement.executeUpdate();

            System.out.println("Employee ID No." + employeeId + "'s position has been updated.");

            MainView.mainInit(CONNECTION);

            System.out.println("");
        

    }

    private void updateStatus(int employeeId) throws SQLException {
        
            StatusView.statusInit(CONNECTION);
            scanner = new Scanner(System.in);
            int statusId = scanner.nextInt();

            statement = CONNECTION.getSqlConnection().prepareStatement(StatusQuery.SEARCH_STATUS);
            statement.setInt(1, statusId);
            result = statement.executeQuery();

            while (!result.next()) {
                System.out.println("Invalid choice. Try again: ");
                statusId = scanner.nextInt();
            }

            statement = CONNECTION.getSqlConnection().prepareStatement(EmployeeQuery.UPDATE_STATUS);
            statement.setInt(1, statusId);
            statement.setInt(2, employeeId);
            statement.executeUpdate();

            System.out.println("Employee ID No." + employeeId + "'s status has been updated.");

            MainView.mainInit(CONNECTION);

            System.out.println("");
        

    }

    private int verifyEmployeeId(Scanner scanner) throws SQLException {

        System.out.println("Enter employee id : ");
        int employeeId = scanner.nextInt();

        statement = CONNECTION.getSqlConnection().prepareStatement(EmployeeQuery.SEARCH_EMPLOYEE);
        statement.setInt(1, employeeId);
        result = statement.executeQuery();

        while (!result.next()) {
            System.out.println("Employee id does not exist. Please try again.: ");
            return verifyEmployeeId(scanner);
        }

        return employeeId;

    }
}
