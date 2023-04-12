package com.example.demo.models;

public class AssignedAssets {

	
	private Long assigned_asset_id;
	
	private Long  emp_id;
	
	private Long asset_id;

	private String assign_date;
	
	private String assign_time;
	
	private Employee employee;
	
	private Asset asset;
	
	private String asset_names;
	
	private String asset_types;
	
	private AssetType atype;
	
	private Designation desig;
	
	private Company company;
	
	private Department depart;
	
	
	
	public Designation getDesig() {
		return desig;
	}

	public void setDesig(Designation desig) {
		this.desig = desig;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Department getDepart() {
		return depart;
	}

	public void setDepart(Department depart) {
		this.depart = depart;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public String getAsset_names() {
		return asset_names;
	}

	public void setAsset_names(String asset_names) {
		this.asset_names = asset_names;
	}

	public String getAsset_types() {
		return asset_types;
	}

	public void setAsset_types(String asset_types) {
		this.asset_types = asset_types;
	}

	public AssetType getAtype() {
		return atype;
	}

	public void setAtype(AssetType atype) {
		this.atype = atype;
	}

	public Long getAssigned_asset_id() {
		return assigned_asset_id;
	}

	public void setAssigned_asset_id(Long assigned_asset_id) {
		this.assigned_asset_id = assigned_asset_id;
	}

	public Long getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Long emp_id) {
		this.emp_id = emp_id;
	}

	public Long getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(Long asset_id) {
		this.asset_id = asset_id;
	}

	public String getAssign_date() {
		return assign_date;
	}

	public void setAssign_date(String assign_date) {
		this.assign_date = assign_date;
	}

	public String getAssign_time() {
		return assign_time;
	}

	public void setAssign_time(String assign_time) {
		this.assign_time = assign_time;
	}
	
	
	
}
