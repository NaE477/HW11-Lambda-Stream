package services;

import models.users.Professor;
import repos.ProfessorRep;

import java.sql.Connection;

public class ProfessorService extends BaseService{

    private final ProfessorRep professorRep;

    public ProfessorService(Connection connection) {
        super(connection);
        professorRep = new ProfessorRep(super.getConnection());
    }
    public Integer signUpProfessor(Professor professor){
        return professorRep.ins(professor);
    }
    public Professor find(Integer professorId){
        return professorRep.read(professorId);
    }
    public Professor find(String username){
        return professorRep.read(username);
    }
    public Integer editProfile(Professor professor){
        return professorRep.update(professor);
    }

}
