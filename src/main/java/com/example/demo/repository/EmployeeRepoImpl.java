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
import com.example.demo.models.AssetType;
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
		return temp.update("insert into tbl_employee values('0',?,?,?,?,?,?,?)", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
			
				ps.setLong(1, emp.getEmp_code());
				ps.setString(2, emp.getEmp_name());
				ps.setString(3, emp.getEmp_email());
				ps.setString(4, emp.getEmp_contact());
				ps.setLong(5, emp.getAsset_id());
				ps.setLong(6, emp.getDept_id());
				ps.setLong(7, emp.getDesig_id());
			}
		});
	}

	@Override
	public List<Employee> getAllEMployees() {
		// TODO Auto-generated method stub
		return temp.query("SELECT tbl_employee.*,tbl_asset.*,tbl_department.*,tbl_company.*,tbl_designation.*, GROUP_CONCAT(DISTINCT tbl_asset.asset_name) ASSET_NAMES FROM tbl_employee JOIN tbl_asset ON tbl_asset.asset_id=tbl_employee.asset_id JOIN tbl_department ON tbl_department.dept_id=tbl_employee.dept_id JOIN tbl_company ON tbl_company.comp_id=tbl_department.comp_id JOIN tbl_designation ON tbl_designation.desig_id=tbl_employee.desig_id GROUP BY tbl_employee.emp_code", new RowMapper<>() {

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
				return emp;
			}
		});
	}

	@Override
	public List<Employee> getEmployeeById(String id) {
		// TODO Auto-generated method stub
		return temp.query(" SELECT tbl_employee.*,tbl_asset.*,tbl_department.*,tbl_company.*,tbl_designation.*, GROUP_CONCAT(DISTINCT tbl_asset.asset_name), GROUP_CONCAT(DISTINCT tbl_assettype.type_name) "
				+ "			FROM tbl_employee "
				+ "         JOIN tbl_asset 		ON tbl_asset.asset_id=tbl_employee.asset_id "
				+ "         JOIN tbl_department ON tbl_department.dept_id=tbl_employee.dept_id "
				+ "         JOIN tbl_company 	ON tbl_company.comp_id=tbl_department.comp_id "
				+ "			JOIN tbl_designation ON tbl_designation.desig_id=tbl_employee.desig_id JOIN tbl_assettype ON tbl_assettype.type_id=tbl_asset.type_id GROUP BY tbl_employee.emp_code", new RowMapper<>() {

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
	public int getLastSavedEmployeeId() {
		// TODO Auto-generated method stub
		
		Object res = temp.queryForObject("select MAX(emp_code) FROM tbl_employee", Object.class);
		
		if(res!=null)
		{
			String output = String.valueOf(res);
			return Integer.parseInt(output);
		}
		else {
			return 0;
		}
		
	}

	@Override
	public List<Employee> getEmployeeByEmpCode(String empcode) {
		// TODO Auto-generated method stub
		return temp.query("SELECT tbl_employee.*,tbl_asset.*,tbl_department.*,tbl_company.*,tbl_designation.*, GROUP_CONCAT(DISTINCT tbl_asset.asset_name), GROUP_CONCAT(DISTINCT tbl_asset.asset_id),GROUP_CONCAT(DISTINCT tbl_asset.asset_id),GROUP_CONCAT(DISTINCT tbl_assettype.type_name)   "
							+ "FROM  tbl_employee "
							+ "JOIN  tbl_asset ON tbl_asset.asset_id=tbl_employee.asset_id "
							+ "JOIN  tbl_department ON tbl_department.dept_id=tbl_employee.dept_id "
							+ "JOIN  tbl_company ON tbl_company.comp_id=tbl_department.comp_id "
							+ "JOIN  tbl_designation ON tbl_designation.desig_id=tbl_employee.desig_id "
							+ "JOIN  tbl_assettype ON tbl_assettype.type_id=tbl_asset.type_id "
							+ "WHERE tbl_employee.emp_code='"+empcode+"'", new RowMapper<>() {

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
				
				emp.setMulti_assets(rs.getString(23));
				
				AssetType atype = new AssetType();
				atype.setType_name(rs.getString(24));
				
				emp.setAstype(atype);
				return emp;
			}
			
		});
	}

	@Override
	public List<Employee> getEmployeeHistoryByEmpCode(String empcode) {
		// TODO Auto-generated method stub
		return null;
	}

}
