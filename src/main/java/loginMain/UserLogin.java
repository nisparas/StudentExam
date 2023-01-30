package loginMain;

import files.ExamTest;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserLogin {
    private final Map<String, User> userMap = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    void startup() throws IOException {
        UserLogin userLogin = new UserLogin();
        String choice;
        do {
            userLogin.menu();
            choice = sc.nextLine();
            userLogin.userChoice(sc, choice);
        } while (!choice.equals("3"));
    }

    private void menu() {
        System.out.println(" __________________________________");
        System.out.println("|  1. Registruoti nauja vartotoja  |");
        System.out.println("|  2. Prisijungti                  |");
        System.out.println("|  3. Iseiti is aplikacijos        |");
        System.out.println("|__________________________________|");
    }

    private void userChoice(Scanner sc, String choice) throws IOException {
        switch (choice) {
            case "1" -> userRegistration(sc);
            case "2" -> userLogin(sc);
            case "3" -> System.out.println("Iseinama is aplikacijos");
            default -> System.out.println("Tokio pasirinkimo nera");
        }
    }

    private void userRegistration(Scanner sc) {
        User user;
        String userName;
        do {
            System.out.println("Iveskite userName");
            userName = sc.nextLine();
            user = userMap.get(userName);
            if (user != null) {
                System.out.println("Tokio vartotojas jau yra");
            }
        } while (user != null);
        System.out.println("Iveskite varda");
        String name = sc.nextLine();
        System.out.println("Iveskite pavarde");
        String userSurname = sc.nextLine();
        System.out.println("Iveskite slaptazodi");
        boolean passwordLength;
        String password;
        do { password = sc.nextLine();
            passwordLength = passwordLength(password);
        }while (!passwordLength);
        String repeatPassword;
        do {
           System.out.println("Pakartokite slaptazodi");
        repeatPassword = sc.nextLine();
        } while (!repeatPassword.equals(password));
        user = new User(userName, name, userSurname,DigestUtils.sha512Hex(password));
        userMap.put(userName, user);
        System.out.printf("Sveikiname prisiregistravus %s %s%n", name, userSurname);
    }

    private void userLogin(Scanner sc) throws IOException {
        ExamTest examTest = new ExamTest();
        Service testService = new Service();
        System.out.println("Iveskite userName");
        String userName = sc.nextLine();
        User user = userMap.get(userName);
        if (user == null) {
            System.out.println("Tokio vartotojo nera: " + userName);
            return;
        }
        System.out.println("Iveskite slaptazodi");
        String password = sc.nextLine();
        if (!user.userPassword().equals(DigestUtils.sha512Hex(password))) {
            System.out.println("Slaptazodis neteisingas");
            return;
        }
        System.out.printf("Sveikiname prisijungus %s %s%n ", user.name(), user.userSurname());

        boolean forward = true;
        while (forward) {
            System.out.println(" __________________________________");
            System.out.println("|  1. Sukurti testa                |");
            System.out.println("|  2. Pradeti testa                |");
            System.out.println("|  3. Gauti rezultata              |");
            System.out.println("|  4. Iseiti is aplikacijos        |");
            System.out.println("|__________________________________|");

            String userAction = sc.nextLine();
            switch (userAction) {
                case "1" -> {
                    examTest = testService.addTest(sc);
                    testService.writeExamFile(examTest, testService.directoryOne);
                }
                case "2" -> testService.startUp(sc, examTest, testService.directoryOne, userMap.toString());
                case "3" -> testService.studentAnswerRead(testService.directoryOne);
                case "4" -> forward = false;
                default -> System.out.println("Tokio pasirinkimo nera");

            }
        }
    }
    public boolean passwordLength(String password) {
        boolean passwordLength = true;
        if (password.length() < 5) {
            passwordLength = false;
            System.out.println("Jūsų slaptažodis per trumpas, pasirinkite kitą slaptažodį");
        }
        if (password.equals(password.toLowerCase())) {
            passwordLength = false;
            System.out.println("Jūsų slaptažodyje yra tik mažosios raidės, pasirinkite kitą slaptažodį");
        }
        if (password.equals(password.toUpperCase())) {
            passwordLength = false;
            System.out.println("Jūsų slaptažodį sudaro tik didžiosios raidės, pasirinkite kitą slaptažodį");
        }
        return passwordLength;
    }


    }




