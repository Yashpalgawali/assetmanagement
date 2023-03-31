package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service("empserv")
public class EmployeeServImpl implements EmployeeService {

	@Autowired
	EmployeeRepository emprepo;
	
	@Override
	public int saveEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return emprepo.saveEmployee(emp);
	}

	@Override
	public List<Employee> getAllEMployees() {
		// TODO Auto-generated method stub
		return emprepo.getAllEMployees();
	}

	@Override
	public List<Employee> getEmployeeById(String id) {
		// TODO Auto-generated method stub
		return emprepo.getEmployeeById(id);
	}

	@Override
	public int updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return emprepo.updateEmployee(emp);
	}

	@Override
	public Object getLastSavedEmployeeId() {
		// TODO Auto-generated method stub
		return emprepo.getLastSavedEmployeeId();
	}

	@Override
	public List<Employee> getEmployeeByEmpCode(String empcode) {
		// TODO Auto-generated method stub
		return emprepo.getEmployeeByEmpCode(empcode);
	}

}
