package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.AssignedAssets;
import com.example.demo.repository.AssignedAssetsRepo;


@Service("assignedassetserv")
public class AssignedAssetsServiceImpl implements AssignedAssetsService {

	@Autowired
	AssignedAssetsRepo assetrepo;
	
	@Override
	public int saveAssignedAssets(AssignedAssets assets) {
		// TODO Auto-generated method stub
		return assetrepo.saveAssignedAssets(assets);
	}

	@Override
	public List<AssignedAssets> getAllAssignedAssetsByEmpId(String empid) {
		// TODO Auto-generated method stub
		return assetrepo.getAllAssignedAssetsByEmpId(empid);
	}

}
