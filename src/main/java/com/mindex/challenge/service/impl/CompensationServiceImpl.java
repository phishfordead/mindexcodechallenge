package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.data.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating employee [{}]", compensation);

        Employee e = compensation.getEmployee();
        if(e == null){
            throw new RuntimeException("Compensation must have an Employee");
        }

        Compensation created = compensationRepository.insert(compensation);

        if(created == null)
        {
            throw new RuntimeException("Error creating Compensation.");
        }

        return compensation;
    }

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
