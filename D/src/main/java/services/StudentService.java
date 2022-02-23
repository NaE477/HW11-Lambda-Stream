package services;

import models.things.Course;
import models.users.Student;
import repos.StudentRep;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService extends BaseService {
    private final StudentRep studentRep;
    private final CourseService courseService;

    public StudentService(Connection connection) {
        super(connection);
        studentRep = new StudentRep(super.getConnection());
        courseService = new CourseService(super.getConnection());
    }

    public Integer signUpStudent(Student student) {
        return studentRep.ins(student);
    }
    public Student find(Integer studentId){
        return studentRep.read(studentId);
    }
    public Student find(String username){
        Student student = studentRep.read(username);
        if(student != null) {
            student.setCourses(new ArrayList<>(courseService.findAllByStudent(student).keySet()));
        }
        return student;
    }
    public List<Student> findAll(){
        return studentRep.readAll();
    }
    public List<Student> findAll(Course course){
        return studentRep.readAll(course);
    }
    public Integer editProfile(Student student){
        return studentRep.update(student);
    }
    public Integer delete(Student student){
        return studentRep.delete(student);
    }
}
