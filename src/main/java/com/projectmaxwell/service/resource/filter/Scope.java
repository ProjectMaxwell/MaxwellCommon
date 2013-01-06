package com.projectmaxwell.service.resource.filter;

public enum Scope {
	CUD_SYSTEM_METADATA("cud_system_metadata"),
	READ_USER_LIST2("read_user_list");
	
	public static final String READ_USER_LIST = "read_user_list";
	
	private String value;
	
	private Scope(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
}
