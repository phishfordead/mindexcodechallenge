package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

// class: ReportingStructureServiceImplTest
// description: test cases for testing the ReportingStructureServiceImpl class.
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String employeeUrl;
    private String reportingStructureIdUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        reportingStructureIdUrl = "http://localhost:" + port + "/employee/reportingstructure/{id}";

    }

    @Test
    public void testRead() {
        // create employee structure to test
        String employeeId = createEmployeeStructure();

        // get the reporting structure for the test employee
        ReportingStructure reportingStructure = 
            restTemplate.getForEntity(reportingStructureIdUrl, ReportingStructure.class, employeeId).getBody();

        // ensure we get the correct employee
        assertEquals(employeeId, reportingStructure.getEmployee().getEmployeeId());

        // ensure we have the correct number of reports
        assertEquals(3, reportingStructure.getNumberOfReports());
    }

    // function: createEmployeeStructure
    // returns: the employee id of the top level employee.
    // description: Creates an employee structure for testing out the 
    //              ReportingStructureServiceImple object.
    private String createEmployeeStructure(){
        // Create employees
        Employee test1 = restTemplate.postForEntity(employeeUrl, createEmployee("John1", null), Employee.class).getBody();
        Employee test2 = restTemplate.postForEntity(employeeUrl, createEmployee("John2",null), Employee.class).getBody();
        
        // employees test1 and test2 report to test3.
        List<Employee> list = new ArrayList<Employee>();
        list.add(test1);
        list.add(test2);
        
        // create test3 employee
        Employee test3 = restTemplate.postForEntity(employeeUrl, createEmployee("John3", list), Employee.class).getBody();
        
        // employee test3 reports to createdEmployee
        list = new ArrayList<Employee>();
        list.add(test3);

        // create createdEmployee employee
        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, createEmployee("John",list), Employee.class).getBody();
        
        return createdEmployee.getEmployeeId();
    }

    // function: createEmployee
    // parameters: name - the firstname of the employee
    //             directReports - a List of the direct reports of an Employee.
    // returns: the newly created Employee.
    private Employee createEmployee(String name, List<Employee> directReports){
        Employee testEmployee = new Employee();
        testEmployee.setFirstName(name);
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");
        testEmployee.setDirectReports(directReports);
        return testEmployee;
    }
}
