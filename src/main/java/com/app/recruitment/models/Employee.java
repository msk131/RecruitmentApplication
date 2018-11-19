package com.app.recruitment.models;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;


@Component
@Document(collection = "employee")
public class Employee{
	
	@Id
	private String _id;
	

 
	@Pattern(regexp = "[a-zA-Z0-9]*")
	@Field("Name")
	private String name;
	
	@Pattern(regexp = "[a-zA-Z0-9 -_* ]*")
	@Field("Department")
	private String department;
	
	public enum Designation{
		
		DEVELOPER("Developer"),  
		SENIOR_DEVELOPER("Senior Developer"), 
		MANAGER("Manager"), 
		TEAM_LEAD("Team Lead"), 
		VP("VP"), 
		CEO("CEO");
		
		private String name;

		Designation(String name) {
	        this.name = name;
	    }

	    public String getName() {
	        return this.name;
	    }    
		
	}
	
	@Field("Designation")
	private String designation;
	
	@Field("Salary")
	private double salary;
	
	
	@Field("Joining Date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date joiningDate;
	
	
	
	public Employee() {
		 
	}
	
	public Employee( String name, String department, String designation, double salary, Date joiningDate) {
		super();
		this.name = name;
		this.department = department;
		this.designation = designation;
		this.salary = salary;
		this.joiningDate = joiningDate;
	}
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	
	
}