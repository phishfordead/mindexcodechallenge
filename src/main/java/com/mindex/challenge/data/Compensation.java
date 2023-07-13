package com.mindex.challenge.data;

import com.mindex.challenge.data.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDate;

// class: Compensation
// description: Compensation holds information about an employee's monetary
//              compensation.
public class Compensation {
    // The employee this compensation belongs to.
    private Employee employee;
    
    // The amount the person makes (per year).
    private BigDecimal salary;

    // The Date this Compensation took effect.
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate effectiveDate;

    public Compensation() {}

    // **** employee GET and SET **** //
    public Employee getEmployee(){
        return employee;
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    // **** salary GET and SET **** //
    public BigDecimal getSalary(){
        return salary;
    }

    public void setSalary(BigDecimal salary){
        this.salary = salary;
    }

    // **** effectiveDate GET and SET *** //
    public LocalDate getEffectiveDate(){
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate){
        this.effectiveDate = effectiveDate;
    }
}
