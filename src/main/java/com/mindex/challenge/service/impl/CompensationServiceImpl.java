package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.data.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// class: CompensationServiceImple
// description: CompensationService implemenation that uses the 
//              CompensationRepository to read and persist data
//              about Compensations.
@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    // function: create
    // parameters: compenation - The Compensation object to insert
    // returns: The created Compensation object.
    // description: Inserts a new Compensation object into the CompensationRepository.
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating employee [{}]", compensation);

        // ensure they supplied an employee
        Employee e = compensation.getEmployee();
        if(e == null){
            throw new RuntimeException("Compensation must have an Employee");
        }

        // ensure compensation
        // String employeeId = e.getEmployeeId();
        // Compensation existing = compensationRepository.findByEmployee_EmployeeId(employeeId);
        // if(existing != null)
        // {
        //     throw new RuntimeException("Compensation already exists for employee "+employeeId);
        // }

        // attempt to insert the compensation
        Compensation created = compensationRepository.insert(compensation);

        // ensure we were able to create the compensation
        if(created == null)
        {
            throw new RuntimeException("Error creating Compensation.");
        }

        return created;
    }

    // function: read
    // parameters: id - the EmployeeId of the Compensation to read.
    // returns: The Compensation object for that employee.
    // description: Gets the Compensation object for that employee.
    @Override
    public Compensation read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Compensation compensation = compensationRepository.findByEmployee_EmployeeId(id);

        if (compensation == null) {
            throw new RuntimeException("Error getting compensation for employee id: " + id);
        }

        return compensation;
    }
}
