package controllers;

import models.users.Professor;
import services.ProfessorService;

import java.sql.Connection;

public class ProfessorController {
    private final ProfessorService professorService;
    private Professor professor;

    public ProfessorController(Connection connection, Professor professor) {
        professorService = new ProfessorService(connection);
        this.professor = professor;
    }
}
