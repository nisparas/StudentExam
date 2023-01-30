package files;

import java.util.List;

public class ExamTest extends Exam {

    public String examName;
    public String examId;
    public List<Questions> questions;


    public ExamTest(String examName, String examId, List<Questions> questions) {
        this.examName = examName;
        this.examId = examId;
        this.questions = questions;
    }

    public ExamTest() {

    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

    @Override
    public ExamName getExamName() {
        return ExamName.OOP_PAGRINDAI;
    }

    @Override
    public void setExamName(String examName) {

    }

    @Override
    public String toString() {
        return "ExamTest{" +
                "examName='" + examName + '\'' +
                ", examId='" + examId + '\'' +
                ", questions=" + questions +
                '}';
    }
}
