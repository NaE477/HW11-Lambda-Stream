package models.users;

import lombok.*;
import models.things.Course;

import java.util.List;

@Getter
@Setter
public class Professor extends User{
    List<Course> courses;
    Double salary;
    ProfPosition profPosition;

    public Professor(Integer professorId,String firstname,String lastname,String username,String password,ProfPosition profPosition){
        super(professorId,firstname,lastname,username,password);
        this.profPosition = profPosition;
    }
}
