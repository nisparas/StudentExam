package loginMain;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import files.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service {
    private static final String FILE_NAME = "oop_answer.json";
    private final String directoryOne = "D:" + "\\" + "exams" + "\\" + "studentTest";
    Scanner sc = new Scanner(System.in);
    List<Result> resultsList = new ArrayList<>();
    List<ExamTest> examTestList = new ArrayList<>();
    LocalDateTime dateTime = LocalDateTime.now();
    List<String> answerSet = new ArrayList<>();
    List<Questions> questions = new ArrayList<>();
    String todayformat = dateTime.format(DateTimeFormatter.BASIC_ISO_DATE);


    public void startUp(ExamTest examTest, String userName) throws IOException {

        Setq setq = new Setq();
        setq = beginTest(examTest);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File fileResults = FileTool.createFileIfNotExist(directoryOne + "\\" + "finalResult.json");
        Result examResult = new Result(userName, setq.getExamId(), todayformat, setq.getCounter());
        resultsList.add(examResult);
        mapper.writeValue(fileResults, resultsList);
        System.out.println("finalResults failo rezultatai irasyti");
    }

    public Setq beginTest(ExamTest examTest) throws IOException {
        Setq setq = new Setq();
        int counter = 0;
        for (Questions q : examTest.questions) {
            System.out.println(q.getQuestionsCarcass());
            System.out.println("Atsakymu variantai zemiau");
            System.out.println(q.getAnswerOptions());
            System.out.println("Prasome pasirinkti teisinga atsakyma");
            boolean badOption = true;
            String answer = "";
            while (badOption) {
                answer = q.setStudentAnswers(sc.nextLine());
                switch (answer) {
                    case "A", "B", "C", "D" -> badOption = false;
                    default -> {
                        badOption = true;
                        System.out.println("Pasirinkite viena iš A B C D variantu");
                    }
                }
            }
            answerSet.add(answer);
            answerSet.add(q.getStudentAnswers());
            if (q.getGoodAnsw().equals(answer)) {
                counter++;
            }
        }
        setq.setExamId(examTest.getExamId());
        setq.setQuestion(answerSet);
        setq.setCounter(counter * 10 / answerSet.size());
        studentAnswerwrite(examTest);
        return setq;
    }

    public ExamTest addTest() {
        ExamTest examTest = new ExamTest();
        System.out.println("Iveskite egzamino ID, kuri norite ikelti");
        examTest.setExamId(sc.nextLine());
        System.out.println("Kiek klausimu bus egzamine");
        int testSize = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < testSize; i++) {
            System.out.println("Iveskite klausima " + (i + 1));
            Questions question = new Questions();
            question.setQuestionsCarcass(sc.nextLine());
            System.out.println(question.getQuestionsCarcass());
            for (AnswerId Id : AnswerId.values()) {
                System.out.println("Nustatykite atsakyma: " + Id);
                question.setAnswerOptionElements(Id, sc.nextLine());
            }
            System.out.println("Nustatykite teisinga atsakyma");
            question.setGoodAnsw(sc.nextLine());
            questions.add(question);
            examTest.setQuestions(questions);
        }
        return examTest;
    }

    public void testFileRead() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File file = FileTool.createFileIfNotExist(directoryOne + "\\" + FILE_NAME);
        mapper.readValue(file, ExamTest.class);

    }

    public void writeExamFile(ExamTest examTest) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File file = FileTool.createFileIfNotExist(directoryOne + "\\" + FILE_NAME);
        mapper.writeValue(file, examTest);
        System.out.println("oop answer failo rezultatai irasyti");
    }

    public void studentAnswerwrite(ExamTest examTest) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File file = FileTool.createFileIfNotExist(directoryOne + "\\" + "studentAnswer.json");
        mapper.writeValue(file, List.of(examTest, examTest.questions));
        System.out.println("StudentAnswer failo rezultatai irasyti");
    }

    public void printRez() {
        for (Result rezult : resultsList) {
            System.out.println(rezult.toString());
        }
    }
}

