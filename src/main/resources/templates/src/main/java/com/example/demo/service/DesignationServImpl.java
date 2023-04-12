package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Designation;
import com.example.demo.repository.DesignationRepository;


@Service("desigserv")
public class DesignationServImpl implements DesignationSevice {

	@Autowired
	DesignationRepository desigrepo;

	@Override
	public int saveDesignation(Designation desig) {
		// TODO Auto-generated method stub
		return desigrepo.saveDesignation(desig);
	}

	@Override
	public List<Designation> getAllDesignations() {
		// TODO Auto-generated method stub
		return desigrepo.getAllDesignations();
	}

	@Override
	public List<Designation> getDesigByid(String id) {
		// TODO Auto-generated method stub
		return desigrepo.getDesigByid(id);
	}

	@Override
	public int updateDesignation(Designation des) {
		// TODO Auto-generated method stub
		return desigrepo.updateDesignation(des);
	}
	

}
