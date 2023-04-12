package com.example.demo.models;

public class Designation {
	
	private Long desig_id;
	
	private String desig_name;

	public Long getDesig_id() {
		return desig_id;
	}

	public void setDesig_id(Long desig_id) {
		this.desig_id = desig_id;
	}

	public String getDesig_name() {
		return desig_name;
	}

	public void setDesig_name(String desig_name) {
		this.desig_name = desig_name;
	}

	@Override
	public String toString() {
		return "Designation [desig_id=" + desig_id + ", desig_name=" + desig_name + "]";
	}
	
	
	

}
