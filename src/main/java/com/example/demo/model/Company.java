package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Company {
	
	@Id
	private long companyCode;
	private String comapnayName;
	private String location;
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Company(long companyCode, String comapnayName, String location) {
		super();
		this.companyCode = companyCode;
		this.comapnayName = comapnayName;
		this.location = location;
	}
	public long getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(long companyCode) {
		this.companyCode = companyCode;
	}
	public String getComapnayName() {
		return comapnayName;
	}
	public void setComapnayName(String comapnayName) {
		this.comapnayName = comapnayName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Company [companyCode=" + companyCode + ", comapnayName=" + comapnayName + ", location=" + location
				+ "]";
	}
	
	

}
