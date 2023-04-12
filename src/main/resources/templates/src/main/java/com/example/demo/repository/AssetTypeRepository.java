package com.example.demo.repository;

import java.util.List;

import com.example.demo.models.AssetType;

public interface AssetTypeRepository {

	public int saveAssetType(AssetType astype);
	
	public List<AssetType> getAllAssetTypes();
	
	public List<AssetType> getAssetById(String aid);
	
	public int updateAssetType(AssetType ast);
	
}
