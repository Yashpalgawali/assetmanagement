package com.example.demo.repository;

import java.util.List;

import com.example.demo.models.Designation;

public interface DesignationRepository {

	public int saveDesignation(Designation desig);
	
	public List<Designation> getAllDesignations();
	
	public List<Designation> getDesigByid(String id);
	
	public int updateDesignation(Designation des);
}
