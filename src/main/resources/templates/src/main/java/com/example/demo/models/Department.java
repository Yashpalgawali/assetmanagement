package com.example.demo.models;



public class Department {

	
	private Long dept_id;
	
	private String dept_name;
	
	private Long comp_id;
	
	public Long getComp_id() {
		return comp_id;
	}

	public void setComp_id(Long comp_id) {
		this.comp_id = comp_id;
	}

	private Company company;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Long getDept_id() {
		return dept_id;
	}

	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	@Override
	public String toString() {
		return "Department [dept_id=" + dept_id + ", dept_name=" + dept_name + ", comp_id=" + comp_id + ", company="
				+ company + "]";
	}
	
	
}
