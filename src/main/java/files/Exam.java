package files;

import java.util.List;

public abstract class Exam {

    private String examName;
    private String examId;

    private List<Object> questions;

    public abstract ExamName getExamName();

    public abstract void setExamName(String examName);


}
