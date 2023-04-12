package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Department;



public interface DepartmentService {

	public int saveDepartment(Department dept);
	
	public List<Department> getAllDepartments();
	
	public List<Department> getDepartmentById(String id);
	
	public List<Department> getDepartmentByCompId(String compid);
	
	public int updateDepartmentById(Department dept);
	
}
