package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Designation;
import com.example.demo.service.DesignationSevice;

@Controller
public class DesignationController {

	@Autowired
	DesignationSevice desigserv;
	 
	
	@GetMapping("/adddesignation")
	public String addDesignation()
	{
		return "AddDesignation";
	}
	
	@RequestMapping("/savedesignation")
	public String saveDesignation(@ModelAttribute("Designation")Designation desig,RedirectAttributes attr)
	{
		int des = desigserv.saveDesignation(desig);
		if(des > 0)
		{
			attr.addFlashAttribute("response", "Desigantion saved successfully");
			return "redirect:/viewdesignation";
		}
		else
		{
			attr.addFlashAttribute("reserr", "Desigantion is not saved ");
			return "redirect:/viewdesignation";
		}
	}

	@GetMapping("viewdesignation")
	public String viewDesignation(Model model)
	{
		List<Designation> dlist = desigserv.getAllDesignations();
		model.addAttribute("dlist", dlist);
		return "ViewDesignation";
	}
	
	@RequestMapping("/editdesignation/{id}")
	public String editDesignation(@PathVariable("id")String id ,Model model ,RedirectAttributes attr)
	{
		List<Designation> dlist = desigserv.getDesigByid(id);
		
		Designation desig = null;
		for(int i=0;i<dlist.size();i++)
		{
			desig = dlist.get(i);
		}
		
		if(desig!=null)
		{
			model.addAttribute("desig", desig);
			return "EditDesignation";
		}
		else {
			attr.addFlashAttribute("reserr", "No designation found for given ID");
			return "redirect:/viewdesignation";
		}
	}
	
	@RequestMapping("/updatedesignation")
	public String updateDesignation(@ModelAttribute("Designation")Designation desig,RedirectAttributes attr)
	{
		int res = desigserv.updateDesignation(desig);
		if(res > 0)
		{
			attr.addFlashAttribute("response", "Designation is updated Successfully");
			return "redirect:/viewdesignation";
		}
		else {
			attr.addFlashAttribute("reserr", "No designation found for given ID");
			return "redirect:/viewdesignation";
		}
	}
}
