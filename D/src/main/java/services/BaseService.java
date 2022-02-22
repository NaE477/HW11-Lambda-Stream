package services;

import lombok.Getter;

import java.sql.Connection;

@Getter
public abstract class BaseService {
    private final Connection connection;

    public BaseService(Connection connection) {
        this.connection = connection;
    }
}
