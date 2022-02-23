package services;

import repos.TermRepository;

import java.sql.Connection;

public class TermService {
    private final TermRepository termRepository;
    public TermService(Connection connection){
        termRepository = new TermRepository(connection);
    }
    public void endTerm(){
        termRepository.update();
    }
    public Integer getCurrentTerm(){
        return termRepository.read();
    }
}
