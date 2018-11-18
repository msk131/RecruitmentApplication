package com.app.recruitment.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.recruitment.models.Employee;
import com.app.recruitment.models.Employee.Designation;
import com.app.recruitment.repositories.EmployeeRepository;
import com.app.recruitment.service.EmployeeService;
import com.app.recruitment.exception.GenericErrorResponse;

@RestController
public class RecruitmentController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	 
	public static final Logger logger = LoggerFactory.getLogger(RecruitmentController.class);
	
	@RequestMapping(method=RequestMethod.GET, value="/employee/{page}/{size}/{sort}")
	public ResponseEntity<Page<Employee>> getAllEmp(@Valid @PathVariable("page") String page, 
			@Valid @PathVariable("sort") String sort,  @Valid @PathVariable("size") String size, @PageableDefault Pageable pageable){
		
		
		ResponseEntity<Page<Employee>> response = new ResponseEntity<>(employeeService.getAllMetaData(pageable), HttpStatus.OK);
		 
		 return  response;
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/employee", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Iterable<Employee> save( @RequestParam("file") MultipartFile file) {
		
		 BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";
	        
	        try {

	        	File convFile = new File(file.getOriginalFilename());
	            convFile.createNewFile(); 
 	            br = new BufferedReader(new FileReader(convFile));
	            while ((line = br.readLine()) != null) {
	            	
	            	Employee emp = new Employee();
	                // use comma as separator
	                String[] country = line.split(cvsSplitBy);

	                emp.setName(country[0]); 
	                emp.setDepartment(country[1]); 
	                emp.setDesignation(Designation.valueOf(country[2]));  
	                emp.setSalary(Double.valueOf(country[3])); 
	                emp.setJoiningDate(Date.valueOf(country[4])); 
	                
	                employeeRepository.save(emp);
	            }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

  
		 
			

		return employeeRepository.findAll();
    }
	
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public GenericErrorResponse blueLightException(Exception ex) {

		logger.error("Handling error with message: {}", ex.getMessage());
		logger.error("Stack:{} ", Arrays.toString(ex.getStackTrace()));
		GenericErrorResponse genericResponse = new GenericErrorResponse();
		
		if(ex.getMessage() == null){
				
			genericResponse.setMessage("Failure.");	
			genericResponse.setError("Email Id not found.");
		}else {
			
			genericResponse.setMessage("Error occurred.");
			genericResponse.setError(ex.getMessage());
		}
		
		return genericResponse;
	}

}
