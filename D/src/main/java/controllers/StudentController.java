package controllers;

import models.users.Student;
import services.StudentService;

import java.sql.Connection;

public class StudentController {
    private final StudentService studentService;
    private Student student;

    public StudentController(Connection connection, Student student) {
        this.studentService = new StudentService(connection);
        this.student = student;
    }
}
