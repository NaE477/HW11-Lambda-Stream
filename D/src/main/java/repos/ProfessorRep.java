package repos;

import models.users.ProfPosition;
import models.users.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorRep extends BaseRepository<Professor> implements Repository<Professor> {
    public ProfessorRep(Connection connection) {
        super(connection);
    }

    @Override
    protected Professor mapTo(ResultSet rs) {
        try {
            if(rs.next()){
                return new Professor(
                        rs.getInt("prof_id"),
                        rs.getString("prof_firstname"),
                        rs.getString("prof_lastname"),
                        rs.getString("prof_username"),
                        rs.getString("prof_password"),
                        ProfPosition.valueOf(rs.getString("prof_position"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<Professor> mapToList(ResultSet rs) {
        List<Professor> professors = new ArrayList<>();
        try {
            while (rs.next()){
                professors.add(
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
            return professors;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer ins(Professor professor) {
        String insStmt = "INSERT INTO professors (prof_firstname, prof_lastname, prof_username, prof_password, prof_position) " +
                " VALUES (?,?,?,?,?) RETURNING prof_id;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(insStmt);
            ps.setString(1,professor.getFirstname());
            ps.setString(1,professor.getLastname());
            ps.setString(1,professor.getUsername());
            ps.setString(1,professor.getPassword());
            ps.setString(1,professor.getProfPosition().toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("prof_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Professor read(Integer id) {
        String readStmt = "SELECT * FROM professors WHERE prof_id = ?;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(readStmt);
            ps.setInt(1,id);
            return mapTo(ps.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Professor read(String username){
        String readStmt = "SELECT * FROM professors WHERE prof_username = ?;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(readStmt);
            ps.setString(1,username);
            return  mapTo(ps.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Professor> readAll(){
        String readStmt = "SELECT * FROM professors;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(readStmt);
            return mapToList(ps.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer update(Professor professor) {
        String upStmt = "UPDATE professors SET prof_firstname = ?, prof_lastname = ?, prof_username = ?,prof_password = ?,prof_position = ? WHERE prof_id = ?;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(upStmt);
            ps.setString(1,professor.getFirstname());
            ps.setString(2,professor.getLastname());
            ps.setString(3,professor.getUsername());
            ps.setString(4,professor.getPassword());
            ps.setString(5,professor.getProfPosition().toString());
            ps.executeUpdate();
            return professor.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delete(Professor professor) {
        return null;
    }
}
