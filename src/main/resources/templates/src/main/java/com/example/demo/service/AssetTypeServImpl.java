package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.AssetType;
import com.example.demo.repository.AssetTypeRepository;


@Service("assettypeserv")
public class AssetTypeServImpl implements AssetTypeService {

	@Autowired
	AssetTypeRepository assettyperepo;
	
	@Override
	public int saveAssetType(AssetType astype) {
		// TODO Auto-generated method stub
		return assettyperepo.saveAssetType(astype);
	}

	@Override
	public List<AssetType> getAllAssetTypes() {
		// TODO Auto-generated method stub
		return assettyperepo.getAllAssetTypes();
	}

	@Override
	public List<AssetType> getAssetById(String aid) {
		// TODO Auto-generated method stub
		
		return assettyperepo.getAssetById(aid);
	}

	@Override
	public int updateAssetType(AssetType ast) {
		// TODO Auto-generated method stub
		return assettyperepo.updateAssetType(ast);
	}

}
