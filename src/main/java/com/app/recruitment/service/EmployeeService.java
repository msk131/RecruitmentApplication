package com.app.recruitment.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.recruitment.models.Employee;

public interface EmployeeService {
	
	 public Page<Employee> getAllMetaData(Pageable pageable);

}
