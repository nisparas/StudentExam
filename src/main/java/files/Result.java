package files;

public class Result {
    private String userName;
    private String examId;
    private String dateAndTime;
    private int assessment;

    public Result(String userName, String examId, String dateAndTime, int assessment) {
        this.userName = userName;
        this.examId = examId;
        this.dateAndTime = dateAndTime;
        this.assessment = assessment;
    }
    public Result() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getAssessment() {
        return assessment;
    }

    public void setAssessment(int assessment) {
        this.assessment = assessment;
    }

    @Override
    public String toString() {
        return "Result{" +
                "userName='" + userName + '\'' +
                ", examId='" + examId + '\'' +
                ", dateAndTime='" + dateAndTime + '\'' +
                ", assessment=" + assessment +
                '}';
    }
}
