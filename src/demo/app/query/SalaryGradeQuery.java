/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.query;

/**
 *
 * @author Philip
 */
public final class SalaryGradeQuery {

    private SalaryGradeQuery() {
    }

    public static final String INCREASE_SALARY = "UPDATE salary_grade SET salary=?";

    public static final String VIEW_SALARY = "SELECT salary FROM salary_grade";
}
