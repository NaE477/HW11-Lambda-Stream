package repos;

import models.things.Course;
import models.users.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CourseToStudentRep {
    private final Connection connection;

    public CourseToStudentRep(Connection connection) {
        this.connection = connection;
    }

    public void ins(Course course, Student student) {
        String insStmt = "INSERT INTO course_to_student (course_id, student_id) VALUES (?,?);";
        try {
            PreparedStatement ps = connection.prepareStatement(insStmt);
            ps.setInt(1, course.getId());
            ps.setInt(2, student.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insGrade(Double grade,Course course,Student student) {
        String insStmt = "UPDATE course_to_student SET grade = ? WHERE course_id = ? AND student_id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(insStmt);
            ps.setDouble(1,grade);
            ps.setInt(2,course.getId());
            ps.setInt(3,student.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void del(Student student) {
        String delStmt = "DELETE FROM course_to_student WHERE student_id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(delStmt);
            ps.setInt(1, student.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void del(Course course) {
        String delStmt = "DELETE FROM course_to_student WHERE course_id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(delStmt);
            ps.setInt(1, course.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
