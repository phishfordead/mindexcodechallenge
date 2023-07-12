package com.mindex.challenge.data;

import com.mindex.challenge.data.Employee;
import java.util.List;

// class:       ReportingStructure
// description: ReportingStructure holds an Employee and its number of 
//              directReports for an employee and all of their distinct reports.
public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    
    public ReportingStructure() { }

    // function: setEmployee
    // parameters: employee - The employee we are determining the number of
    //                        reports for.
    // description: Sets the employee and determines the number of 
    //              reports for that employee.
    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    // function: getEmployee
    // description: Gets the employee.
    public Employee getEmployee(){
        return employee;
    }

    // function: setNumberOfReports
    // parameters: val - the integer value we are setting numberOfReports to.
    public void setNumberOfReports(int val){
        numberOfReports = val;
    }

    // function: getNumberOfReports
    // description: Gets the number of reports the employee.
    public int getNumberOfReports(){
        return numberOfReports;
    }
}