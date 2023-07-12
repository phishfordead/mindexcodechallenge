package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.ArrayList;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String employeeUrl;
    private String employeeIdUrl;
    private String reportingStructureIdUrl;


    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        employeeIdUrl = "http://localhost:" + port + "/employee/{id}";
        reportingStructureIdUrl = "http://localhost:" + port + "/reportingstructure/{id}";

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

    private String createEmployeeStructure(){
        // Create employees
        Employee test1 = restTemplate.postForEntity(employeeUrl, createEmployee("John1", null), Employee.class).getBody();
        Employee test2 = restTemplate.postForEntity(employeeUrl, createEmployee("John2",null), Employee.class).getBody();
        List<Employee> list = new ArrayList<Employee>();
        list.add(test1);
        list.add(test2);
        Employee test3 = restTemplate.postForEntity(employeeUrl, createEmployee("John3", list), Employee.class).getBody();
        list = new ArrayList<Employee>();
        list.add(test3);
        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, createEmployee("John",list), Employee.class).getBody();
        return createdEmployee.getEmployeeId();
    }

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
