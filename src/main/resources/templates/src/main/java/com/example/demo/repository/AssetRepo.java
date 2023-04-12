package com.example.demo.repository;

import java.util.List;

import com.example.demo.models.Asset;

public interface AssetRepo {

public int saveAsset(Asset asset);
	
	public List<Asset> getAllAssets();
	
	public List<Asset> getAssetById(String asid);
	
	public int updateAssetById(Asset ast); 
	
	public int getAssetQuantity(Long assetid);
	
	public int updateAssetQuantityByAssetId(Long asid,String qty);
	
}
