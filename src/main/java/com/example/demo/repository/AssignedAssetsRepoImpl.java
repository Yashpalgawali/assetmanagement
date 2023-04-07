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
import com.example.demo.models.AssignedAssets;
import com.example.demo.models.Department;
import com.example.demo.models.Designation;
import com.example.demo.models.Employee;


@Repository("assignedassetrepo")
public class AssignedAssetsRepoImpl implements AssignedAssetsRepo {

	@Autowired
	JdbcTemplate temp;
	
	@Override
	public int saveAssignedAssets(AssignedAssets assets) {
		// TODO Auto-generated method stub
		return temp.update("INSERT INTO tbl_assigned_assets VALUES('0',?,?,?,?)", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				
				ps.setLong(1, assets.getEmp_id());
				ps.setLong(2, assets.getAsset_id());
				ps.setString(3, assets.getAssign_date());
				ps.setString(4, assets.getAssign_time());
				
			}
		});
	}

	@Override
	public List<AssignedAssets> getAllAssignedAssetsByEmpId(String empid) {
		// TODO Auto-generated method stub
		return temp.query("SELECT * FROM tbl_assigned_assets JOIN tbl_employee ON tbl_employee.emp_id=tbl_assigned_assets.emp_id JOIN tbl_asset ON tbl_asset.asset_id=tbl_assigned_assets.asset_id JOIN tbl_designation ON tbl_designation.desig_id=tbl_employee.desig_id JOIN tbl_department ON tbl_department.dept_id=tbl_employee.dept_id JOIN tbl_company ON tbl_company.comp_id=tbl_department.comp_id JOIN tbl_assettype ON tbl_assettype.type_id=tbl_asset.type_id WHERE tbl_assigned_assets.emp_id='"+empid+"'", new RowMapper<AssignedAssets>() {

			@Override
			public AssignedAssets mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				AssignedAssets assets = new AssignedAssets();
				
				assets.setAsset_id(rs.getLong(1));
				assets.setEmp_id(rs.getLong(2));
				assets.setAsset_id(rs.getLong(3));
				assets.setAssign_date(rs.getString(4));
				assets.setAssign_time(rs.getString(5));
				
				Employee emp = new Employee();
				
				emp.setEmp_id(rs.getLong(6));
				emp.setEmp_name(rs.getString(7));
				emp.setEmp_email(rs.getString(8));
				emp.setEmp_contact(rs.getString(9));
				emp.setDept_id(rs.getLong(10));
				emp.setDesig_id(rs.getLong(11));
				
				assets.setEmployee(emp);
				
				
				Asset ast = new Asset();
				
				ast.setAsset_id(rs.getLong(12));
				ast.setAsset_name(rs.getString(13));
				ast.setType_id(rs.getLong(14));
				ast.setAsset_number(rs.getString(15));
				ast.setModel_number(rs.getString(16));
				
				assets.setAsset(ast);
				
				Designation desig = new Designation();
				
				desig.setDesig_id(rs.getLong(17));
				desig.setDesig_name(rs.getString(18));
				
				assets.setDesig(desig);
				
				Department dept = new Department();
				
				
				return assets;
			}
		});
	}

	@Override
	public int deleteAssignedAssetByEmpAndAssetId(int asset_id, Long emp_id) {
		// TODO Auto-generated method stub
		return temp.update("DELETE FROM tbl_assigned_assets WHERE emp_id=? AND asset_id=?", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				
				
				ps.setLong(1, emp_id);
				ps.setInt(2, asset_id);
			}
		});
	}

}
