package controllers;

import models.users.Clerk;
import services.ClerkService;

import java.sql.Connection;

public class ClerkController {
    private final ClerkService clerkService;
    private Clerk clerk;

    public ClerkController(Connection connection, Clerk clerk) {
        this.clerkService = new ClerkService(connection);
        this.clerk = clerk;
    }

    public void entry() {
        System.out.println("Welcome to Clerk Section.\nChoose an option.");

    }
}
