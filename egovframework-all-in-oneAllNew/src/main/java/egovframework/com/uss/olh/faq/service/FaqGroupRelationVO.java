package egovframework.com.uss.olh.faq.service;

public class FaqGroupRelationVO extends FaqDefaultVO {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String faqGroupId;
	private String faqId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFaqGroupId() {
		return faqGroupId;
	}
	public void setFaqGroupId(String faqGroupId) {
		this.faqGroupId = faqGroupId;
	}
	public String getFaqId() {
		return faqId;
	}
	public void setFaqId(String faqId) {
		this.faqId = faqId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
