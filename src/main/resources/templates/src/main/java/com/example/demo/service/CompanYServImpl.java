package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Company;
import com.example.demo.repository.CompanyRepo;

@Service("compserv")
public class CompanYServImpl implements CompanyService {

	@Autowired
	CompanyRepo comprepo;
	
	@Override
	public int saveCompany(Company comp) {
		// TODO Auto-generated method stub
		return comprepo.saveCompany(comp);
	}

	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		return comprepo.getAllCompanies();
	}

	@Override
	public Company getCompanyById(String id) {
		// TODO Auto-generated method stub
		return comprepo.getCompanyById(id);
	}

	@Override
	public int updateCompany(Company comp) {
		// TODO Auto-generated method stub
		return comprepo.updateCompany(comp);
	}

}
