package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Employee;

public interface EmployeeService {

	public int saveEmployee(Employee emp);
	
	public List<Employee> getAllEMployees();
	
	public List<Employee> getEmployeeById(String id);
	
	public List<Employee> getEmployeeByEmpCode(String empcode);
	
	public int updateEmployee(Employee emp);
	
	public Object getLastSavedEmployeeId();
	
}
