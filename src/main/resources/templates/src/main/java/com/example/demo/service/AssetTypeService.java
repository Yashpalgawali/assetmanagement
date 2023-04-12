package com.example.demo.service;

import java.util.List;

import com.example.demo.models.AssetType;


public interface AssetTypeService {

	public int saveAssetType(AssetType astype);
	
	public List<AssetType> getAllAssetTypes();
	
	public List<AssetType> getAssetById(String aid);
	
	public int updateAssetType(AssetType ast);
	
}
