package models.things;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.users.Professor;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Course {
    Integer id,units;
    String courseName;
    Professor professor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
