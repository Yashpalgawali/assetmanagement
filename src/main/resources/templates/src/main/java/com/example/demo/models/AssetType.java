package com.example.demo.models;

public class AssetType {
	
	private Long type_id;
	
	private String type_name;

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	@Override
	public String toString() {
		return "AssetType [type_id=" + type_id + ", type_name=" + type_name + "]";
	}
	
	
}
