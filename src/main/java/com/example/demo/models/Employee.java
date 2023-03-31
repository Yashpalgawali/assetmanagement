package com.example.demo.models;

public class Employee {

	private Long 	emp_id;
	
	private String 	emp_name;
	
	private String 	emp_email;
	
	private String	emp_contact;
	
	private Long 	dept_id;
	
	private Long 	asset_id;
	
	private String 	multi_assets;
	
	private Long 	emp_code;
	
	private String 	asset_names;
	
	private String 	asset_types;
	
	private Department department;
	
	private String emp_codes;
	
	public  String getEmp_codes() {
		return emp_codes;
	}

	public  void setEmp_codes(String emp_codes) {
		this.emp_codes = emp_codes;
	}

	public  String getAsset_types() {
		return asset_types;
	}

	public  void setAsset_types(String asset_types) {
		this.asset_types = asset_types;
	}

	public  String getAsset_names() {
		return asset_names;
	}

	public  void setAsset_names(String asset_names) {
		this.asset_names = asset_names;
	}

	public Long getEmp_code() {
		return emp_code;
	}

	public void setEmp_code(Long emp_code) {
		this.emp_code = emp_code;
	}

	public String getMulti_assets() {
		return multi_assets;
	}

	public void setMulti_assets(String multi_assets) {
		this.multi_assets = multi_assets;
	}

	private Asset asset;
	
	private AssetType astype;
	
	private Long type_id;
	
	private Company company;
	
	private Long comp_id;
	
	private Designation designation;
	
	private Long desig_id;
	
	
	public  Designation getDesignation() {
		return designation;
	}

	public  void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public  Long getDesig_id() {
		return desig_id;
	}

	public  void setDesig_id(Long desig_id) {
		this.desig_id = desig_id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Long getComp_id() {
		return comp_id;
	}

	public void setComp_id(Long comp_id) {
		this.comp_id = comp_id;
	}

	public AssetType getAstype() {
		return astype;
	}

	public void setAstype(AssetType astype) {
		this.astype = astype;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public Long getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Long emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_email() {
		return emp_email;
	}

	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}

	public String getEmp_contact() {
		return emp_contact;
	}

	public void setEmp_contact(String emp_contact) {
		this.emp_contact = emp_contact;
	}

	public Long getDept_id() {
		return dept_id;
	}

	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Long getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(Long asset_id) {
		this.asset_id = asset_id;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	@Override
	public String toString() {
		return "Employee [emp_id=" + emp_id + ", emp_name=" + emp_name + ", emp_email=" + emp_email + ", emp_contact="
				+ emp_contact + ", dept_id=" + dept_id + ", asset_id=" + asset_id + ", multi_assets=" + multi_assets
				+ ", emp_code=" + emp_code + ", asset_names=" + asset_names + ", asset_types=" + asset_types
				+ ", department=" + department + ", emp_codes=" + emp_codes + ", asset=" + asset + ", astype=" + astype
				+ ", type_id=" + type_id + ", company=" + company + ", comp_id=" + comp_id + ", designation="
				+ designation + ", desig_id=" + desig_id + "]";
	}

	
	
	

}
