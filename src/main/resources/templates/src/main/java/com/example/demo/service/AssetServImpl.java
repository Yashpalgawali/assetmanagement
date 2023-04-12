package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Asset;
import com.example.demo.repository.AssetRepo;

@Service("assetserv")
public class AssetServImpl implements AssetService {

	@Autowired
	AssetRepo assetrepo;

	@Override
	public int saveAsset(Asset asset) {
		// TODO Auto-generated method stub
		return assetrepo.saveAsset(asset);
	}

	@Override
	public List<Asset> getAllAssets() {
		// TODO Auto-generated method stub
		return assetrepo.getAllAssets();
	}

	@Override
	public List<Asset> getAssetById(String asid) {
		// TODO Auto-generated method stub
		return assetrepo.getAssetById(asid);
	}

	@Override
	public int updateAssetById(Asset ast) {
		// TODO Auto-generated method stub
		return assetrepo.updateAssetById(ast);
	}

	@Override
	public int getAssetQuantity(Long assetid) {
		// TODO Auto-generated method stub
		
		List<Asset> ast = assetrepo.getAssetById(String.valueOf(assetid));
		if(ast.size()>0)
		{
			return assetrepo.getAssetQuantity(assetid);
		}
		else {
			return 0;
		}
		
	}

	@Override
	public int updateAssetQuantityByAssetId(Long asid, String qty) {
		// TODO Auto-generated method stub
		
		System.err.println("Inside updateAssetQuantityByAssetId service\n");
		
		List<Asset> ast = assetrepo.getAssetById(String.valueOf(asid));
		if(ast.size()>0)
		{
			return assetrepo.updateAssetQuantityByAssetId(asid, qty);
		}
		else {
			return 0;
		}
	}
	

}
