package services;

import models.users.Student;
import repos.StudentRep;

import java.sql.Connection;
import java.util.List;

public class StudentService extends BaseService {
    private final StudentRep studentRep;

    public StudentService(Connection connection) {
        super(connection);
        studentRep = new StudentRep(super.getConnection());
    }

    public Integer signUpStudent(Student student) {
        return studentRep.ins(student);
    }
    public Student find(Integer studentId){
        return studentRep.read(studentId);
    }
    public Student find(String username){
        return studentRep.read(username);
    }
    public List<Student> findAll(){
        return studentRep.readAll();
    }
    public Integer editProfile(Student student){
        return studentRep.update(student);
    }
    public Integer delete(Student student){
        return studentRep.delete(student);
    }
}
