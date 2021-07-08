package egovframework.com.sec.gmt.service;

import egovframework.com.cmm.ComDefaultVO;

public class TrainGroupVmVO extends ComDefaultVO {

	private static final long serialVersionUID = 844901341617982163L;
	
	private String mappingId;
	private String trainId;
	private String userGroupId;
	private String vmGroupId;
	
	public String getMappingId() {
		return mappingId;
	}
	public void setMappingId(String mappingId) {
		this.mappingId = mappingId;
	}
	public String getTrainId() {
		return trainId;
	}
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	public String getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}
	public String getVmGroupId() {
		return vmGroupId;
	}
	public void setVmGroupId(String vmGroupId) {
		this.vmGroupId = vmGroupId;
	}
	
}
