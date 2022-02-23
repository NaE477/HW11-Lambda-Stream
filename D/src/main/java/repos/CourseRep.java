package repos;

import models.things.Course;
import models.users.Clerk;
import models.users.ProfPosition;
import models.users.Professor;
import models.users.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CourseRep extends BaseRepository<Course> implements Repository<Course> {
    public CourseRep(Connection connection) {
        super(connection);
    }

    @Override
    protected Course mapTo(ResultSet rs) {
        try {
            if (rs.next()) {
                return new Course(
                        rs.getInt("course_id"),
                        rs.getInt("course_unit"),
                        rs.getString("course_name"),
                        new Professor(
                                rs.getInt("prof_id"),
                                rs.getString("prof_firstname"),
                                rs.getString("prof_lastname"),
                                rs.getString("prof_username"),
                                rs.getString("prof_password"),
                                ProfPosition.valueOf(rs.getString("prof_position"))
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<Course> mapToList(ResultSet rs) {
        List<Course> courses = new ArrayList<>();
        try {
            while (rs.next()) {
                courses.add(
                        new Course(
                                rs.getInt("course_id"),
                                rs.getInt("course_unit"),
                                rs.getString("course_name"),
                                new Professor(
                                        rs.getInt("prof_id"),
                                        rs.getString("prof_firstname"),
                                        rs.getString("prof_lastname"),
                                        rs.getString("prof_username"),
                                        rs.getString("prof_password"),
                                        ProfPosition.valueOf(rs.getString("prof_position"))
                                )
                        )
                );
            }
            return courses;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer ins(Course course) {
        String insStmt = "INSERT INTO courses (course_name, course_unit, prof_id) " +
                "VALUES (?,?,?) RETURNING course_id;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(insStmt);
            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getUnits());
            ps.setInt(3, course.getProfessor().getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("course_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer insWithoutProf(Course course) {
        String insStmt = "INSERT INTO courses (course_name, course_unit) " +
                "VALUES (?,?) RETURNING course_id;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(insStmt);
            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getUnits());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("course_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Course read(Integer id) {
        String readStmt = "SELECT * FROM courses WHERE course_id = ?;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(readStmt);
            ps.setInt(1, id);
            return mapTo(ps.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Course> readAll() {
        String readStmt = "SELECT * FROM courses;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(readStmt);
            return mapToList(ps.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<Course,Double> readAllByStudent(Student student) {
        HashMap<Course,Double> courseWithGrade = new HashMap<>();
        String readStmt = "SELECT c.*,grade FROM course_to_student " +
                "INNER JOIN students s on s.student_id = course_to_student.student_id " +
                "INNER JOIN courses c on c.course_id = course_to_student.course_id " +
                "WHERE course_to_student.student_id = ?;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(readStmt);
            ps.setInt(1, student.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                courseWithGrade.put(
                        new Course(
                                rs.getInt("course_id"),
                                rs.getInt("course_unit"),
                                rs.getString("course_name"),
                                new Professor(
                                        rs.getInt("prof_id"),
                                        rs.getString("prof_firstname"),
                                        rs.getString("prof_lastname"),
                                        rs.getString("prof_username"),
                                        rs.getString("prof_password"),
                                        ProfPosition.valueOf(rs.getString("prof_position"))
                                )
                        ),
                        rs.getDouble("grade")
                );
            }
            return courseWithGrade;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer update(Course course) {
        String upStmt = "UPDATE courses SET course_name = ?,course_unit = ?,prof_id = ? WHERE course_id = ?;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(upStmt);
            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getUnits());
            ps.setInt(3, course.getProfessor().getId());
            ps.setInt(4, course.getId());
            ps.executeUpdate();
            return course.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delete(Course course) {
        String delStmt = "DELETE FROM courses WHERE course_id = ?;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(delStmt);
            ps.setInt(1, course.getId());
            ps.executeUpdate();
            return course.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
