package com.example.dto;

public class DashboardResponse {
	
	private Integer totalEnquires;
	private Integer openEnquires;
	private Integer enrolledEnquires;
	private Integer lostEnquires;
	public Integer getTotalEnquires() {
		return totalEnquires;
	}
	public void setTotalEnquires(Integer totalEnquires) {
		this.totalEnquires = totalEnquires;
	}
	public Integer getOpenEnquires() {
		return openEnquires;
	}
	public void setOpenEnquires(Integer openEnquires) {
		this.openEnquires = openEnquires;
	}
	public Integer getEnrolledEnquires() {
		return enrolledEnquires;
	}
	public void setEnrolledEnquires(Integer enrolledEnquires) {
		this.enrolledEnquires = enrolledEnquires;
	}
	public Integer getLostEnquires() {
		return lostEnquires;
	}
	public void setLostEnquires(Integer lostEnquires) {
		this.lostEnquires = lostEnquires;
	}
	
	
}
