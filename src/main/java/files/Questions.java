package files;

import java.util.HashMap;
import java.util.Map;

public class Questions  {
    private String questionsCarcass;
    private Map<AnswerId, String> answerOptions = new HashMap<>();
    private String goodAnsw;
    private String studentAnswers;

    public String getStudentAnswers() {
        return studentAnswers;
    }

    public String setStudentAnswers(String studentAnswers) {
        this.studentAnswers = studentAnswers;
        return studentAnswers;
    }

    public Questions(String questionsCarcass, Map<AnswerId, String> answerOptions, String goodAnsw) {
        this.questionsCarcass = questionsCarcass;
        this.answerOptions = answerOptions;
        this.goodAnsw = goodAnsw;
    }

    public Questions() {
    }

    public String getQuestionsCarcass() {
        return questionsCarcass;
    }

    public void setQuestionsCarcass(String questionsCarcass) {
        this.questionsCarcass = questionsCarcass;
    }

    public Map<AnswerId, String> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(Map<AnswerId, String> answerOptions) {
        this.answerOptions = answerOptions;
    }

    public String getGoodAnsw() {
        return goodAnsw;
    }

    public void setGoodAnsw(String goodAnsw) {
        this.goodAnsw = goodAnsw;
    }
    public void setAnswerOptionElements(AnswerId id, String answerOption) {
        answerOptions.put(id, answerOption);
    }

    @Override
    public String toString() {
        return "Questions{" +
                "questionsCarcass='" + questionsCarcass + '\'' +
                ", answerOptions=" + answerOptions +
                ", goodAnsw='" + goodAnsw + '\'' +
                '}';
    }
}
