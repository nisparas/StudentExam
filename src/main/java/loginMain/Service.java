package loginMain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import files.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service {
    private static final String FILE_NAME = "examresults.json";
    String directoryOne = "D:" + "\\" + "exams" + "\\" + "oop-basics";
    List<Result> resultsList = new ArrayList<>();
    LocalDateTime dateTime = LocalDateTime.now();
    String Todayformat = dateTime.format(DateTimeFormatter.BASIC_ISO_DATE);


    public ExamTest addTest(Scanner sc) {
        ExamTest examTest = new ExamTest();
        System.out.println("Įveskite egzamino ID, kurį norite įkelti");
        examTest.setExamId(sc.nextLine());
        System.out.println("Įveskite egzamino pavadinimą, kurį norite įkelti");
        examTest.setExamName(sc.nextLine());
        System.out.println("Kiek klausimų bus egzamine");
        int testSize = Integer.parseInt(sc.nextLine());
        List<Questions> questions = new ArrayList<>();

        for (int i = 0; i < testSize; i++) {
            System.out.println("Iveskite klausimą " + (i + 1));
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


    public void startUp(Scanner sc, ExamTest examTest, String directoryOne, String userName) throws IOException {
        Setq setq = new Setq();
        setq = beginTest(sc, examTest);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File fileResults = FileTool.createFileIfNotExist(directoryOne + "\\" + FILE_NAME);
        Result examResult = new Result(userName, setq.getExamId(), Todayformat, setq.getCounter());
        resultsList.add(examResult);
        mapper.writeValue(fileResults, resultsList);
        System.out.println("examresults failo rezultatai irasyti");
    }

    public Setq beginTest(Scanner sc, ExamTest examTest) {
        Setq setq = new Setq();
        List<String> answerSet = new ArrayList<>();
        int counter = 0;
        for (Questions q : examTest.questions) {
            System.out.println(q.getQuestionsCarcass());
            System.out.println("Atsakymų variantai žemiau");
            System.out.println(q.getAnswerOptions());
            System.out.println("Prašome pasirinkti teisingą atsakymą");
            boolean badOption = true;
            String answer = "";
            while (badOption) {
                answer = sc.nextLine();
                switch (answer) {
                    case "A", "B", "C", "D" -> badOption = false;
                    default -> {
                        badOption = true;
                        System.out.println("Pasirinkite vieną iš A B C D variantų");
                    }
                }
            }
            answerSet.add(answer);
            if (q.getGoodAnsw().equals(answer)) {
                counter++;
            }
        }
        setq.setExamId(examTest.getExamId());
        setq.setQuestion(answerSet);
        // pagal 10 vertinimo sistemą
        setq.setCounter(counter * 10 / answerSet.size());
        return setq;
    }

    public void writeExamFile(ExamTest examTest, String directoryOne) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = FileTool.createFileIfNotExist(directoryOne + "\\" + "oop_answer.json");
        mapper.writeValue(file, examTest);
        System.out.println("oop answer failo rezultatai irasyti ");
    }

    public void studentAnswerRead(String directoryOne) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File file = FileTool.createFileIfNotExist(directoryOne + "\\" + FILE_NAME);
        if (file.equals(resultsList)) {
           resultsList = mapper.readValue(file, new TypeReference<>() {
            });
            resultsList.forEach(System.out::println);

        }
    }
    }


