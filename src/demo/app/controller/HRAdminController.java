/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.controller;

import demo.app.connection.DBConnection;
import demo.app.query.HRAdminQuery;
import demo.app.model.HRAdmin;
import demo.app.repository.HRAdminRepository;
import demo.app.utils.BirthDateValidator;
import demo.app.view.LoginView;
import demo.app.view.MainView;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Philip
 */
public class HRAdminController implements HRAdminRepository {

    private final DBConnection CONNECTION;
    private PreparedStatement statement;
    private ResultSet result;
    private Scanner scanner;

    public HRAdminController(DBConnection CONNECTION) {
        this.CONNECTION = CONNECTION;
    }

    @Override
    public void login(HRAdmin hrAdmin) {

        try {
            hrAdmin = getLoginInput();

            statement = CONNECTION.
                    getSqlConnection().
                    prepareStatement(HRAdminQuery.SEARCH_USERNAME_PASSWORD);

            statement.setString(1, hrAdmin.getUserName());
            statement.setString(2, hrAdmin.getPassword());
            result = statement.executeQuery();

            if (result.next()) {
                MainView.userName = hrAdmin.getUserName();
                MainView.mainInit(CONNECTION);
            } else {
                System.out.println("Incorrect username or password. Please try again.");
                login(hrAdmin);
            }

        } catch (SQLException | InputMismatchException ex) {
            System.err.println(ex.getMessage());
            login(hrAdmin);
        } finally {
            scanner.close();
            try {
                CONNECTION.getSqlConnection().close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                login(hrAdmin);
            }

        }
    }

    @Override
    public void logout(HRAdmin hrAdmin) {
        LoginView.accountInit(CONNECTION);
    }

    @Override
    public void register(HRAdmin hrAdmin) {

        try {
            hrAdmin = getRegisterInput();

           

            statement = CONNECTION.getSqlConnection().prepareStatement(HRAdminQuery.ADD_HRADMIN);
            statement.setString(1, hrAdmin.getFirstName());
            statement.setString(2, hrAdmin.getMidName());
            statement.setString(3, hrAdmin.getLastName());
            statement.setDate(4, Date.valueOf(hrAdmin.getBirthDate()));
            statement.setString(5, hrAdmin.getUserName());
            statement.setString(6, hrAdmin.getPassword());
            statement.executeUpdate();

            System.out.printf("%s %s %s has been successfully registered as HR Admin%n", hrAdmin.getFirstName(), hrAdmin.getMidName(), hrAdmin.getLastName());
            LoginView.accountInit(CONNECTION);

        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
            MainView.mainInit(CONNECTION);
        } finally {
            scanner.close();
            try {
                CONNECTION.getSqlConnection().close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());

                MainView.mainInit(CONNECTION);
            }
        }
    }

    private HRAdmin getRegisterInput() throws SQLException {
        HRAdmin hrAdmin = new HRAdmin();
        scanner = new Scanner(System.in);
        try {
            System.out.println("Enter first name: ");
            hrAdmin.setFirstName(scanner.nextLine());

            System.out.println("Enter middle name: ");
            hrAdmin.setMidName(scanner.nextLine());

            System.out.println("Enter last name: ");
            hrAdmin.setLastName(scanner.nextLine());

            System.out.println("Enter birth date(MM/dd/yyyy): ");
            hrAdmin.setBirthDate(BirthDateValidator.validate(scanner));

            validateUserName(hrAdmin);

        } catch (InputMismatchException ex) {
            System.err.println(ex.getMessage());
            getRegisterInput();
        }
        return hrAdmin;
    }

    private HRAdmin getLoginInput() {
        HRAdmin hrAdmin = new HRAdmin();

        try {
            scanner = new Scanner(System.in);
            System.out.println("Enter user name: ");
            hrAdmin.setUserName(scanner.nextLine());

            System.out.println("Enter password: ");
            hrAdmin.setPassword(scanner.nextLine());

        } catch (InputMismatchException ex) {
            System.err.println("Only numbers can be inputted. Please try again: ");
            getLoginInput();
        }
        return hrAdmin;
    }

    private void validateUserName(HRAdmin hrAdmin) throws SQLException {

        scanner = new Scanner(System.in);
        System.out.println("Enter user name: ");
        hrAdmin.setUserName(scanner.nextLine());

        System.out.println("Enter password: ");
        hrAdmin.setPassword(scanner.nextLine());

        statement = CONNECTION.getSqlConnection().prepareStatement(HRAdminQuery.SEARCH_USERNAME);
        statement.setString(1, hrAdmin.getUserName());
        result = statement.executeQuery();

        while (result.next()) {
            System.out.println("User name already exists. Please try again");
            validateUserName(hrAdmin);
        }

    }

}
