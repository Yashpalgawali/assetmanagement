package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Department;
import com.example.demo.repository.DepartmentRepo;




@Service("deptserv")
public class DeprtmentServImpl implements DepartmentService {

	@Autowired
	DepartmentRepo deptrepo;
	
	@Override
	public int saveDepartment(Department dept) {
		// TODO Auto-generated method stub
		return deptrepo.saveDepartment(dept);
	}

	@Override
	public List<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		return deptrepo.getAllDepartments();
	}

	@Override
	public List<Department> getDepartmentById(String id) {
		// TODO Auto-generated method stub
		return deptrepo.getDepartmentById(id);
	}

	@Override
	public int updateDepartmentById(Department dept) {
		// TODO Auto-generated method stub
		return deptrepo.updateDepartmentById(dept);
	}

	@Override
	public List<Department> getDepartmentByCompId(String compid) {
		// TODO Auto-generated method stub
		return deptrepo.getDepartmentByCompId(compid);
	}

}
