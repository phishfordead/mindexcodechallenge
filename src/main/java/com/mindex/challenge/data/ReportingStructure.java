package com.mindex.challenge.data;

// class:       ReportingStructure
// description: ReportingStructure holds an Employee and its number of 
//              directReports for an employee and all of their distinct reports.
public class ReportingStructure {
    // The employee this ReportingStructure describes
    private Employee employee;

    // The Number of people reporting to *employee*
    private int numberOfReports;

    
    public ReportingStructure() { }

    // **** employee GET and SET *** //

    public Employee getEmployee(){
        return employee;
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    // **** numberOfReports GET and SET **** //
    public void setNumberOfReports(int val){
        numberOfReports = val;
    }

    public int getNumberOfReports(){
        return numberOfReports;
    }
}