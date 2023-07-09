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
import demo.app.view.LoginForm;
import demo.app.view.MainForm;
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

        CONNECTION.connect();
    }

    @Override
    public void login(HRAdmin hrAdmin) {
        
        try {
            scanner = new Scanner(System.in);
            System.out.println("Enter user name: ");
            hrAdmin.setUserName(scanner.nextLine());

            System.out.println("Enter password");
            hrAdmin.setPassword(scanner.nextLine());

            statement = CONNECTION.
                    getSqlConnection().
                    prepareStatement(HRAdminQuery.FIND_HRADMIN);

            statement.setString(1, hrAdmin.getUserName());
            statement.setString(2, hrAdmin.getPassword());
            result = statement.executeQuery();

            if (result.next()) {
                MainForm.userName = hrAdmin.getUserName();
                MainForm.mainInit(CONNECTION);
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
       LoginForm.accountInit(CONNECTION);
    }

    @Override
    public void register(HRAdmin hrAdmin) {

        try {
            hrAdmin = getInput();

            statement = CONNECTION.getSqlConnection().prepareStatement(HRAdminQuery.SEARCH_USERNAME);
            statement.setString(1, hrAdmin.getUserName());
            result = statement.executeQuery();

            while (result.next()) {
                System.out.println("User Name already exists! Try again: ");
                getCredentialInput(hrAdmin, scanner);
            }
            
            statement = CONNECTION.getSqlConnection().prepareStatement(HRAdminQuery.ADD_HRADMIN);
            statement.setString(1, hrAdmin.getFirstName());
            statement.setString(2, hrAdmin.getMidName());
            statement.setString(3, hrAdmin.getLastName());
            statement.setDate(4, Date.valueOf(hrAdmin.getBirthDate()));
            statement.setString(5, hrAdmin.getUserName());
            statement.setString(6, hrAdmin.getPassword());
            statement.executeUpdate();

            System.out.printf("%s %s %s has been successfully registered as HR Admin%n", hrAdmin.getFirstName(), hrAdmin.getMidName(), hrAdmin.getLastName());
            LoginForm.accountInit(CONNECTION);

        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
            MainForm.mainInit(CONNECTION);
        } finally {
            scanner.close();
            try {
                CONNECTION.getSqlConnection().close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());

                MainForm.mainInit(CONNECTION);
            }
        }
    }

    private HRAdmin getInput() {
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

            getCredentialInput(hrAdmin, scanner);

        } catch (InputMismatchException ex) {
            System.err.println(ex.getMessage());
            getInput();
        }
        return hrAdmin;
    }

    private void getCredentialInput(HRAdmin hrAdmin, Scanner scanner) {

        System.out.println("Enter user name");
        hrAdmin.setUserName(scanner.nextLine());

        System.out.println("Enter password: ");
        hrAdmin.setPassword(scanner.nextLine());
    }

}
