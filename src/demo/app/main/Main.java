/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.main;

import demo.app.connection.DBConnection;
import demo.app.view.LoginView;
import demo.app.view.MainView;

/**
 *
 * @author Philip
 */
public class Main {

    private static DBConnection connection = DBConnection.getInstance();

    public static void main(String[] args) {
        connection.connect();

        LoginView.accountInit(connection);
    }
}
