package models.users;

import lombok.*;
import models.things.Course;

import java.util.List;

@Getter
@Setter
public class Student extends User{
    List<Course> courses;

    public Student(Integer studentId,String firstname,String lastname,String username,String password){
        super(studentId,firstname,lastname,username,password);
    }

    @Override
    public String toString() {
        return "ID: " + getId() +
                ", Full Name: " + getFirstname() + " " + getLastname() +
                ", Username: " + getUsername();
    }
}
