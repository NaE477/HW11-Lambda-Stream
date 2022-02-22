package services;

import models.things.Course;
import repos.CourseRep;

import java.sql.Connection;

public class CourseService extends BaseService {
    private final CourseRep courseRep;

    public CourseService(Connection connection) {
        super(connection);
        courseRep = new CourseRep(super.getConnection());
    }

    public Integer createNewCourse(Course course) {
        return courseRep.ins(course);
    }

    public Course find(Integer id) {
        return courseRep.read(id);
    }

    public Integer editCourse(Course course) {
        return courseRep.update(course);
    }

    public Integer deleteCourse(Course course) {
        return courseRep.delete(course);
    }
}
