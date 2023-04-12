package com.example.demo.repository;

import java.util.List;

import com.example.demo.models.AssignedAssets;

public interface AssignedAssetsRepo {

	
	public int saveAssignedAssets(AssignedAssets assets);
	
	public List<AssignedAssets> getAllAssignedAssetsByEmpId(String empid);
	
	public int deleteAssignedAssetByEmpAndAssetId(int asset_id, Long emp_id);
	

}
