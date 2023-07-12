package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// class: ReportingStructureServiceImple
// implements: ReportingStructureService
// description: Service class for building the ReportingStructure for a specific 
//              employee.
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeService employeeService;

    // function: read
    // parameters: id - the ID of the employee to build the reporting structure for.
    // description: Provides a ReportingStructure for 
    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Reading the reporting with id [{}]", id);

        // get the employee
        Employee employee = employeeService.read(id);

        // ensure the employee id was real, if not complain.
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        // create and populate the ReportingStructure object.
        ReportingStructure rs = new ReportingStructure();
        int numberReports = countDirectReports(employee);
        rs.setEmployee(employee);
        rs.setNumberOfReports(numberReports);

        return rs;
    }

    // function: countDirectReports
    // parameters: employee - The employee we are determining the number of
    //                        reports for.
    // description: Recursively counts the reports for a given employee.
    private int countDirectReports(Employee employee){
        // get the employee's direct reports
        List<Employee> list = employee.getDirectReports();
        // initialize listSize and numReports
        int listSize = 0;
        int numReports = 0;

        // if the list is null, it means we don't have any direct reports
        if(list != null){
            // set the numReports and listSize to the lists size
            // as this is the number of direct reports an employee has
            numReports = listSize = list.size();

            // loop through all of the employee's direct reports
            for(int i=0;i<listSize;i++){
                Employee emp = list.get(i);
                // read the information for this direct report as
                // it only comes through with the employeeId
                emp = employeeService.read(emp.getEmployeeId());

                // make sure we can return the information about this
                // employee.
                list.set(i, emp);

                // count the direct reports for this employee.
                numReports += countDirectReports(emp);
            }
        }
        
        return numReports;
    }
}
