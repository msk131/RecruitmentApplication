package com.app.recruitment.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;


@Document(collection = "employee")
public class Employee{
	
	@Id
	String id;
	
	
	String name;
	String department;
	
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
	public Designation designation;
	double salary;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date joiningDate;
	
	
	
	public Employee() {
		 
	}
	
	public Employee(String id, String name, String department, Designation designation, double salary, Date joiningDate) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.designation = designation;
		this.salary = salary;
		this.joiningDate = joiningDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Designation getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation.name = designation;
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