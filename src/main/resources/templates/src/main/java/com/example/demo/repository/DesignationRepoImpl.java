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

import com.example.demo.models.Designation;

@Repository("desigrepo")
public class DesignationRepoImpl implements DesignationRepository {

	@Autowired
	JdbcTemplate temp;
	
	
	@Override
	public int saveDesignation(Designation desig) {
		// TODO Auto-generated method stub
		return temp.update("insert into tbl_designation values('0',?)", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				
				ps.setString(1, desig.getDesig_name());
			}
		});
	}

	@Override
	public List<Designation> getAllDesignations() {
		// TODO Auto-generated method stub
		return temp.query("SELECT * FROM tbl_designation", new RowMapper<Designation>() {

			@Override
			public Designation mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				Designation desig = new Designation();
				
				desig.setDesig_id(rs.getLong(1));
				desig.setDesig_name(rs.getString(2));
				
				return desig;
			}
		});
	}

	@Override
	public List<Designation> getDesigByid(String id) {
		// TODO Auto-generated method stub
		return temp.query("SELECT * FROM tbl_designation where desig_id='"+id+"'", new RowMapper<Designation>() {

			@Override
			public Designation mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				Designation desig = new Designation();
				
				desig.setDesig_id(rs.getLong(1));
				desig.setDesig_name(rs.getString(2));
				
				return desig;
			}
		});
	}

	@Override
	public int updateDesignation(Designation des) {
		// TODO Auto-generated method stub
		return temp.update("UPDATE tbl_designation SET desig_name=? where desig_id=?", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
			
				ps.setString(1, des.getDesig_name());
				ps.setLong(2, des.getDesig_id());
			}
		});
	}

}
