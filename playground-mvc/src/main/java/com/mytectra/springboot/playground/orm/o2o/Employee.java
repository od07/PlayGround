package com.mytectra.springboot.playground.orm.o2o;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name ="employees")
public class Employee {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	private String emailId;
	
	private String qulification;
	
	@ManyToOne
	private Department department;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the qulification
	 */
	public String getQulification() {
		return qulification;
	}

	/**
	 * @param qulification the qulification to set
	 */
	public void setQulification(String qulification) {
		this.qulification = qulification;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	
	

}
