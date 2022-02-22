package repos;

import lombok.Getter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

@Getter
public abstract class BaseRepository<T> {
    private final Connection connection;

    public BaseRepository(Connection connection){
        this.connection = connection;
    }

    protected abstract T mapTo(ResultSet rs);

    protected abstract List<T> mapToList(ResultSet rs);
}
