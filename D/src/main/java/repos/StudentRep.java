package repos;

import models.users.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRep extends BaseRepository<Student> implements Repository<Student> {
    public StudentRep(Connection connection) {
        super(connection);
    }

    @Override
    protected Student mapTo(ResultSet rs) {
        try {
            if (rs.next()) {
                return new Student(
                        rs.getInt("student_id"),
                        rs.getString("student_firstname"),
                        rs.getString("student_lastname"),
                        rs.getString("student_username"),
                        rs.getString("student_password")
                );
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<Student> mapToList(ResultSet rs) {
        List<Student> students = new ArrayList<>();
        try {
            while (rs.next()) {
                students.add(
                        new Student(
                                rs.getInt("student_id"),
                                rs.getString("student_firstname"),
                                rs.getString("student_lastname"),
                                rs.getString("student_username"),
                                rs.getString("student_password")
                        )
                );
            }
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer ins(Student student) {
        String insStmt = "INSERT INTO students (student_firstname, student_lastname, student_username, student_password) " +
                "VALUES (?,?,?,?) RETURNING student_id;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(insStmt);
            ps.setString(1,student.getFirstname());
            ps.setString(2,student.getLastname());
            ps.setString(3,student.getUsername());
            ps.setString(4,student.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("student_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student read(Integer id) {
        String readStmt = "SELECT * FROM students WHERE student_id = ?;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(readStmt);
            ps.setInt(1,id);
            return mapTo(ps.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Student read(String username){
        String readStmt = "SELECT * FROM students WHERE student_username = ?;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(readStmt);
            ps.setString(1,username);
            return  mapTo(ps.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer update(Student student) {
        String upStmt = "UPDATE students SET student_firstname = ?,student_lastname = ?,student_username = ?,student_password = ? WHERE student_id = ?;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(upStmt);
            ps.setString(1,student.getFirstname());
            ps.setString(2,student.getLastname());
            ps.setString(3,student.getUsername());
            ps.setString(4,student.getPassword());
            ps.setInt(5,student.getId());
            ps.executeUpdate();
            return student.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delete(Student student) {
        String delStmt = "DELETE FROM students WHERE student_id = ?;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(delStmt);
            ps.setInt(1,student.getId());
            ps.executeUpdate();
            return student.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
