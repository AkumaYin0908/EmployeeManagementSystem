/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.view;

import demo.app.connection.DBConnection;
import demo.app.controller.HRAdminController;
import demo.app.model.HRAdmin;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Philip
 */
public class LoginView {
    
    public static void accountInit(DBConnection connection) {
        HRAdminController hrAdminController = new HRAdminController(connection);
        System.out.println(" Welcome to EMPLOYEE MANAGEMENT SYSTEM V 1.0.0");
        System.out.println("[1] Login"
                + "\n[2] Register"
                + "\n[0] Cancel");
        Scanner scanner = new Scanner(System.in);
        try {

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hrAdminController.login(new HRAdmin());
                    break;
                case 2:
                    hrAdminController.register(new HRAdmin());
                    break;
                case 0:
                    System.out.println("Exiting....");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Try again");
                    LoginView.accountInit(connection);
            }
        } catch (InputMismatchException ex) {
            System.err.println("Only numbers can be inputted!");
            LoginView.accountInit(connection);
        } finally {
            scanner.close();
        }
    }
}
