package egovframework.com.sec.gmt.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 그룹관리에 대한 model 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이문준          최초 생성
 *
 * </pre>
 */

public class VmGroupType extends ComDefaultVO {
	private static final long serialVersionUID = 1L;
	private VmGroupType vmGroupType;
	
	private String groupId;
	private String typeId;
	private String vmGroupTypeId;
	private String url;
	private String createdDatetime;
	
	public VmGroupType getVmGroupType() {
		return vmGroupType;
	}
	public void setVmGroupType(VmGroupType vmGroupType) {
		this.vmGroupType = vmGroupType;
	}
	
	public String getGroupId() {
		return groupId;
	}
	
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getVmGroupTypeId() {
		return vmGroupTypeId;
	}
	public void setVmGroupTypeId(String vmGroupTypeId) {
		this.vmGroupTypeId = vmGroupTypeId;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getCreatedDatetime() {
		return createdDatetime;
	}
	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
	}



}