package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Company;

public interface CompanyService {

	public int saveCompany(Company comp);
	
	public List<Company> getAllCompanies();
	
	public Company getCompanyById(String id);
	
	public int updateCompany(Company comp);
}
