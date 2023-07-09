/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Philip
 */
public class BirthDateValidator {

    private BirthDateValidator() {

    }

    public static LocalDate validate(Scanner scanner) {
        LocalDate parsedDate = LocalDate.now();
        try {
            String date=scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            parsedDate = LocalDate.parse(date, formatter);
        } catch (DateTimeException ex) {
            System.err.println(ex.getMessage() + " Try again: ");
            validate(scanner);
        }

        return parsedDate;
    }
}
