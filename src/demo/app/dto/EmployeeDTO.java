/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Philip
 */
public class EmployeeDTO {

    private int employeeId;
    private String firstName;
    private String midName;
    private String lastName;
    private LocalDate birthDate;
    private int age;
    private String position;
    private BigDecimal salary;

    public EmployeeDTO() {
    }

    public EmployeeDTO(int employeeId, String firstName, String midName, String lastName, LocalDate birthDate, int age, String position, BigDecimal salary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.age = age;
        this.position = position;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

}
