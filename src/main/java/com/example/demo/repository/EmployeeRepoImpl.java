package com.example.demo.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Asset;
import com.example.demo.models.AssetAssignHistory;
import com.example.demo.models.AssetType;
import com.example.demo.models.AssignedAssets;
import com.example.demo.models.Company;
import com.example.demo.models.Department;
import com.example.demo.models.Designation;
import com.example.demo.models.Employee;

@Repository("emprepo")
public class EmployeeRepoImpl implements EmployeeRepository {

	@Autowired
	JdbcTemplate temp;
	
	
	@Override
	public int saveEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return temp.update("INSERT INTO tbl_employee VALUES('0',?,?,?,?,?)", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
			
				ps.setString(1, emp.getEmp_name());
				ps.setString(2, emp.getEmp_email());
				ps.setString(3, emp.getEmp_contact());
				
				ps.setLong(4, emp.getDept_id());
				ps.setLong(5, emp.getDesig_id());
			}
		});
	}

	@Override
	public List<Employee> getAllEMployees() {
		// TODO Auto-generated method stub
		return temp.query("SELECT *,GROUP_CONCAT(tbl_asset.asset_name)asset_names,GROUP_CONCAT(tbl_assettype.type_name)asset_types FROM tbl_employee JOIN tbl_assigned_assets ON tbl_assigned_assets.emp_id=tbl_employee.emp_id JOIN tbl_asset ON tbl_asset.asset_id=tbl_assigned_assets.asset_id JOIN tbl_assettype ON tbl_assettype.type_id=tbl_asset.type_id JOIN tbl_designation ON tbl_designation.desig_id=tbl_employee.desig_id JOIn tbl_department ON tbl_department.dept_id=tbl_employee.dept_id JOIN tbl_company  ON tbl_company.comp_id=tbl_department.comp_id", new RowMapper<>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				Employee emp = new Employee();
				
				emp.setEmp_id(rs.getLong(1));
				emp.setEmp_name(rs.getString(2));
				emp.setEmp_email(rs.getString(3));
				emp.setEmp_contact(rs.getString(4));
				emp.setDept_id(rs.getLong(5));
				emp.setDesig_id(rs.getLong(6));
				
				
				AssignedAssets assigned = new AssignedAssets();
				
				assigned.setAssigned_asset_id(rs.getLong(7));
				assigned.setEmp_id(rs.getLong(8));
				assigned.setAssign_date(rs.getString(10));
				assigned.setAssign_time(rs.getString(11));
				
				
				Designation desig = new Designation();
				desig.setDesig_id(rs.getLong(19));
				desig.setDesig_name(rs.getString(20));
				
				emp.setDesignation(desig);
				
				Department dept = new Department();
				
				dept.setDept_id(rs.getLong(21));
				dept.setDept_name(rs.getString(22));
				
				emp.setDepartment(dept);
				
				Company comp = new Company();
				comp.setComp_id(rs.getLong(23));
				comp.setComp_name(rs.getString(25));
				
				emp.setCompany(comp);
				
				emp.setAsset_types(rs.getString(26));
				emp.setAsset_names(rs.getString(27));
				
				
				return emp;
			}
		});
	}

	@Override
	public List<Employee> getEmployeeById(String id) {
		// TODO Auto-generated method stub
		return temp.query(" SELECT * FROM tbl_employee JOIN tbl_asset_assign_history ON tbl_asset_assign_history.emp_id=tbl_employee.emp_id JOIN tbl_asset ON tbl_asset.asset_id=tbl_asset_assign_history.asset_id JOIN tbl_assettype ON tbl_assettype.type_id=tbl_asset.type_id JOIN tbl_designation ON tbl_designation.desig_id=tbl_employee.desig_id JOIN tbl_department ON tbl_department.dept_id=tbl_employee.dept_id JOIN tbl_company ON tbl_company.comp_id=tbl_department.comp_id WHERE tbl_employee.emp_id='"+id+"'", new RowMapper<>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				Employee emp = new Employee();
				
				emp.setEmp_id(rs.getLong(1));
				emp.setEmp_code(rs.getLong(2));
				emp.setEmp_name(rs.getString(3));
				emp.setEmp_email(rs.getString(4));
				emp.setEmp_contact(rs.getString(5));
				
				
				Asset asset = new Asset();
				
				asset.setAsset_id(rs.getLong(9));
				asset.setAsset_name(rs.getString(10));
				asset.setType_id(rs.getLong(11));
				asset.setAsset_number(rs.getString(12));
				asset.setModel_number(rs.getString(13));
				
				emp.setAsset(asset);
				
				Department dept = new Department();
				
				dept.setDept_id(rs.getLong(7));
				dept.setDept_name(rs.getString(15));
				
				emp.setDepartment(dept);
				
				Company comp = new Company();
				comp.setComp_id(rs.getLong(16));
				comp.setComp_name(rs.getString(18));
				
				emp.setCompany(comp);
				
				Designation desig = new Designation();
				desig.setDesig_id(rs.getLong(19));
				desig.setDesig_name(rs.getString(20));
				
				emp.setDesignation(desig);
				
				emp.setAsset_names(rs.getString(21));
				emp.setAsset_types(rs.getString(22));
				return emp;
			}
			
		});
	}

	@Override
	public int updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return temp.update("UPDATE tbl_employee SET emp_code=?,emp_name=?,emp_email=?,emp_contact=?,asset_id=?,dept_id=?,desig_id=? WHERE emp_id=?", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				
				ps.setLong(1, emp.getEmp_code());
				ps.setString(2, emp.getEmp_name());
				ps.setString(3, emp.getEmp_email());
				ps.setString(4, emp.getEmp_contact());
				ps.setString(5, emp.getMulti_assets());
				ps.setLong(6, emp.getDept_id());
				ps.setLong(7, emp.getDesig_id());
				ps.setLong(8, emp.getEmp_id());
			}
		});
	}

	@Override
	public Long getLastSavedEmployeeId() {
		// TODO Auto-generated method stub
		
		Long res = temp.queryForObject("SELECT MAX(emp_id) FROM tbl_employee", Long.class);
		
		if(res!=null)
		{
			return res;
		}
		else {
			return 0L;
		}
	}

	@Override
	public List<Employee> getEmployeeByEmpId(String empid) {
		// TODO Auto-generated method stub
		return temp.query("SELECT * FROM tbl_employee  JOIN tbl_designation ON tbl_designation.desig_id=tbl_employee.desig_id JOIN tbl_department ON tbl_department.dept_id=tbl_employee.dept_id JOIN tbl_company ON tbl_company.comp_id=tbl_department.comp_id WHERE tbl_employee.emp_id='"+empid+"'", new RowMapper<>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				Employee emp = new Employee();
				
				emp.setEmp_id(rs.getLong(1));
				emp.setEmp_name(rs.getString(2));
				emp.setEmp_email(rs.getString(3));
				emp.setEmp_contact(rs.getString(4));
				
				
				Designation desig = new Designation();
				desig.setDesig_id(rs.getLong(6));
				desig.setDesig_name(rs.getString(8));
				
				emp.setDesignation(desig);
				
				Department dept = new Department();
				
				dept.setDept_id(rs.getLong(9));
				dept.setDept_name(rs.getString(10));
				
				emp.setDepartment(dept);
				
				Company comp = new Company();
				comp.setComp_id(rs.getLong(11));
				comp.setComp_name(rs.getString(13));
				
				emp.setCompany(comp);
				
				return emp;
			}
			
		});
	}

	@Override
	public List<Employee> getEmployeeHistoryByEmpId(String empid) {
		// TODO Auto-generated method stub
		return temp.query("SELECT * FROM tbl_employee JOIN tbl_asset_assign_history ON tbl_asset_assign_history.emp_id=tbl_employee.emp_id JOIN tbl_asset ON tbl_asset.asset_id=tbl_asset_assign_history.asset_id JOIN tbl_assettype ON tbl_assettype.type_id=tbl_asset.type_id JOIN tbl_designation ON tbl_designation.desig_id=tbl_employee.desig_id JOIN tbl_department ON tbl_department.dept_id=tbl_employee.dept_id JOIN tbl_company ON tbl_company.comp_id=tbl_department.comp_id WHERE tbl_employee.emp_id='"+empid+"'", new RowMapper<>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				Employee emp = new Employee();
				
				emp.setEmp_id(rs.getLong(1));
				emp.setEmp_name(rs.getString(2));
				emp.setEmp_email(rs.getString(3));
				emp.setEmp_contact(rs.getString(4));
				emp.setDept_id(rs.getLong(5));
				emp.setDesig_id(rs.getLong(6));
				
				
				AssetAssignHistory ahist = new AssetAssignHistory();
				
				ahist.setHist_id(rs.getLong(7));
				ahist.setAsset_id(rs.getLong(8));
				ahist.setOperation_date(rs.getString(9));
				ahist.setOperation_time(rs.getString(10));
				ahist.setOperation(rs.getString(11));
				ahist.setEmp_id(rs.getLong(12));
				
				emp.setAhist(ahist);
				
				Asset asset = new Asset();
				
				asset.setAsset_id(rs.getLong(13));
				asset.setAsset_name(rs.getString(14));
				asset.setType_id(rs.getLong(15));
				asset.setAsset_number(rs.getString(16));
				asset.setModel_number(rs.getString(17));
				
				emp.setAsset(asset);
				
				AssetType atype = new AssetType();
				
				atype.setType_id(rs.getLong(18));
				atype.setType_name(rs.getString(19));
				
				emp.setAssettype(atype);
				
				Designation desig = new Designation();
				desig.setDesig_id(rs.getLong(20));
				desig.setDesig_name(rs.getString(21));
				
				emp.setDesignation(desig);
				
				Department dept = new Department();
				
				dept.setDept_id(rs.getLong(22));
				dept.setDept_name(rs.getString(23));
				
				emp.setDepartment(dept);
				
				Company comp = new Company();
				comp.setComp_id(rs.getLong(24));
				comp.setComp_name(rs.getString(26));
				
				emp.setCompany(comp);
				
				emp.setAsset_names(rs.getString(27));
				return emp;
			}
	 });
	}

	@Override
	public List<Employee> getEmployeeAssignAssetsByEmpId(String empid) {
		// TODO Auto-generated method stub
		return temp.query("select *,GROUP_CONCAT(tbl_assettype.type_name)asset_types,GROUP_CONCAT(tbl_asset.asset_name)asset_names from tbl_employee JOIN tbl_assigned_assets ON tbl_assigned_assets.emp_id=tbl_employee.emp_id JOIN tbl_asset ON tbl_asset.asset_id=tbl_assigned_assets.asset_id JOIN tbl_designation ON tbl_designation.desig_id=tbl_employee.desig_id JOIN tbl_department ON tbl_department.dept_id=tbl_employee.dept_id joiN tbl_company ON tbl_company.comp_id=tbl_department.comp_id JOIN tbl_assettype ON tbl_assettype.type_id=tbl_asset.type_id WHERE tbl_employee.emp_id='"+empid+"'", new RowMapper<>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				Employee emp = new Employee();
				
				emp.setEmp_id(rs.getLong(1));
				emp.setEmp_name(rs.getString(2));
				emp.setEmp_email(rs.getString(3));
				emp.setEmp_contact(rs.getString(4));
				emp.setDept_id(rs.getLong(5));
				emp.setDesig_id(rs.getLong(6));
				
				
				AssignedAssets assigned = new AssignedAssets();
				
				assigned.setAssigned_asset_id(rs.getLong(7));
				assigned.setEmp_id(rs.getLong(8));
				assigned.setAssign_date(rs.getString(10));
				assigned.setAssign_time(rs.getString(11));
				
				
				
				
				Designation desig = new Designation();
				desig.setDesig_id(rs.getLong(17));
				desig.setDesig_name(rs.getString(18));
				
				emp.setDesignation(desig);
				
				Department dept = new Department();
				
				dept.setDept_id(rs.getLong(19));
				dept.setDept_name(rs.getString(20));
				
				emp.setDepartment(dept);
				
				Company comp = new Company();
				comp.setComp_id(rs.getLong(21));
				comp.setComp_name(rs.getString(23));
				
				emp.setCompany(comp);
				
				emp.setAsset_types(rs.getString(26));
				emp.setAsset_names(rs.getString(27));
				
				
				return emp;
			}
	 });
	}

	@Override
	public List<Employee> getAllAssignedAssetsEmployees() {
		// TODO Auto-generated method stub
		return temp.query("SELECT *,GROUP_CONCAT(tbl_assettype.type_name),GROUP_CONCAT(tbl_asset.asset_name),GROUP_CONCAT(tbl_asset.model_number),GROUP_CONCAT(tbl_asset.asset_number) Assigned_Assets from tbl_employee JOIN tbl_assigned_assets ON tbl_employee.emp_id=tbl_assigned_assets.emp_id JOIN tbl_asset ON tbl_asset.asset_id=tbl_assigned_assets.asset_id JOIN tbl_assettype ON tbl_assettype.type_id=tbl_asset.type_id JOIN tbl_department ON tbl_department.dept_id=tbl_employee.dept_id JOIN tbl_company ON tbl_company.comp_id=tbl_department.comp_id", new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				Employee emp = new Employee();
				
				emp.setEmp_id(rs.getLong(1));
				emp.setEmp_name(rs.getString(2));
				emp.setEmp_email(rs.getString(3));
				emp.setEmp_contact(rs.getString(4));
				emp.setDept_id(rs.getLong(5));
				emp.setDesig_id(rs.getLong(6));
				
				
				AssignedAssets assigned = new AssignedAssets();
				
				assigned.setAssigned_asset_id(rs.getLong(7));
				assigned.setEmp_id(rs.getLong(8));
				assigned.setAssign_date(rs.getString(10));
				assigned.setAssign_time(rs.getString(11));
				
				emp.setAssignedassets(assigned);
				
				Department dept = new Department();
				dept.setDept_id(rs.getLong(19));
				dept.setDept_name(rs.getString(20));
				dept.setComp_id(rs.getLong(21));
				
				emp.setDepartment(dept);
				
				Company comp = new Company();
				comp.setComp_id(rs.getLong(22));
				comp.setComp_name(rs.getString(23));
				
				emp.setCompany(comp);
				
				emp.setAsset_types(rs.getString(24));
				emp.setAsset_names(rs.getString(25));
				
				emp.setModel_numbers(rs.getString(26));
				
				return emp;
			}
			
		});
	}

	@Override
	public boolean isEmployeeExists(String empid) {
		// TODO Auto-generated method stub
		
		int res = temp.queryForObject("SELECT COUNT(emp_id) FROM tbl_employee WHERE emp_id='"+empid+"'", Integer.class);
		
		if(res > 0)
		{
			return true;
		}
		else {
		
			return false;
		}
		
	}

	@Override
	public boolean isAssetAssigned(Long empid, Long assetid) {
		// TODO Auto-generated method stub
		
		int res = temp.queryForObject("select COUNT(assigned_asset_id) FROM tbl_assigned_assets WHERE emp_id='"+empid+"' AND asset_id='"+assetid+"'", Integer.class);
		if(res>0)
		{
			return true;
		}
		else {
			return false;
		}
		
	}
}
