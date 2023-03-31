package com.example.demo.repository;

import java.util.List;

import com.example.demo.models.Employee;

public interface EmployeeRepository {

	
	public int saveEmployee(Employee emp);
	
	public List<Employee> getAllEMployees();
	
	public List<Employee> getEmployeeById(String id);
	
	public List<Employee> getEmployeeByEmpCode(String empcode);
	
	public List<Employee> getEmployeeHistoryByEmpCode(String empcode);
	
	public int updateEmployee(Employee emp);
	
	public int getLastSavedEmployeeId();
	
}
