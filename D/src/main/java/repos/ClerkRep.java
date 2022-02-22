package repos;

import models.users.Clerk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClerkRep extends BaseRepository<Clerk> implements Repository<Clerk> {
    public ClerkRep(Connection connection) {
        super(connection);
    }

    @Override
    protected Clerk mapTo(ResultSet rs) {
        try {
            if(rs.next()) {
                return new Clerk(
                        rs.getInt("clerk_id"),
                        rs.getString("clerk_firstname"),
                        rs.getString("clerk_lastname"),
                        rs.getString("clerk_username"),
                        rs.getString("clerk_password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    protected List<Clerk> mapToList(ResultSet rs) {
        List<Clerk> clerks = new ArrayList<>();
        try {
            while (rs.next()) {
                clerks.add(
                        new Clerk(
                                rs.getInt("clerk_id"),
                                rs.getString("clerk_firstname"),
                                rs.getString("clerk_lastname"),
                                rs.getString("clerk_username"),
                                rs.getString("clerk_password")
                        )
                );
            }
            return clerks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer ins(Clerk clerk) {
        String insStmt = "INSERT INTO clerks " +
                "(clerk_firstname, clerk_lastname, clerk_username, clerk_password, clerk_salary) " +
                " VALUES (?,?,?,?,2000000)" +
                " RETURNING clerk_id;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(insStmt);
            ps.setString(1,clerk.getFirstname());
            ps.setString(2,clerk.getLastname());
            ps.setString(3,clerk.getUsername());
            ps.setString(4,clerk.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return rs.getInt("clerk_id");
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }

    @Override
    public Clerk read(Integer id) {
        String readStmt = "SELECT * FROM clerks WHERE clerk_id = ?;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(readStmt);
            ps.setInt(1,id);
            return mapTo(ps.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Clerk read(String username){
        String readStmt = "SELECT * FROM clerks WHERE clerk_username = ?;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(readStmt);
            ps.setString(1,username);
            return  mapTo(ps.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Clerk> readAll(){
        String readStmt = "SELECT * FROM clerks;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(readStmt);
            return mapToList(ps.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer update(Clerk clerk) {
        String updateStmt = "UPDATE clerks SET clerk_firstname = ?,clerk_lastname = ?,clerk_username = ?,clerk_password = ? WHERE clerk_id = ?;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(updateStmt);
            ps.setString(1,clerk.getFirstname());
            ps.setString(2,clerk.getLastname());
            ps.setString(3,clerk.getUsername());
            ps.setString(4,clerk.getPassword());
            ps.setInt(5,clerk.getId());
            ps.executeUpdate();
            return clerk.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delete(Clerk clerk) {
        String delStmt = "DELETE FROM clerks WHERE clerk_id = ?;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(delStmt);
            ps.setInt(1,clerk.getId());
            ps.executeUpdate();
            return clerk.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
