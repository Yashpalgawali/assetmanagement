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

import com.example.demo.models.Company;
import com.example.demo.models.Department;

@Repository("deptrepo")
public class DepartmentRepoImpl implements DepartmentRepo {

	@Autowired
	JdbcTemplate temp;
	
	@Override
	public int saveDepartment(Department dept) {
		// TODO Auto-generated method stub
		return temp.update("INSERT INTO tbl_department values('0',?,?)", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, dept.getDept_name());
				ps.setLong(2, dept.getComp_id());
				
			}
		});
	}

	@Override
	public List<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		return temp.query("SELECT * FROM tbl_department JOIN tbl_company ON tbl_company.comp_id=tbl_department.comp_id", new RowMapper<Department>() {

			@Override
			public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				Department dept = new Department();
				
				dept.setDept_id(rs.getLong(1));
				dept.setDept_name(rs.getString(2));
				
				Company comp = new Company();
				
				comp.setComp_id(rs.getLong(3));
				comp.setComp_name(rs.getString(5));
				
				dept.setCompany(comp);
				
				return dept;
			}
		});
	}

	@Override
	public List<Department> getDepartmentById(String id) {
		// TODO Auto-generated method stub
		return  temp.query("SELECT * FROM tbl_department JOIN tbl_company ON tbl_company.comp_id=tbl_department.comp_id where dept_id='"+id+"'", new RowMapper<Department>() {

			@Override
			public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				Department dept = new Department();
				
				dept.setDept_id(rs.getLong(1));
				dept.setDept_name(rs.getString(2));

				Company comp = new Company();
				
				comp.setComp_id(rs.getLong(3));
				comp.setComp_name(rs.getString(5));
				
				dept.setCompany(comp);
				
				return dept;
			}
			
		});
	}

	@Override
	public int updateDepartmentById(Department dept) {
		// TODO Auto-generated method stub
		return temp.update("UPDATE tbl_department SET dept_name=?,comp_id=? where dept_id=?", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				
				ps.setString(1, dept.getDept_name());
				ps.setLong(2, dept.getComp_id());
				ps.setLong(3, dept.getDept_id());
			}
		});
	}

	@Override
	public List<Department> getDepartmentByCompId(String compid) {
		// TODO Auto-generated method stub
		return  temp.query("SELECT * FROM tbl_department JOIN tbl_company ON tbl_company.comp_id=tbl_department.comp_id where tbl_department.comp_id='"+compid+"'", new RowMapper<Department>() {

			@Override
			public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				Department dept = new Department();
				
				dept.setDept_id(rs.getLong(1));
				dept.setDept_name(rs.getString(2));

				Company comp = new Company();
				
				comp.setComp_id(rs.getLong(3));
				comp.setComp_name(rs.getString(5));
				
				dept.setCompany(comp);
				
				return dept;
			}
			
		});
	}

}
