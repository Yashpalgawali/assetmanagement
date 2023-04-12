package com.example.demo.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Company;


@Repository("comprepo")
public class CompanyRepoImpl  implements CompanyRepo{

	@Autowired
	JdbcTemplate temp;
	
	@Override
	public int saveCompany(Company comp) {
		// TODO Auto-generated method stub
		return temp.update("insert into tbl_company values('0',?)", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				
				ps.setString(1, comp.getComp_name());
			}
		});
	}

	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		return temp.query("SELECT * FROM tbl_company", new RowMapper<Company>() {

			@Override
			public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				Company comp = new Company();
				
				comp.setComp_id(rs.getLong(1));
				comp.setComp_name(rs.getString(2));
				return comp;
			}
		});
	}

	@Override
	public Company getCompanyById(String id) {
		// TODO Auto-generated method stub
		
		Company comp = temp.queryForObject("SELECT * FROM tbl_company where comp_id='"+id+"'", new BeanPropertyRowMapper<Company>(Company.class)); 
		
		if(comp!=null)
		{
			System.err.println("\n Company found");
			return comp;
		}
		else {
			System.err.println("\n Company NOt found");
			return comp;
		}
		
	}

	@Override
	public int updateCompany(Company comp) {
		// TODO Auto-generated method stub
		return temp.update("UPDATE tbl_company set comp_name=? where comp_id=?", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				
				ps.setString(1, comp.getComp_name());
				ps.setLong(2, comp.getComp_id());
				
			}
		});
	}

}
