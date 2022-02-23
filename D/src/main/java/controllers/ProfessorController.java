package controllers;

import models.things.Course;
import models.users.ProfPosition;
import models.users.Professor;
import models.users.Student;
import services.CourseService;
import services.ProfessorService;
import services.StudentService;
import services.TermService;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class ProfessorController {
    private final ProfessorService professorService;
    private final CourseService courseService;
    private final StudentService studentService;
    private final Professor professor;
    private final Integer term;
    private final Scanner sc = new Scanner(System.in);

    public ProfessorController(Connection connection, Professor professor) {
        professorService = new ProfessorService(connection);
        TermService termService = new TermService(connection);
        courseService = new CourseService(connection);
        studentService = new StudentService(connection);
        term = termService.getCurrentTerm();
        this.professor = professor;
    }

    public void entry() {
        System.out.println("Welcome to Professor Section," + professor.getFirstname() + " " + professor.getLastname() + ".\nChoose an Option:");
        label:
        while (true) {
            System.out.println("1-View Profile");
            System.out.println("2-Enter Grade");
            System.out.println("3-View Paycheck");
            System.out.println("4-Change Password");
            System.out.println("0-Exit");
            System.out.print("Option: ");
            String opt = sc.nextLine();

            switch (opt) {
                case "1":
                    System.out.println(professor);
                    break;
                case "2":
                    enterGrade();
                    break;
                case "3":
                    System.out.println("Which Term: ");
                    Integer term = termReceiver();
                    System.out.println(getSalary(term));
                    break;
                case "4":
                    changePassword();
                    break;
                case "0":
                    break label;
            }
        }
    }

    private void enterGrade() {
        List<Course> courses = courseService.findAllByProfessor(professor);
        courses.forEach(System.out::println);
        System.out.println("Enter Course ID: ");
        Integer courseID = Utilities.intReceiver();
        Course course = courseService.find(courseID);
        if (course != null && courses.contains(course)) {
            List<Student> students = studentService.findAll(course);
            System.out.println("Enter Student ID: ");
            Integer studentID = Utilities.intReceiver();
            Student student = studentService.find(studentID);
            if (student != null && students.contains(student)) {
                System.out.println("Enter Grade: ");
                Double grade = gradeReceiver();
                courseService.insertGradeForStudent(grade, course, student);
                System.out.println("Grade inserted for student");
            } else System.out.println("Wrong ID");
        } else System.out.println("Wrong ID");
    }

    private Double getSalary(Integer term) {
        AtomicReference<Double> salary = new AtomicReference<>((double) 0);
        List<Course> termCourses = professor
                .getCourses()
                .stream()
                .filter(course -> course.getTerm().equals(term))
                .collect(Collectors.toList());

        termCourses
                .stream()
                .mapToDouble(Course::getUnits)
                .forEach(unit -> salary.updateAndGet(v -> v + unit * 1000000.0));

        if (professor.getProfPosition().equals(ProfPosition.C)) {
            return salary.updateAndGet(v -> v + 5000000.0);
        } else {
            return salary.get();
        }
    }

    private void changePassword() {
        System.out.println("Old Password: ");
        String oldPass = sc.nextLine();
        System.out.println("New Password: ");
        String newPass = sc.nextLine();
        if (professor.getPassword().equals(oldPass)) {
            professor.setPassword(newPass);
            Integer changePassID = professorService.editProfile(professor);
            if (changePassID != null) System.out.println("Password changed successfully.");
            else System.out.println("Something went wrong with database");
        } else System.out.println("Old Password was Wrong.");
    }

    private Integer termReceiver() {
        while (true) {
            Integer term = Utilities.intReceiver();
            if (term <= this.term && term > 0) {
                return term;
            } else System.out.println("Wrong term,Choose a term between: " + "0-" + this.term);
        }
    }

    private Double gradeReceiver() {
        while (true) {
            Double grade = Utilities.doubleReceiver();
            if (grade <= 20.0 && grade > 0.0) {
                return grade;
            } else System.out.println("Wrong Grade,Enter a number between 0-20");
        }
    }
}
