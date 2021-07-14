package egovframework.com.uss.olh.faq.service;

public class FaqGroupVO extends FaqDefaultVO{

	private static final long serialVersionUID = 1L;

	private String faqGroupId;
	private String faqGroupNm;
	private String faqGroupDc;
	private String type;
	
	public String getFaqGroupId() {
		return faqGroupId;
	}
	public void setFaqGroupId(String faqGroupId) {
		this.faqGroupId = faqGroupId;
	}
	
	public String getFaqGroupNm() {
		return faqGroupNm;
	}
	public void setFaqGroupNm(String faqGroupNm) {
		this.faqGroupNm = faqGroupNm;
	}
	public String getFaqGroupDc() {
		return faqGroupDc;
	}
	public void setFaqGroupDc(String faqGroupDc) {
		this.faqGroupDc = faqGroupDc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "FaqGroupVO [faqGroupId=" + faqGroupId + ", faqGroupNm=" + faqGroupNm + ", faqGroupDc=" + faqGroupDc
				+ ", type=" + type + "]";
	}
	
	
	
}
