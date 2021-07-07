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

public class VmType extends ComDefaultVO {
	private static final long serialVersionUID = 1L;
	private VmType vmType;
	
	private String id;
	private String type;
	private String createdDatetime;
	
	public VmType getVmType() {
		return vmType;
	}
	public void setVmType(VmType vmType) {
		this.vmType = vmType;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getCreatedDatetime() {
		return createdDatetime;
	}
	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
	}



}