package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompensationService compensationService;
    

    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation) {
        LOG.debug("Received employee create request for [{}]", compensation);
        Compensation temp = compensationService.create(compensation);
        temp.setEmployee(employeeService.read(temp.getEmployee().getEmployeeId()));
        return temp;
    }

    @GetMapping("/compensation/{id}")
    public Compensation read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);
        Compensation temp = compensationService.read(id);
        temp.setEmployee(employeeService.read(temp.getEmployee().getEmployeeId()));
        return temp;
    }
}
