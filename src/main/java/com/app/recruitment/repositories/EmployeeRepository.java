package com.app.recruitment.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.recruitment.models.Employee;


public interface EmployeeRepository extends MongoRepository<Employee, String> {
	

}
