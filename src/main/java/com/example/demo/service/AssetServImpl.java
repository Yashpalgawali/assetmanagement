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
	
	

}
