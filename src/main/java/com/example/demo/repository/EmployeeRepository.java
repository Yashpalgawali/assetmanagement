package com.example.demo.repository;

import java.util.List;

import com.example.demo.models.Employee;

public interface EmployeeRepository {

	
	public int saveEmployee(Employee emp);
	
	public List<Employee> getAllEMployees();
	
	public List<Employee> getEmployeeById(String id);
	
	public List<Employee> getEmployeeByEmpId(String empid);
	
	public List<Employee> getEmployeeHistoryByEmpId(String empid);
	
	public int updateEmployee(Employee emp);
	
	public Long getLastSavedEmployeeId();
	
}
