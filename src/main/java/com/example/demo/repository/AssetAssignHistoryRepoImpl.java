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
import com.example.demo.models.Company;
import com.example.demo.models.Department;
import com.example.demo.models.Employee;

@Repository("assetassignrepo")
public class AssetAssignHistoryRepoImpl implements AssetAssignHistoryRepo {

	@Autowired
	JdbcTemplate temp;
	
	@Override
	public int saveAssignAssetHistory(AssetAssignHistory hist) {
		// TODO Auto-generated method stub
		
		System.err.println("Inside history repo \n Asset Id-->> "+hist.getAsset_id()+"\nCode->>> "+hist.getEmp_code());
		
		return temp.update("insert into tbl_asset_assign_history values('0',?,?,?,?,?)", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				
				ps.setLong(1, hist.getAsset_id());
				
				ps.setString(2, hist.getOperation_date());
				ps.setString(3, hist.getOperation_time());
				ps.setString(4, hist.getOperation());
				ps.setLong(5, hist.getEmp_id());
					
			}
		});
	}

	@Override
	public List<AssetAssignHistory> getAllAssignedAssetsHistory() {
		// TODO Auto-generated method stub
		return temp.query("SELECT hist.*,te.*,dep.*,tbl_company.*,tas.*,tbl_assettype.*,GROUP_CONCAT(DISTINCT tas.asset_name),GROUP_CONCAT(DISTINCT tbl_assettype.type_name)  FROM tbl_asset_assign_history hist JOIN tbl_employee te ON te.emp_code=hist.emp_code JOIN tbl_asset tas ON tas.asset_id=te.asset_id JOIN tbl_department dep ON dep.dept_id=te.dept_id JOIN tbl_company ON tbl_company.comp_id=dep.comp_id JOIN tbl_assettype ON tbl_assettype.type_id=tas.type_id GROUP BY hist.emp_code", new RowMapper<>() {

			@Override
			public AssetAssignHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				AssetAssignHistory hist = new AssetAssignHistory();
				
				hist.setHist_id(rs.getLong(1));
				hist.setAsset_id(rs.getLong(2));
				hist.setOperation_date(rs.getString(3));
				hist.setOperation_time(rs.getString(4));
				hist.setOperation(rs.getString(5));
				hist.setEmp_id(rs.getLong(6));
				
				Employee emp = new Employee();
				
				emp.setEmp_id(rs.getLong(7));
				
				emp.setEmp_name(rs.getString(9));
				emp.setEmp_email(rs.getString(10));
				emp.setEmp_contact(rs.getString(11));
				
				
				Department dept =new Department();
				
				dept.setDept_id(rs.getLong(13));
				dept.setDept_name(rs.getString(16));
				
				Company comp = new Company();
				
				comp.setComp_id(rs.getLong(17));
				comp.setComp_name(rs.getString(19));
				
				Asset ast = new Asset();
				ast.setAsset_id(rs.getLong(20));
				ast.setAsset_name(rs.getString(21));
				ast.setType_id(rs.getLong(22));
				ast.setAsset_number(rs.getString(23));
				ast.setModel_number(rs.getString(24));
				
				AssetType atype = new AssetType();
				
				atype.setType_id(rs.getLong(25));
				atype.setType_name(rs.getString(26));
				
				
				
				hist.setAssigned_assets(rs.getString(27));
				hist.setAssigned_asset_type(rs.getString(28));
				
				hist.setAsset(ast);
				hist.setDept(dept);
				hist.setCompany(comp);
				hist.setEmployee(emp);
				
				return hist;
			}
			
		});
	}

	@Override
	public List<AssetAssignHistory> getAssetAssignHistoryEmpCode(String emp_code) {
		// TODO Auto-generated method stub
		
		System.err.println("Inside gethistory repo \nEMployee code is -->> "+emp_code);
		
		return temp.query("SELECT tbl_asset_assign_history.*,tbl_employee.emp_code,tbl_employee.emp_name,tbl_employee.emp_email,\r\n"
				+ "tbl_employee.emp_contact,tbl_asset.*,tbl_assettype.*,tbl_department.*,tbl_company.*\r\n"
				+ "FROM  tbl_asset_assign_history \r\n"
				+ "JOIN   tbl_employee   ON tbl_employee.emp_code=tbl_asset_assign_history.emp_code \r\n"
				+ "JOIN   tbl_asset 	  ON tbl_asset.asset_id=tbl_asset_assign_history.asset_id  \r\n"
				+ "JOIN   tbl_assettype  ON tbl_assettype.type_id=tbl_asset.type_id \r\n"
				+ "JOIN   tbl_department ON tbl_department.dept_id=tbl_employee.dept_id \r\n"
				+ "JOIN   tbl_company    ON tbl_company.comp_id=tbl_department.comp_id\r\n"
				+ "WHERE  tbl_asset_assign_history.emp_code='"+emp_code+"' GROUP BY tbl_asset_assign_history.hist_id;", new RowMapper<>() {

			@Override
			public AssetAssignHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				AssetAssignHistory hist = new AssetAssignHistory();
				
				hist.setHist_id(rs.getLong(1));
				hist.setAsset_id(rs.getLong(2));
				hist.setOperation_date(rs.getString(3));
				hist.setOperation_time(rs.getString(4));
				hist.setOperation(rs.getString(5));
				hist.setEmp_code(rs.getLong(6));
				
				Employee emp = new Employee();
				
				emp.setEmp_code(rs.getLong(7));
				
				emp.setEmp_name(rs.getString(8));
				emp.setEmp_email(rs.getString(9));
				emp.setEmp_contact(rs.getString(10));
				
				Asset ast = new Asset();
				ast.setAsset_id(rs.getLong(11));
				ast.setAsset_name(rs.getString(12));
				ast.setType_id(rs.getLong(13));
				ast.setAsset_number(rs.getString(14));
				ast.setModel_number(rs.getString(15));
				

				AssetType atype = new AssetType();
				
				atype.setType_id(rs.getLong(16));
				atype.setType_name(rs.getString(17));
				
				
				Department dept =new Department();
				
				dept.setDept_id(rs.getLong(18));
				dept.setDept_name(rs.getString(19));
				
				Company comp = new Company();
				
				comp.setComp_id(rs.getLong(20));
				comp.setComp_name(rs.getString(22));
				
				
				hist.setAsset(ast);
				hist.setDept(dept);
				hist.setCompany(comp);
				hist.setEmployee(emp);
				
				return hist;
			}
		});
	}

	@Override
	public int updateAssetAssignHistory(AssetAssignHistory hist) {
		// TODO Auto-generated method stub
		return 0;
	}

}
