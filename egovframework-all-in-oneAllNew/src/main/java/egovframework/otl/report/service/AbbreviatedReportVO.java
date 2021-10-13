package egovframework.otl.report.service;

public class AbbreviatedReportVO {

    /*primary key*/
    private String trainId;
    private String teamId;
    private String reportId;
    /*primary key*/

    private String reportStatus;
    private String reportType;
    private String reportTitle;
    private String reportContent;
    private String attachFileId;

    private String createUserId;
    private String createdDateTime;
    private String updatedDateTime;

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getAttachFileId() {
        return attachFileId;
    }

    public void setAttachFileId(String attachFileId) {
        this.attachFileId = attachFileId;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(String updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    @Override
    public String toString() {
        return "AbbreviatedReportVO{" +
                "trainId='" + trainId + '\'' +
                ", teamId='" + teamId + '\'' +
                ", reportId='" + reportId + '\'' +
                ", reportStatus='" + reportStatus + '\'' +
                ", reportType='" + reportType + '\'' +
                ", reportTitle='" + reportTitle + '\'' +
                ", reportContent='" + reportContent + '\'' +
                ", attachFileId='" + attachFileId + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", createdDateTime='" + createdDateTime + '\'' +
                ", updatedDateTime='" + updatedDateTime + '\'' +
                '}';
    }
}
