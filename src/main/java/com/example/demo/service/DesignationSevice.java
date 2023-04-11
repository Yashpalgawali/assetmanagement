package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Designation;


public interface DesignationSevice {

	public int saveDesignation(Designation desig);
	
	public List<Designation> getAllDesignations();
	
	public List<Designation> getDesigByid(String id);
	
	public int updateDesignation(Designation des);
	
}
