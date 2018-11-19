package com.app.recruitment.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.recruitment.models.Employee;
import com.app.recruitment.repositories.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	 public Page<Employee> getAllMetaData(Pageable pageable) {
		 
	        return employeeRepository.findAll(pageable);
	    }

	@Override
	public void save(Employee emp) {
		 
		
		employeeRepository.save(emp);
	}
}
