package services;

import models.things.Course;
import models.users.Student;
import repos.CourseRep;
import repos.CourseToStudentRep;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

public class CourseService extends BaseService {
    private final CourseRep courseRep;
    private final CourseToStudentRep courseToStudentRep;

    public CourseService(Connection connection) {
        super(connection);
        courseRep = new CourseRep(super.getConnection());
        courseToStudentRep = new CourseToStudentRep(super.getConnection());
    }

    public Integer createNewCourse(Course course) {
        return courseRep.ins(course);
    }

    public Integer createNewCourseWithoutProfessor(Course course) {
        return courseRep.insWithoutProf(course);
    }

    public Course find(Integer id) {
        return courseRep.read(id);
    }

    public List<Course> findAll() {
        return courseRep.readAll();
    }

    public HashMap<Course,Double> findAllByStudent(Student student) {
        return courseRep.readAllByStudent(student);
    }
    public void pickCourse(Course course,Student student){
        courseToStudentRep.ins(course,student);
    }

    public Integer editCourse(Course course) {
        return courseRep.update(course);
    }

    public Integer deleteCourse(Course course) {
        courseToStudentRep.del(course);
        return courseRep.delete(course);
    }
}
