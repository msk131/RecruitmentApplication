package com.app.recruitment.controllers;

import java.io.BufferedReader;
 import java.io.FileNotFoundException;
 import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
 
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.app.recruitment.apiresponse.UploadResponse;
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
	
	
	@RequestMapping(value="/employee", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UploadResponse> save( @RequestParam("file") MultipartFile file) {
		
		 BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";
	        if (!file.isEmpty()) {
	            
	                
			try {
				
				 
			     InputStream is = file.getInputStream();
			     br = new BufferedReader(new InputStreamReader(is));
				int i=0;	 
				     
	            while ((line = br.readLine()) != null) {
	            	
	            	if(i !=0) {
	            		
	            		String[] employee = line.split(cvsSplitBy);
	            	 
	 	                
	            		
	            		 if(null != employee[0].trim() && null != employee[1].trim() && null != employee[2].trim() && null != employee[3].trim() && 
	            				 null != employee[4].trim() && "" != employee[0].trim() && "" != employee[1].trim()
	            						 && "" != employee[2].trim() && "" != employee[3].trim() && "" != employee[4].trim())   {
	            		
	            			 
	            			 Employee emp =new Employee();
	            			 emp.setName(employee[0]); 
	            			 emp.setDepartment(employee[1]); 
	            				
	            			if(employee[2].equals(Designation.DEVELOPER.getName())) {
	            				
	            				emp.setDesignation(Designation.DEVELOPER.getName()); 
	            			}
							if(employee[2].equals(Designation.SENIOR_DEVELOPER.getName())) {
								            				
	            				emp.setDesignation(Designation.SENIOR_DEVELOPER.getName()); 
	            			}
							if(employee[2].equals(Designation.MANAGER.getName())) {
								
								emp.setDesignation(Designation.MANAGER.getName()); 
							}
							if(employee[2].equals(Designation.TEAM_LEAD.getName())) {
								
								emp.setDesignation(Designation.TEAM_LEAD.getName()); 
							}
							if(employee[2].equals(Designation.VP.getName())) {
								
								emp.setDesignation(Designation.VP.getName()); 
							}
							if(employee[2].equals(Designation.CEO.getName())) {
								
								emp.setDesignation(Designation.CEO.getName()); 
							}
 
	 	                	emp.setSalary(Double.valueOf(employee[3])); 
	            	            	
	            			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	            			java.util.Date date = format.parse(employee[4]);
	            			
	            			emp.setJoiningDate(date); 
	            		 
	 	                
	 	                	employeeService.save(emp);
	            		 }else {
	 	            		
	            			 br.close();
	 	            	}
	            	}
	                i++;
	               
	            }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				 
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

  
	        }
			
	        UploadResponse response = UploadResponse.builder().message("Successfully uploaded").build();
		return new ResponseEntity<>(response, HttpStatus.OK);
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
