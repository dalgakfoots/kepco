package egovframework.otl.train.train;

public class EgovTrainVO {

	private String faqId;
	private String answer;
	private String trainType;
	private String trainTypeName;
	
	public String getFaqId() {
		return faqId;
	}
	public void setFaqId(String faqId) {
		this.faqId = faqId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getTrainType() {
		return trainType;
	}
	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}
	public String getTrainTypeName() {
		return trainTypeName;
	}
	public void setTrainTypeName(String trainTypeName) {
		this.trainTypeName = trainTypeName;
	}
	
	@Override
	public String toString() {
		return "EgovTrainVO [faqId=" + faqId + ", answer=" + answer + ", trainType=" + trainType + ", trainTypeName="
				+ trainTypeName + "]";
	}
	
	
}
