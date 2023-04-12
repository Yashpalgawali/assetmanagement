package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Company;
import com.example.demo.service.CompanyService;

@Controller
public class CompanyController {

	@GetMapping("/")
	public String index()
	{
		return "Home";
	}
	
	@GetMapping("/addcompany")
	public String addCompany()
	{
		return "AddCompany";
	}
	
	@Autowired
	CompanyService compserv;
	
	@RequestMapping("/savecompany")
	public String saveCompany(@ModelAttribute("Company")Company comp,RedirectAttributes attr)
	{
		int res = compserv.saveCompany(comp);
		
		if(res > 0)
		{
			attr.addFlashAttribute("response", "Company Saved Successfully");
			return "redirect:/viewcompanies";
		}
		else {
			attr.addFlashAttribute("reserr", "Company is not Saved ");
			return "redirect:/viewcompanies";
		}
	}
	
	@GetMapping("/viewcompanies")
	public String viewCompanies(Model model) {
		
		List<Company> clist = compserv.getAllCompanies();
		
		model.addAttribute("clist", clist);
		return "ViewCompanies";
	}
	
	@GetMapping("/editcompany/{id}")
	public String editCompanyById(@PathVariable("id") String id,Model model,RedirectAttributes attr)
	{
		Company company = compserv.getCompanyById(id);
		if(company!=null)
		{
			model.addAttribute("comp", company);
			return "EditCompany";
		}
		else {
			attr.addFlashAttribute("reserr", "Company is not Found for given ID ");
			return "redirect:/viewcompanies";
		}
	}
	
	@RequestMapping("/updatecompany")
	public String updateCompany(@ModelAttribute("Company")Company comp,RedirectAttributes attr)
	{
		int res = compserv.updateCompany(comp);
		if(res > 0)
		{
			attr.addFlashAttribute("response", "Company updated successfully");
			return "redirect:/viewcompanies";
		}
		else {
			attr.addFlashAttribute("reserr", "Company is not updated ");
			return "redirect:/viewcompanies";
		}
	}
}
