package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Asset;
import com.example.demo.models.AssetAssignHistory;
import com.example.demo.models.AssetType;
import com.example.demo.models.AssignedAssets;
import com.example.demo.models.Company;
import com.example.demo.models.Department;
import com.example.demo.models.Designation;
import com.example.demo.models.Employee;
import com.example.demo.service.AssetAssignHistoryService;
import com.example.demo.service.AssetService;
import com.example.demo.service.AssetTypeService;
import com.example.demo.service.AssignedAssetsService;
import com.example.demo.service.CompanyService;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.DesignationServImpl;
import com.example.demo.service.DesignationSevice;
import com.example.demo.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	AssetService asservice;
	
	@Autowired
	DepartmentService deptserv;
	
	@Autowired
	DesignationSevice desigserv;
	
	@Autowired
	AssetTypeService atypeserv;
	
	@Autowired
	CompanyService compserv;
	
	@Autowired
	EmployeeService empserv;
	
	@Autowired
	AssetAssignHistoryService histserv;
	
	@Autowired
	AssetAssignHistoryService assetassignserv;
	
	@Autowired
	AssignedAssetsService assignedassetserv;
	
	private LocalDateTime today;
	
	
	//private DateTimeFormatter dformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	private DateTimeFormatter ddate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private DateTimeFormatter dtime = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	private String tday=ddate.format(today.now()),ttime=dtime.format(today.now());
	
	@GetMapping("/addemployee")
	public String addEmployee(Model model, HttpSession sess ,RedirectAttributes attr)
	{
		sess.removeAttribute("empcode");
		
//		System.out.println("Todays date time is -->> "+today.now() );
//		
//		System.out.println("Formatted Todays ONLY date  is -->> "+ ddate.format(today.now()) );
//		
//		System.out.println("Formatted Todays ONLY TIME  is -->> "+ dtime.format(today.now()) );
		
		List<Asset> aslist = asservice.getAllAssets();
		List<Designation> desiglist = desigserv.getAllDesignations();
		List<Department> dlist = deptserv.getAllDepartments();
		List<AssetType> atypelist = atypeserv.getAllAssetTypes();
		List<Company> clist = compserv.getAllCompanies();
		
		model.addAttribute("clist", clist);
		model.addAttribute("atlist", atypelist);
		model.addAttribute("aslist", aslist);
		model.addAttribute("dlist", dlist);
		model.addAttribute("desigserv", attr);
		model.addAttribute("desiglist", desiglist);
		
		return "AddEmployee";
	}
	
	
	@RequestMapping("/saveassignasset")
	public String saveEmployee(@ModelAttribute("Employee")Employee empl,HttpSession sess, RedirectAttributes attr)
	{
		String multi_asset_id = empl.getMulti_assets();
		
		int res =0,rhist=0,result=0 ;
		
		char[] chararr = multi_asset_id.toCharArray();
		
		res = empserv.saveEmployee(empl);
		
		if(res > 0)
		{
		 for(int i=0;i<chararr.length;i++)
		 {
			if(Character.isDigit(chararr[i]))
			{
				int asid = 0;
				
				asid = Character.getNumericValue(chararr[i]);
				
				empl.setAsset_id((long)asid);
				
				today = LocalDateTime.now();
				
				Long lastemp = empserv.getLastSavedEmployeeId();
				
				AssetAssignHistory ahist = new AssetAssignHistory();
				
					ahist.setAsset_id((long)asid);
					ahist.setOperation_date(ddate.format(today.now()));
					ahist.setOperation_time(dtime.format(today.now()));
					ahist.setOperation("Asset Assigned");
					ahist.setEmp_id(lastemp);
					
				AssignedAssets assts = new AssignedAssets();
				
					assts.setEmp_id(lastemp);
					assts.setAsset_id((long)asid);
					assts.setAssign_date(ddate.format(today.now()));
					assts.setAssign_time(dtime.format(today.now()));
					
					result = assignedassetserv.saveAssignedAssets(assts);
					
					if(result > 0)
					{
						int qty = asservice.getAssetQuantity((long) asid);
						
						qty-=1;
						
						String qtyop = String.valueOf((qty));
						
						asservice.updateAssetQuantityByAssetId((long)asid, qtyop);
					}
					rhist = assetassignserv.saveAssignAssetHistory(ahist);
			}
		  }
		}
		
		if(res>0 && rhist>0){
			attr.addFlashAttribute("response", "Asset(s) assigned successfully ");
			return "redirect:/viewassignedassets";
		}
		else {
			attr.addFlashAttribute("reserr", "Asset(s) not assigned ");
			return "redirect:/viewassignedassets";
		}
	}
	
	@GetMapping("/viewassignedassets")
	public String viewAssignedAssets(Model model)
	{
		List<Employee> empl = empserv.getAllAssignedAssetsEmployees();
		
		model.addAttribute("empl", empl);
		return "ViewAssignedAssets";
	}
	
	@GetMapping("/viewemployees")
	public String viewEmployees(Model model,RedirectAttributes attr)
	{
		List<Employee> elist = empserv.getAllEMployees();
		
		model.addAttribute("elist", elist);
		return "ViewEmployees";
	}
	
	@GetMapping("/editempassignassetbyempid/{id}")
	public String editEmployeeById(@PathVariable("id")String id,Model model,RedirectAttributes attr)
	{
		List<Employee> emp = empserv.getEmployeeAssignAssetsByEmpId(id);
		
		Employee empl = null;
		
		for(int i=0;i<emp.size();i++)
		{
			empl = emp.get(i);
		}
			if(empl!=null)
			{
				if(empl.getAsset_ids()!=null) 
				{
					List<Asset>  	 	aslist 		= asservice.getAllAssets();
					List<Designation> 	desiglist 	= desigserv.getAllDesignations();
					List<Department> 	dlist 		= deptserv.getAllDepartments();
					List<AssetType>  	atypelist 	= atypeserv.getAllAssetTypes();
					List<Company>    	clist 		= compserv.getAllCompanies();
					
					model.addAttribute("clist", 	clist);
					model.addAttribute("atlist", 	atypelist);
					model.addAttribute("aslist", 	aslist);
					model.addAttribute("dlist", 	dlist);
					model.addAttribute("desigserv", attr);
					model.addAttribute("desiglist", desiglist);
					model.addAttribute("emp", 		empl);
					
					// This will Convert the String into array of string 
					String[] string = empl.getAsset_ids().replaceAll("\\[","").replaceAll("]","").split(",");
					
					Long[] strArray = new Long[string.length];
					
					for(int i=0;i<string.length;i++)
					{
						strArray[i] = Long.valueOf(string[i]);
					}
					
					model.addAttribute("assignedlist", strArray);
					
					return "EditEmployee";
				}
				else
				{
					emp = empserv.getEmployeeByEmpId(id);
					
					for(int i=0;i<emp.size();i++)
					{
						empl = emp.get(i);
					}
					
					List<Asset>  	 	aslist 		= asservice.getAllAssets();
					List<Designation> 	desiglist 	= desigserv.getAllDesignations();
					List<Department> 	dlist 		= deptserv.getAllDepartments();
					List<AssetType>  	atypelist 	= atypeserv.getAllAssetTypes();
					List<Company>    	clist 		= compserv.getAllCompanies();
					
					model.addAttribute("clist", 	clist);
					model.addAttribute("atlist", 	atypelist);
					model.addAttribute("aslist", 	aslist);
					model.addAttribute("dlist", 	dlist);
					model.addAttribute("desigserv", attr);
					model.addAttribute("desiglist", desiglist);
					model.addAttribute("emp", 		empl);
					
				
					model.addAttribute("assignedlist", null);
					return "EditEmployee";
				}
			}
	
		else {
			attr.addFlashAttribute("reserr", "No Employee found for given Id");
			return "redirect:/viewemployees";
		}
	}
	
	
	@RequestMapping("/exampleupdateasset")
	@ResponseBody
	public String exampleupdateAssignedAssets(@ModelAttribute("Employee")Employee empl,HttpSession sess, RedirectAttributes attr)
	{
		String multi_asset_id = empl.getMulti_assets();
		
		int newassetassignlength = multi_asset_id.length();
		
		System.err.println("New assets Length is "+multi_asset_id.length());
		
		List<Employee> elist=  empserv.getEmployeeAssignAssetsByEmpId(""+empl.getEmp_id());
		int assignassetid = elist.get(0).getAsset_ids().length();
		
		
		return ""+multi_asset_id+"-----------------   From the list ->> "+elist.get(0).getAsset_ids()+"----------------Size is "+elist.get(0).getAsset_ids().length();
	}
		
	
	@RequestMapping("/updateassignasset")
	public String updateAssignedAssets(@ModelAttribute("Employee")Employee empl,HttpSession sess, RedirectAttributes attr)
	{
		String multi_asset_id = empl.getMulti_assets();
		
		List<Employee> emplist = empserv.getEmployeeAssignAssetsByEmpId(""+empl.getEmp_id());
		
		String assigned_assets = emplist.get(0).getAsset_ids();
		
		int assignedassetlen = emplist.get(0).getAsset_ids().length();
		int newassetassignlen = multi_asset_id.length();
		
		
		int res = 0,rhist=0;
		
		char[] chararr = multi_asset_id.toCharArray();
		
		boolean result ;
		
		if(newassetassignlen>=assignedassetlen)
		{
		 for(int i=0;i<chararr.length;i++)
		 {
			if(Character.isDigit(chararr[i]))
			{
				int asid = 0;
				
				asid = Character.getNumericValue(chararr[i]);
				
				result  = empserv.isAssetAssigned(empl.getEmp_id(),(long)asid);
				if(result!=true)
				{
					empl.setAsset_id((long)asid);
				
					AssetAssignHistory ahist = new AssetAssignHistory();
					
						ahist.setAsset_id((long)asid);
						ahist.setOperation_date(tday);
						ahist.setOperation_time(ttime);
						ahist.setOperation("Asset Updated");
						ahist.setEmp_id(empl.getEmp_id());
						
					AssignedAssets assts = new AssignedAssets();
						
						assts.setEmp_id(empl.getEmp_id());
						assts.setAsset_id((long)asid);
						assts.setAssign_date(tday);
						assts.setAssign_time(ttime);
						
					res  =	assignedassetserv.saveAssignedAssets(assts);
					
					if(res>0)
					{
						int qty = asservice.getAssetQuantity((long)asid);
						qty-=1;
						asservice.updateAssetQuantityByAssetId((long)asid, ""+qty);
					}
					rhist = assetassignserv.saveAssignAssetHistory(ahist);
				}
			 }
		   }
		 	if(res > 0)
			{
				attr.addFlashAttribute("response", "Assigned assets updated successfully");
				return "redirect:/viewassignedassets";
			}
			else {
				attr.addFlashAttribute("reserr", "Assigned assets are not updated ");
				return "redirect:/viewassignedassets";
			}
		}
		
		if(newassetassignlen<assignedassetlen)
		{	
		 
		 String assigned_asset_ids = emplist.get(0).getAsset_ids();// Already Assigned assets
		 
		 char nchararr[] = assigned_asset_ids.toCharArray();
		 
		 List<String> slit = Arrays.asList(multi_asset_id);// New Assets to be assigned
		 
		 int asid ;
		 
		 for(int i=0;i<nchararr.length;i++)
		 {
			 if(Character.isDigit(nchararr[i]))
			 {
				asid = Character.getNumericValue(nchararr[i]);
				
				Boolean isContainstheval = multi_asset_id.contains(""+asid);
				
				if(isContainstheval)
				{
					continue;
				}
				else {
					
					result  = empserv.isAssetAssigned(empl.getEmp_id(),(long)asid);
					
					
					if(result)
					{
						//empl.setAsset_id((long)asid);
						
						int retrieveasset = assignedassetserv.deleteAssignedAssetByEmpAndAssetId(asid, empl.getEmp_id());
						
						if(retrieveasset>0)
						{
							
							int qtyy = asservice.getAssetQuantity((long)asid);
							qtyy+=1;
							asservice.updateAssetQuantityByAssetId((long)asid, ""+qtyy);
						}
						
						AssetAssignHistory ahist = new AssetAssignHistory();
						
							ahist.setAsset_id((long)asid);
							ahist.setOperation_date(tday);
							ahist.setOperation_time(ttime);
							ahist.setOperation("Asset Retrieved");
							ahist.setEmp_id(empl.getEmp_id());
							
						rhist = assetassignserv.saveAssignAssetHistory(ahist);
						
					}
				 }
			}
		  }
		 	if(rhist > 0)
			{
				attr.addFlashAttribute("response", "Assigned assets updated successfully");
				return "redirect:/viewassignedassets";
			}
			else {
				attr.addFlashAttribute("reserr", "Assigned assets are not updated ");
				return "redirect:/viewassignedassets";
			}
		 }
		
		attr.addFlashAttribute("reserr", "To retrieve Assets goto view employeed");
		return "redirect:/viewemployees";
	}
	
	@GetMapping("/viewallemployees")
	public String viewAllEmployees(Model model)
	{
		List<Employee> empl = empserv.getAllEMployees();
		model.addAttribute("empl", empl);
		return "ViewAllEmployees";
	}
	
	@GetMapping("/viewemphistbyempid/{id}")
	public String viewEmpHistoryByEmpId(@PathVariable("id") String id,Model model,RedirectAttributes attr)
	{
		List<Employee> emp = empserv.getEmployeeByEmpId(id);
		
		Employee empl = null;
		if(emp!=null)
		{
			for(int i=0;i<emp.size();i++)
			{
				empl = emp.get(i);
			}
	
			List<AssetAssignHistory> ahist = histserv.getAssetAssignHistoryEmpId(id);
			
			model.addAttribute("ahist", ahist);
			model.addAttribute("emp", empl);
			return "ViewEmployeeHistory";
		}
		else
		{
			attr.addFlashAttribute("reserr", "Employee not found for given Id");
			return "redirect:/viewemployees";
		}
	}

	
	@GetMapping("/retrieveassetsbyempid/{id}")
	public String retrieveAssetsByEmpId(@PathVariable String id,Model model,RedirectAttributes attr)
	{
		List<Employee> emp = empserv.getEmployeeAssignAssetsByEmpId(id);
		
		Employee empl = null;
		for(int i=0;i<emp.size();i++)
		{
			empl = emp.get(i);
		}
		
		
		if(empl!=null)
		{
			if(empl.getAsset_ids()!=null)
			{
				List<Asset>  aslist 	= asservice.getAllAssets();
				
				// This will Convert the String into Array of String 
				String[] string = empl.getAsset_ids().replaceAll("\\[","").replaceAll("]","").split(",");
				
				Long[] strArray = new Long[string.length];
				
				for(int i=0;i<string.length;i++)
				{
					strArray[i] = Long.valueOf(string[i]);
				}
		
				model.addAttribute("aslist", 	aslist);
				model.addAttribute("emp", 		empl);
				model.addAttribute("assignedlist", strArray);
				
				return "RetrieveAssets";
			}
			else {
					attr.addFlashAttribute("reserr", "No Asset(s) are assigned to the employee");
					return "redirect:/viewemployees";
			}
		}
		else {
			attr.addFlashAttribute("reserr", "No Employee found for given Id");
			return "redirect:/viewemployees";
		}
	}
	
	@PostMapping("/updateretrieveassets")
	public String updateRetrieveAssets(@ModelAttribute("Employee")Employee empl,RedirectAttributes attr)
	{
		int res = empserv.updateRetrieveAssets(empl);
		if(res>0)
		{
			attr.addFlashAttribute("response", "Asset(s) Retrieved Successfully...");
			return "redirect:/viewemployees";
		}
		else {
			attr.addFlashAttribute("reserr", "Asset(s) are not Retrieved ");
			return "redirect:/viewemployees";
		}
	}
	
	
}

