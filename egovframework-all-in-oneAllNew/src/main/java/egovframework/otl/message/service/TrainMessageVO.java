package egovframework.otl.message.service;


public class TrainMessageVO extends TrainMessageForListVO {
    private String messageId;
    private String teamId;
    private String messageTitle;
    private String messageContent;
    private String createdUserId;

    private String createdDateTime;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public String toString() {
        return "TrainMessageVO{" +
                "messageId='" + messageId + '\'' +
                ", teamId='" + teamId + '\'' +
                ", messageTitle='" + messageTitle + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", createdUserId='" + createdUserId + '\'' +
                ", createdDateTime='" + createdDateTime + '\'' +
                '}';
    }
}
