package com.mytectra.springboot.playground.orm.o2o;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {
	
	@Id
	@GeneratedValue
	private int deptId;
	
	private String name;
	
	@OneToOne
	private Employee hod;
	
	@OneToMany(mappedBy = "department")
	private List<Employee> employees;

	/**
	 * @return the deptId
	 */
	public int getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(int deptId) {
		this.deptId = deptId;
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
	 * @return the hod
	 */
	public Employee getHod() {
		return hod;
	}

	/**
	 * @param hod the hod to set
	 */
	public void setHod(Employee hod) {
		this.hod = hod;
	}

	/**
	 * @return the employees
	 */
	public List<Employee> getEmployees() {
		return employees;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	

}
