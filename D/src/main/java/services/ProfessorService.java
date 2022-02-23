package services;

import models.users.Professor;
import repos.CourseRep;
import repos.CourseToStudentRep;
import repos.ProfessorRep;

import java.sql.Connection;
import java.util.List;

public class ProfessorService extends BaseService{

    private final ProfessorRep professorRep;
    private final CourseService courseService;
    private final CourseToStudentRep courseToStudentRep;

    public ProfessorService(Connection connection) {
        super(connection);
        professorRep = new ProfessorRep(super.getConnection());
        courseToStudentRep = new CourseToStudentRep(super.getConnection());
        courseService = new CourseService(super.getConnection());
    }
    public Integer signUpProfessor(Professor professor){
        return professorRep.ins(professor);
    }
    public Professor find(Integer professorId){
        return professorRep.read(professorId);
    }
    public Professor find(String username){
        Professor professor = professorRep.read(username);
        if(professor != null) {
            professor.setCourses(courseService.findAllByProfessor(professor));
        }
        return professor;
    }
    public List<Professor> findAll(){
        return professorRep.readAll();
    }
    public Integer editProfile(Professor professor){
        return professorRep.update(professor);
    }
    public Integer deleteProfessor(Professor professor){
        courseToStudentRep.del(professor);
        return professorRep.delete(professor);
    }

}
