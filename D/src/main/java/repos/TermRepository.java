package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TermRepository {
    private final Connection connection;
    public TermRepository(Connection connection){
        this.connection = connection;
    }

    public void update(){
        String updateStmt = "UPDATE term SET term = term +1;";
        try {
            PreparedStatement ps = connection.prepareStatement(updateStmt);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer read(){
        String readStmt = "SELECT term FROM term;";
        try {
            PreparedStatement ps = connection.prepareStatement(readStmt);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return rs.getInt("term");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
