package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.AssetAssignHistory;
import com.example.demo.repository.AssetAssignHistoryRepo;


@Service("assetassignserv")
public class AssetAssignHistoryServImpl implements AssetAssignHistoryService {

	@Autowired
	AssetAssignHistoryRepo assetassignrepo;
	
	@Override
	public int saveAssignAssetHistory(AssetAssignHistory hist) {
		// TODO Auto-generated method stub
		return assetassignrepo.saveAssignAssetHistory(hist);
	}

	@Override
	public List<AssetAssignHistory> getAllAssignedAssetsHistory() {
		// TODO Auto-generated method stub
		return assetassignrepo.getAllAssignedAssetsHistory();
	}

	@Override	
	public List<AssetAssignHistory> getAssetAssignHistoryEmpId(String empid) {
		// TODO Auto-generated method stub
		return assetassignrepo.getAssetAssignHistoryEmpId(empid);
	}

	@Override
	public int updateAssetAssignHistory(AssetAssignHistory hist) {
		// TODO Auto-generated method stub
		return assetassignrepo.updateAssetAssignHistory(hist);
	}

}
