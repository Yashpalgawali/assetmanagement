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

@Repository("assetrepo")
public class AssetRepoImpl implements AssetRepo {

	@Autowired
	JdbcTemplate temp;
	
	@Override
	public int saveAsset(Asset asset) {
		// TODO Auto-generated method stub
		return temp.update("INSERT INTO tbl_asset values('0',?,?,?,?,?)", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				
				ps.setString(1, asset.getAsset_name());
				ps.setLong(2, asset.getType_id());
				ps.setString(3, asset.getAsset_number());
				ps.setString(4, asset.getModel_number());
				ps.setString(5, asset.getQuantity());
			}
		});
	}

	@Override
	public List<Asset> getAllAssets() {
		// TODO Auto-generated method stub
		return temp.query("SELECT * FROM tbl_asset JOIN tbl_assettype ON tbl_assettype.type_id=tbl_asset.type_id", new RowMapper<Asset>() {

			@Override
			public Asset mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Asset ast = new Asset();
				
				ast.setAsset_id(rs.getLong(1));
				ast.setAsset_name(rs.getString(2));
				ast.setAsset_number(rs.getString(4));
				ast.setModel_number(rs.getString(5));
				ast.setQuantity(rs.getString(6));
				
				AssetType atype = new AssetType();
				
				atype.setType_id(rs.getLong(3));
				atype.setType_name(rs.getString(8));
				
				ast.setAssettype(atype);
				
				return ast;
			}
		});
	}

	@Override
	public List<Asset> getAssetById(String asid) {
		// TODO Auto-generated method stub
		return temp.query("SELECT * FROM tbl_asset JOIN tbl_assettype ON tbl_assettype.type_id=tbl_asset.type_id WHERE asset_id='"+asid+"'", new RowMapper<Asset>() {

			@Override
			public Asset mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				Asset ast = new Asset();
				
				ast.setAsset_id(rs.getLong(1));
				ast.setAsset_name(rs.getString(2));
				ast.setAsset_number(rs.getString(4));
				ast.setModel_number(rs.getString(5));
				ast.setQuantity(rs.getString(6));
				
				AssetType atype =new AssetType();
				
				atype.setType_id(rs.getLong(6));
				atype.setType_name(rs.getString(8));
				
				ast.setAssettype(atype);
				
				return ast;
			}
			
		});
	}

	@Override
	public int updateAssetById(Asset ast) {
		// TODO Auto-generated method stub
		return temp.update("UPDATE tbl_asset set asset_name=?,asset_number=?,model_number=?,type_id=? where asset_id=?", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				
				ps.setString(1, ast.getAsset_name());
				ps.setString(2, ast.getAsset_number());
				ps.setString(3, ast.getModel_number());
				ps.setLong(4, ast.getType_id());
				ps.setLong(5, ast.getAsset_id());
			}
		});
	}

	@Override
	public int getAssetQuantity(Long assetid) {
		// TODO Auto-generated method stub
		return temp.queryForObject("SELECT quantity FROM tbl_asset WHERE asset_id='"+assetid+"'", Integer.class);
	}

	@Override
	public int updateAssetQuantityByAssetId(Long asid,String qty) {
		// TODO Auto-generated method stub
		
		return temp.update("UPDATE tbl_asset SET quantity=? WHERE asset_id=?", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
			
				ps.setString(1, qty);
				ps.setLong(2, asid);
			}
		});
	}

}
