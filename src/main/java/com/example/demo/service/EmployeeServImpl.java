package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.AssetAssignHistory;
import com.example.demo.models.Employee;
import com.example.demo.repository.AssetAssignHistoryRepo;
import com.example.demo.repository.AssetRepo;
import com.example.demo.repository.AssignedAssetsRepo;
import com.example.demo.repository.EmployeeRepository;

@Service("empserv")
public class EmployeeServImpl implements EmployeeService {

	@Autowired
	EmployeeRepository emprepo;
	
	@Autowired
	AssignedAssetsRepo assignedassetrepo;
	
	@Autowired
	AssetAssignHistoryRepo histrepo;
	
	@Autowired
	AssetRepo assetrepo;
	
	private LocalDateTime today;
	
	private DateTimeFormatter ddate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private DateTimeFormatter dtime = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	private String tday=ddate.format(today.now()),ttime=dtime.format(today.now());
	
	
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
	public Long getLastSavedEmployeeId() {
		// TODO Auto-generated method stub
		return emprepo.getLastSavedEmployeeId();
	}

	@Override
	public List<Employee> getEmployeeByEmpId(String empid) {
		// TODO Auto-generated method stub
		
		boolean res = emprepo.isEmployeeExists(empid);
		if(res)
		{
			return emprepo.getEmployeeByEmpId(empid);
		}
		else {
			return null;
		}
	}

	@Override
	public List<Employee> getEmployeeAssignAssetsByEmpId(String empid) {
		// TODO Auto-generated method stub
		
		return emprepo.getEmployeeAssignAssetsByEmpId(empid);
	}

	@Override
	public List<Employee> getAllAssignedAssetsEmployees() {
		// TODO Auto-generated method stub
		return emprepo.getAllAssignedAssetsEmployees();
	}

	@Override
	public int updateRetrieveAssets(Employee emp) {
		// TODO Auto-generated method stub
		
		String masset = emp.getMulti_assets();
		char[] ch = masset.toCharArray();
		
		int asset_id=0,res=0;
		
		for(int i=0;i<masset.length();i++)
		{
			if(Character.isDigit(ch[i]))
			{
				asset_id = Character.getNumericValue(ch[i]);
				
				long qty = assetrepo.getAssetQuantity((long)asset_id);
				
				res = assignedassetrepo.deleteAssignedAssetByEmpAndAssetId(asset_id, emp.getEmp_id());
				if(res > 0)
				{
					AssetAssignHistory hist = new AssetAssignHistory();
					hist.setAsset_id((long)asset_id);
					hist.setOperation_date(tday);
					hist.setOperation_time(ttime);
					hist.setOperation("Asset Retrieved");
					hist.setEmp_id(emp.getEmp_id());
					hist.setComments(emp.getComments());
					
					assetrepo.updateAssetQuantityByAssetId((long)asset_id, ""+(qty+1));
					
					histrepo.saveAssignAssetHistory(hist);
				}
			}
		}
		
		return res;
	}

	@Override
	public boolean isAssetAssigned(Long empid,Long assetid) {
		// TODO Auto-generated method stub
		
		System.out.println("Inside the isAssetAssigned service empId->> "+empid+" and the asset ID is ->>"+assetid);
		
		return emprepo.isAssetAssigned(empid, assetid);
	}

}
