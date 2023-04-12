package com.example.demo.repository;

import java.util.List;

import com.example.demo.models.Company;

public interface CompanyRepo {

	public int saveCompany(Company comp);
	
	public List<Company> getAllCompanies();
	
	public Company getCompanyById(String id);
	
	public int updateCompany(Company comp);
}
