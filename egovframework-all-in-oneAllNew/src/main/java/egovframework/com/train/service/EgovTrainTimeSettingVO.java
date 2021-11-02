package egovframework.com.train.service;

import egovframework.com.cmm.ComDefaultVO;

public class EgovTrainTimeSettingVO extends ComDefaultVO{
	
	private static final long serialVersionUID = 1L;

	private String trainingId;
	private String trainingDate;
	private String startDatetime;
	private String endDatetime;
	
	private String ddosStartDatetime;
	private String ddosEndDatetime;
	private String ransomStartDatetime;
	private String ransomEndDatetime;
	private String whStartDatetime;
	private String whEndDatetime;
	private String apt01StartDatetime;
	private String apt01EndDatetime;
	private String apt02StartDatetime;
	private String apt02EndDatetime;

	/*사용자 접근 제한용 컬럼*/
	private String schedulingState;

	public String getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(String trainingId) {
		this.trainingId = trainingId;
	}
	public String getTrainingDate() {
		return trainingDate;
	}
	public void setTrainingDate(String trainingDate) {
		this.trainingDate = trainingDate;
	}
	public String getStartDatetime() {
		return startDatetime;
	}
	public void setStartDatetime(String startDatetime) {
		this.startDatetime = startDatetime;
	}
	public String getEndDatetime() {
		return endDatetime;
	}
	public void setEndDatetime(String endDatetime) {
		this.endDatetime = endDatetime;
	}
	public String getDdosStartDatetime() {
		return ddosStartDatetime;
	}
	public void setDdosStartDatetime(String ddosStartDatetime) {
		this.ddosStartDatetime = ddosStartDatetime;
	}
	public String getDdosEndDatetime() {
		return ddosEndDatetime;
	}
	public void setDdosEndDatetime(String ddosEndDatetime) {
		this.ddosEndDatetime = ddosEndDatetime;
	}
	public String getRansomStartDatetime() {
		return ransomStartDatetime;
	}
	public void setRansomStartDatetime(String ransomStartDatetime) {
		this.ransomStartDatetime = ransomStartDatetime;
	}
	public String getRansomEndDatetime() {
		return ransomEndDatetime;
	}
	public void setRansomEndDatetime(String ransomEndDatetime) {
		this.ransomEndDatetime = ransomEndDatetime;
	}
	public String getWhStartDatetime() {
		return whStartDatetime;
	}
	public void setWhStartDatetime(String whStartDatetime) {
		this.whStartDatetime = whStartDatetime;
	}
	public String getWhEndDatetime() {
		return whEndDatetime;
	}
	public void setWhEndDatetime(String whEndDatetime) {
		this.whEndDatetime = whEndDatetime;
	}
	public String getApt01StartDatetime() {
		return apt01StartDatetime;
	}
	public void setApt01StartDatetime(String apt01StartDatetime) {
		this.apt01StartDatetime = apt01StartDatetime;
	}
	public String getApt01EndDatetime() {
		return apt01EndDatetime;
	}
	public void setApt01EndDatetime(String apt01EndDatetime) {
		this.apt01EndDatetime = apt01EndDatetime;
	}
	public String getApt02StartDatetime() {
		return apt02StartDatetime;
	}
	public void setApt02StartDatetime(String apt02StartDatetime) {
		this.apt02StartDatetime = apt02StartDatetime;
	}
	public String getApt02EndDatetime() {
		return apt02EndDatetime;
	}
	public void setApt02EndDatetime(String apt02EndDatetime) {
		this.apt02EndDatetime = apt02EndDatetime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSchedulingState() {
		return schedulingState;
	}

	public void setSchedulingState(String schedulingState) {
		this.schedulingState = schedulingState;
	}
}
