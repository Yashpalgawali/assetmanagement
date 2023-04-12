package com.example.demo.controller;

import java.util.List;

import org.apache.el.parser.AstAssign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Asset;
import com.example.demo.models.AssetType;
import com.example.demo.service.AssetService;
import com.example.demo.service.AssetTypeService;


@Controller
public class AssetController {

	@Autowired
	AssetTypeService assettypeserv; 
	
	@Autowired
	AssetService assetserv;
	
	@GetMapping("/addasset")
	public String addAsset(Model model)
	{
		List<AssetType> atypes = assettypeserv.getAllAssetTypes();
		model.addAttribute("alist", atypes);
		
		return "AddAsset";
	}
	
	@RequestMapping("/saveasset")
	public String saveAsset(@ModelAttribute("Asset") Asset asset,RedirectAttributes attr)
	{
		int ast = assetserv.saveAsset(asset);
		if(ast > 0)
		{
			attr.addFlashAttribute("response", "Asset added successfully.");
			return "redirect:/viewassets";
		}
		else
		{
			attr.addFlashAttribute("reserr", "Asset is not added ");
			return "redirect:/viewassets";
		}	
	}
	
	@RequestMapping("/viewassets")
	public String viewAsset(Model model)
	{
		List<Asset> aslist = assetserv.getAllAssets();
	
		model.addAttribute("aslist", aslist);
		return "ViewAssets";
	}
	
	@RequestMapping("/editasset/{id}")
	public String editAssetById(@PathVariable("id") String aid,Model model,RedirectAttributes attr)
	{
		List<Asset> asset = assetserv.getAssetById(aid);
		
		Asset ast = null;
		for(int i=0;i<asset.size();i++)
		{
			ast = asset.get(i);
		}
		
		if(ast!=null)
		{
			List<AssetType> atype = assettypeserv.getAllAssetTypes();
			
			model.addAttribute("asset", ast);
			model.addAttribute("atype", atype);
			return "EditAsset";
		}
		else
		{
			attr.addFlashAttribute("reserr", "No asset found for given ID");
			return "redirect:/viewassets";
		}
	}
	
	@RequestMapping("updateasset")
	public String updateAssetById(@ModelAttribute("Asset") Asset asst,BindingResult br ,RedirectAttributes attr)
	{
		
		List<Asset> asset = assetserv.getAssetById(String.valueOf(asst.getAsset_id()));
		
		Asset asset_obj =null;
		for(int i=0;i<asset.size();i++)
		{
			asset_obj = asset.get(i);
		}
		String ol_name = asset_obj.getAsset_name();
		
		int ast = assetserv.updateAssetById(asst);
		
		if(ast > 0)
		{
			if(ol_name.equals(asst.getAsset_name())) 
			{
				attr.addFlashAttribute("response", "Detail(s) of "+ol_name+" Updated Successfully " );
				return "redirect:/viewassets";
			}
			else {
				attr.addFlashAttribute("response", "Detail(s) of "+ol_name+" updated to "+asst.getAsset_name()+"  Successfully" );
				return "redirect:/viewassets";
			}
		}
		else
		{
			attr.addFlashAttribute("reserr", "Detail(s) of "+ol_name+ " are not updated ");
			return "redirect:/viewassets";
		}	
	}
	
	
	@GetMapping("addassettype")
	public String addAssetType()
	{
		return "AddAssettype";
	}
	
	
	@PostMapping("/saveassettype")
	public String saveAssetType(@ModelAttribute("AssetType")AssetType astype,RedirectAttributes attr)
	{
		int ast = assettypeserv.saveAssetType(astype);
		
		if(ast > 0)
		{
			attr.addFlashAttribute("response", " Asset type saved successfully ");
			return "redirect:/viewassettype";
		}
		else
		{
			attr.addFlashAttribute("reserr", " Asset type is not saved ");
			return "redirect:/viewassettype";
		}	
	}
	
	@RequestMapping("/viewassettype")
	public String viewAssetTypes(Model model)
	{
		List<AssetType> ast = assettypeserv.getAllAssetTypes(); 
		
		model.addAttribute("aslist", ast);
		return "ViewAssetType";
	}
	
	@RequestMapping("/editassettype/{id}")
	public String editassettype(@PathVariable("id") String tid,Model model)
	{
		List<AssetType> ast = assettypeserv.getAssetById(tid);
		
		AssetType atype = null;
		
		for(int i=0;i<ast.size();i++){
			atype = ast.get(i);
		}
		
		model.addAttribute("types", atype);
		return "EditAssetType";	
	}
	
	@RequestMapping("updateassettype")
	public String updateAssetType(@ModelAttribute("AssetType") AssetType astype,RedirectAttributes attr)
	{
		int vale = assettypeserv.updateAssetType(astype);
		
		if(vale > 0)
		{
			attr.addFlashAttribute("response", " Asset type updated successfully ");
			return "redirect:/viewassettype";
		}
		else
		{
			attr.addFlashAttribute("reserr", " Asset type is not updated ");
			return "redirect:/viewassettype";
		}
	}
	
}
