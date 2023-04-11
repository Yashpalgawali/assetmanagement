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

import com.example.demo.models.AssetType;

@Repository("assettyperepo")
public class AssetTypeRepoImpl implements AssetTypeRepository {

	@Autowired
	JdbcTemplate temp;
	
	@Override
	public int saveAssetType(AssetType astype) {
		// TODO Auto-generated method stub
		return temp.update("INSERT INTO tbl_assettype values('0',?)", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
			
				ps.setString(1, astype.getType_name());
			}
		});
	}

	@Override
	public List<AssetType> getAllAssetTypes() {
		// TODO Auto-generated method stub
		return temp.query("SELECT * FROM tbl_assettype", new RowMapper<AssetType>() {

			@Override
			public AssetType mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				AssetType atype = new AssetType();
				
				atype.setType_id(rs.getLong(1));
				atype.setType_name(rs.getString(2));
				return atype;
			}
		});
	}

	@Override
	public List<AssetType> getAssetById(String aid) {
		// TODO Auto-generated method stub
		return temp.query("SELECT * FROM tbl_assettype where type_id='"+aid+"'", new RowMapper<AssetType>() {

			@Override
			public AssetType mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				AssetType atype = new AssetType();
				
				atype.setType_id(rs.getLong(1));
				atype.setType_name(rs.getString(2));
				return atype;
			}
		});
	}

	@Override
	public int updateAssetType(AssetType ast) {
		// TODO Auto-generated method stub
		return temp.update("UPDATE tbl_assettype SET type_name=? where type_id=?", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
			
				ps.setString(1, ast.getType_name());
				ps.setLong(2, ast.getType_id());
			}
		});
	}

}
