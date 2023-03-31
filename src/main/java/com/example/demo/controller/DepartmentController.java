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
import com.example.demo.models.Department;
import com.example.demo.service.CompanyService;
import com.example.demo.service.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired
	DepartmentService deptserv;
	
	@Autowired
	CompanyService compserv;
	
	@GetMapping("/adddepartment")
	public String addDepartment(Model model)
	{
		List<Company> clist = compserv.getAllCompanies();
		
		model.addAttribute("clist", clist);
		return "AddDepartment";
	}
	
	@RequestMapping("/savedepartment")
	public String saveDepartment(@ModelAttribute("Department") Department depart,RedirectAttributes attr)
	{
		int dep =  deptserv.saveDepartment(depart);
		
		if(dep > 0)
		{
			attr.addFlashAttribute("response", depart.getDept_name()+"Department is saved succefully");
			return "redirect:/viewdepartments";
		}
		else
		{
			attr.addFlashAttribute("response", depart.getDept_name()+" Department is not  saved ");
			return "redirect:/viewdepartments";
		}
		
	}

	@RequestMapping("viewdepartments")
	public String viewDepartment(Model model)
	{
		List<Department> dep = deptserv.getAllDepartments();
		model.addAttribute("deplist", dep);
		return "ViewDepartments";
	}
	
	@RequestMapping("/editdeptbyid/{id}")
	public String editDepartmentById(@PathVariable("id") String id,Model model,RedirectAttributes attr)
	{
		
		List<Department> dlist =deptserv.getDepartmentById(id);
		
		Department depart =null;
		
		for(int i=0;i<dlist.size();i++)
		{
			depart = dlist.get(i);
		}
		
		if(depart!=null)
		{
			List<Company> clist = compserv.getAllCompanies();
			
			model.addAttribute("clist", clist);
			model.addAttribute("depart", depart);
			return "EditDepartment";
		}
		else {
			attr.addFlashAttribute("reserr", "Department is not found for given ID");
			return "redirect:/viewdepartments";
		}
	}
	
	@RequestMapping("/updatedepartment")
	public String updateDepartment(@ModelAttribute("Department") Department dept ,RedirectAttributes attr)
	{
		int res = deptserv.updateDepartmentById(dept);
		if(res > 0)
		{
			attr.addFlashAttribute("response", "Department is updated successfully..");
			return "redirect:/viewdepartments";
		}
		else {
			attr.addFlashAttribute("reserr", "Department is not updated");
			return "redirect:/viewdepartments";
		}
	}
	
	@RequestMapping("/getdeptbycompid/{id}")
	@ResponseBody
	public List<Department> getdeptbycompid(@PathVariable("id")String id)
	{
		List<Department> dlist = deptserv.getDepartmentByCompId(id);

		
		return dlist;
	}
}
