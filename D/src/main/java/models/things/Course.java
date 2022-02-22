package models.things;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.users.Professor;

@Getter
@Setter
@AllArgsConstructor
public class Course {
    Integer id,units;
    String courseName;
    Professor professor;
}
