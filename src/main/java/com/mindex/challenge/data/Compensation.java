package com.mindex.challenge.data;

import com.mindex.challenge.data.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Compensation {
    private Employee employee;
    private BigDecimal salary;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate effectiveDate;

    public Compensation() {}

    public Employee getEmployee(){
        return employee;
    }

    public BigDecimal getSalary(){
        return salary;
    }

    public LocalDate getEffectiveDate(){
        return effectiveDate;
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    public void setSalary(BigDecimal salary){
        this.salary = salary;
    }

    public void setEffectiveDate(LocalDate effectiveDate){
        this.effectiveDate = effectiveDate;
    }
}
