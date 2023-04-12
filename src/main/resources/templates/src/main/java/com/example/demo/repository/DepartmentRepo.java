package com.example.demo.repository;

import java.util.List;

import com.example.demo.models.Department;

public interface DepartmentRepo {

public int saveDepartment(Department dept);
	
	public List<Department> getAllDepartments();
	
	public List<Department> getDepartmentById(String id);
	
	public List<Department> getDepartmentByCompId(String compid);
	
	public int updateDepartmentById(Department dept);
	
}
