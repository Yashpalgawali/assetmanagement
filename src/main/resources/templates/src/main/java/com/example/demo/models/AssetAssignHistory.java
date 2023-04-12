package com.example.demo.models;

public class AssetAssignHistory {

	
	private Long hist_id;
	
	private Long asset_id;
	
	private String operation_date;
	
	private String operation_time;
	
	private Long emp_id;
	
	private Long emp_code;
	
	private String assigned_assets;
	
	private Department dept;
	
	private Company company;
	
	private AssetType astype;
	
	private Asset asset;
	
	private String assigned_asset_type;
	
	private String operation;
	
	private Designation desig;
	
	
	
	public Designation getDesig() {
		return desig;
	}

	public void setDesig(Designation desig) {
		this.desig = desig;
	}

	public String getOperation_date() {
		return operation_date;
	}

	public void setOperation_date(String operation_date) {
		this.operation_date = operation_date;
	}

	public String getOperation_time() {
		return operation_time;
	}

	public void setOperation_time(String operation_time) {
		this.operation_time = operation_time;
	}

	public  String getOperation() {
		return operation;
	}

	public  void setOperation(String operation) {
		this.operation = operation;
	}

	public  Long getAsset_id() {
		return asset_id;
	}

	public  void setAsset_id(Long asset_id) {
		this.asset_id = asset_id;
	}

	public String getAssigned_asset_type() {
		return assigned_asset_type;
	}

	public void setAssigned_asset_type(String assigned_asset_type) {
		this.assigned_asset_type = assigned_asset_type;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public AssetType getAstype() {
		return astype;
	}

	public void setAstype(AssetType astype) {
		this.astype = astype;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getAssigned_assets() {
		return assigned_assets;
	}

	public void setAssigned_assets(String assigned_assets) {
		this.assigned_assets = assigned_assets;
	}

	public Long getEmp_code() {
		return emp_code;
	}

	public void setEmp_code(Long emp_code) {
		this.emp_code = emp_code;
	}

	private Employee employee;

	public Long getHist_id() {
		return hist_id;
	}

	public void setHist_id(Long hist_id) {
		this.hist_id = hist_id;
	}

	public Long getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Long emp_id) {
		this.emp_id = emp_id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
