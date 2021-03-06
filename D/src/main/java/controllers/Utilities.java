package controllers;

import services.ClerkService;
import services.ProfessorService;
import services.StudentService;

import java.sql.Connection;
import java.util.Scanner;

public class Utilities {
    static Scanner sc = new Scanner(System.in);
    private static final Connection connection = ConClass.getInstance().getConnection();
    private static final ClerkService clerkService = new ClerkService(connection);
    private static final ProfessorService professorService = new ProfessorService(connection);
    private static final StudentService studentService = new StudentService(connection);

    public static String usernameReceiver() {
        while (true) {
            System.out.println("Username: ");
            String username = sc.nextLine();
            if (clerkService.find(username) != null || professorService.find(username) != null || studentService.find(username) != null) {
                System.out.println("This username Already Exists! Try another one: ");
            } else {
                return username;
            }
        }
    }

    public static Integer intReceiver() {
        while (true) {
            try {
                int output = Integer.parseInt(sc.nextLine());
                if (output > 0) return output;
                else System.out.println("Enter a number bigger than 0");
            } catch (NumberFormatException e) {
                System.out.println("Only numbers are allowed here.");
            }
        }
    }

    public static Double doubleReceiver() {
        while (true) {
            try {
                double output = Double.parseDouble(sc.nextLine());
                if (output > 0) return output;
                else System.out.println("Enter a number bigger than 0");
            } catch (NumberFormatException e) {
                System.out.println("Only numbers are allowed here.");
            }
        }
    }
}
