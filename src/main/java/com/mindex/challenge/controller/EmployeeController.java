package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ReportingStructureService reportingStructureService;

    @Autowired
    private CompensationService compensationService;

    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return employeeService.read(id);
    }

    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }

    // function: readReportingStructure
    // parameters: id - string containing the Id of a specific employee
    // returns: The ReportingStructure for a specific employee.
    // description: REST endpoint for getting the ReportingStructure for a
    //              specific employee based on it's *id*.
    @GetMapping("/employee/reportingstructure/{id}")
    public ReportingStructure readReportingStructure(@PathVariable String id) {
        LOG.debug("Received ReportingStructure read request for employee id [{}]", id);
        ReportingStructure rs = reportingStructureService.read(id);
        return rs;
    }

    // function: createCompensation
    // parameters: compensation - The Compensation object to be inserted
    //                            into the database
    // returns: The fully fleshed out Compensation object.
    // description:  REST endpoint for inserting a *compensation* into the
    //               database.
    @PostMapping("/employee/compensation")
    public Compensation createCompensation(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for [{}]", compensation);
        Compensation temp = compensationService.create(compensation);
        temp.setEmployee(employeeService.read(temp.getEmployee().getEmployeeId()));
        return temp;
    }

    // function: readCompensation
    // parameters: id - string containing the Id of a specific employee
    // returns: The Compensation object for the employee with the specific *id*.
    @GetMapping("/employee/compensation/{id}")
    public Compensation readCompensation(@PathVariable String id) {
        LOG.debug("Received employee compensation read request for id [{}]", id);
        Compensation temp = compensationService.read(id);
        temp.setEmployee(employeeService.read(temp.getEmployee().getEmployeeId()));
        return temp;
    }
    
}
