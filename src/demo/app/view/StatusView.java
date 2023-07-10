/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.view;

import demo.app.connection.DBConnection;
import demo.app.controller.StatusController;
import demo.app.model.Status;
import java.util.List;

/**
 *
 * @author User
 */
public class StatusView {

    public static void statusInit(DBConnection connection) {
        Status status = new Status();
        StatusController statusController = new StatusController(connection);

        List<Status> statusList = statusController.viewStatus(status);

        System.out.println("************************************** CHOOSE POSITION **************************************");
        System.out.printf("%s\t-5%s\n",
                "Status ID",
                "Status");

        for (Status stat: statusList) {
          System.out.printf("%s\t-5%s\n",
                stat.getStatusId(),
                stat.getStatus());
        }
    }

}
