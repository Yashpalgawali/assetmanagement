package com.example.demo.repository;

import java.util.List;

import com.example.demo.models.AssetAssignHistory;

public interface AssetAssignHistoryRepo {

	public int saveAssignAssetHistory(AssetAssignHistory hist);
	
	public List<AssetAssignHistory> getAllAssignedAssetsHistory();
	
	public List<AssetAssignHistory> getAssetAssignHistoryEmpId(String emp_id);
	
	public int updateAssetAssignHistory(AssetAssignHistory hist);
	
	
	
}
