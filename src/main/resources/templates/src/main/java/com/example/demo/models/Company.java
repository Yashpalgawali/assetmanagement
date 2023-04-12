package com.example.demo.models;



public class Company {
	
	
	private Long comp_id;
	
	private String comp_name;

	
	public Long getComp_id() {
		return comp_id;
	}

	public void setComp_id(Long comp_id) {
		this.comp_id = comp_id;
	}

	public String getComp_name() {
		return comp_name;
	}

	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}

	public Company(Long comp_id, String comp_name) {
		super();
		this.comp_id = comp_id;
		this.comp_name = comp_name;
	}

	public Company() {}

	@Override
	public String toString() {
		return "Company [comp_id=" + comp_id + ", comp_name=" + comp_name + "]";
	}
	
	
	
}
