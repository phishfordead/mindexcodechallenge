package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

// interface: CompensationRepository
// description: Interface extending MongoRepository for persisting/retrieving
//              data about an Employee's compensation.            
@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    Compensation findByEmployee_EmployeeId(String employeeId);
}
