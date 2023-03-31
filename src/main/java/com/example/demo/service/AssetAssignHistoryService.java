package com.example.demo.service;

import java.util.List;

import com.example.demo.models.AssetAssignHistory;

public interface AssetAssignHistoryService {

	
	public int saveAssignAssetHistory(AssetAssignHistory hist);
	
	public List<AssetAssignHistory> getAllAssignedAssetsHistory();
	
	public List<AssetAssignHistory> getAssetAssignHistoryEmpCode(String emp_code);
	
	public int updateAssetAssignHistory(AssetAssignHistory hist);
}
