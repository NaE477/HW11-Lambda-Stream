package controllers;

import models.users.Clerk;
import models.users.Professor;
import models.users.Student;
import models.users.User;
import services.ClerkService;
import services.ProfessorService;
import services.StudentService;

import java.sql.Connection;
import java.util.*;

public class Entry {

    static Scanner sc = new Scanner(System.in);
    static Connection connection = ConClass.getInstance().getConnection();

    public static void main(String[] args) {
        System.out.println("Welcome to University App.\n");
        while (true) {
            System.out.println("Enter L/l to login or E/e to exit:");
            String opt = sc.nextLine().toUpperCase(Locale.ROOT);
            if (opt.equals("L")) {
                login();
            } else if (opt.equals("E")) {
                break;
            } else System.out.print("Wrong Option. Choose L/S/E: ");
        }
    }

    private static void login() {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        User user = auth(username, password);
        if (user != null) {
            if (user instanceof Clerk) {
                ClerkController clerkController = new ClerkController(connection, (Clerk) user);
                clerkController.entry();
            } else if (user instanceof Professor) {
                ProfessorController professorController = new ProfessorController(connection, (Professor) user);
                professorController.entry();
            } else if (user instanceof Student) {
                StudentController studentController = new StudentController(connection, (Student) user);
                studentController.entry();
            }
        } else System.out.println("Wrong Username/Password.");
    }

    private static User auth(String username, String password) {
        ClerkService clerkService = new ClerkService(connection);
        ProfessorService professorService = new ProfessorService(connection);
        StudentService studentService = new StudentService(connection);

        Clerk probableClerk = clerkService.find(username);
        Professor probableProfessor = professorService.find(username);
        Student probableStudent = studentService.find(username);

        if (probableClerk != null && probableClerk.getPassword().equals(password)) return probableClerk;
        else if (probableProfessor != null && probableProfessor.getPassword().equals(password)) return probableProfessor;
        else if (probableStudent != null && probableStudent.getPassword().equals(password)) return probableStudent;
        else return null;
    }

}
