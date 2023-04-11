package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Employee;

public interface EmployeeService {

	public int saveEmployee(Employee emp);
	
	public List<Employee> getAllEMployees();
	
	public List<Employee> getAllAssignedAssetsEmployees();
	
	public List<Employee> getEmployeeById(String id);
	
	public List<Employee> getEmployeeByEmpId(String empid);
	
	public List<Employee> getEmployeeAssignAssetsByEmpId(String empid);
	
	public int updateEmployee(Employee emp);
	
	public Long getLastSavedEmployeeId();
	
	public int updateRetrieveAssets(Employee emp);
	
	public boolean isAssetAssigned(Long empid,Long assetid);
}
