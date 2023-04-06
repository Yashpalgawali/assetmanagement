package com.example.demo.service;

import java.util.List;

import com.example.demo.models.AssignedAssets;

public interface AssignedAssetsService {

public int saveAssignedAssets(AssignedAssets assets);
	
	public List<AssignedAssets> getAllAssignedAssetsByEmpId(String empid);
	
}
