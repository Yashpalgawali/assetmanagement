package com.example.demo.service;

import java.util.List;

import com.example.demo.models.AssetAssignHistory;

public interface AssetAssignHistoryService {

	
	public int saveAssignAssetHistory(AssetAssignHistory hist);
	
	public List<AssetAssignHistory> getAllAssignedAssetsHistory();
	
	public List<AssetAssignHistory> getAssetAssignHistoryEmpId(String empid);
	
	public int updateAssetAssignHistory(AssetAssignHistory hist);
}
