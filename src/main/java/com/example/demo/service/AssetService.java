package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Asset;

public interface AssetService {

	public int saveAsset(Asset asset);
	
	public List<Asset> getAllAssets();
	
	public List<Asset> getAssetById(String asid);
	
	public int updateAssetById(Asset ast);
	
	public int getAssetQuantity(Long assetid);
	
	public int updateAssetQuantityByAssetId(Long asid,String qty);
	
	public int getAssetIdByName(String asname);

}
