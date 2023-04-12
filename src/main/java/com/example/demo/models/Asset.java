package com.example.demo.models;




public class Asset {
	
	private Long asset_id;
	
	private String asset_name;
	
	private String asset_number;
	
	private String model_number;
	
	private AssetType assettype;
	
	private Long type_id;
	
	private String quantity;
	
	private String model_numbers;
	
	private String asset_numbers;
	
	
	
	public String getAsset_numbers() {
		return asset_numbers;
	}

	public void setAsset_numbers(String asset_numbers) {
		this.asset_numbers = asset_numbers;
	}

	public String getModel_numbers() {
		return model_numbers;
	}

	public void setModel_numbers(String model_numbers) {
		this.model_numbers = model_numbers;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public Long getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(Long asset_id) {
		this.asset_id = asset_id;
	}

	public String getAsset_name() {
		return asset_name;
	}

	public void setAsset_name(String asset_name) {
		this.asset_name = asset_name;
	}

	public String getAsset_number() {
		return asset_number;
	}

	public void setAsset_number(String asset_number) {
		this.asset_number = asset_number;
	}

	public String getModel_number() {
		return model_number;
	}

	public void setModel_number(String model_number) {
		this.model_number = model_number;
	}

	public AssetType getAssettype() {
		return assettype;
	}

	public void setAssettype(AssetType assettype) {
		this.assettype = assettype;
	}

	@Override
	public String toString() {
		return "Asset [asset_id=" + asset_id + ", asset_name=" + asset_name + ", asset_number=" + asset_number
				+ ", model_number=" + model_number + ", assettype=" + assettype + ", type_id=" + type_id + ", quantity="
				+ quantity + ", model_numbers=" + model_numbers + ", asset_numbers=" + asset_numbers + "]";
	}

	
	
}
