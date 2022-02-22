package services;

import models.users.Clerk;
import repos.ClerkRep;

import java.sql.Connection;
import java.util.List;

public class ClerkService extends BaseService {
    private final ClerkRep clerkRep;

    public ClerkService(Connection connection) {
        super(connection);
        clerkRep = new ClerkRep(super.getConnection());
    }

    public Integer signUpClerk(Clerk clerk) {
        return clerkRep.ins(clerk);
    }

    public Clerk find(Integer clerkId) {
        return clerkRep.read(clerkId);
    }

    public Clerk find(String username) {
        return clerkRep.read(username);
    }

    public List<Clerk> findAll() {
        return clerkRep.readAll();
    }

    public Integer editProfile(Clerk clerk) {
        return clerkRep.update(clerk);
    }

    public Integer deleteClerk(Clerk clerk){
        return clerkRep.delete(clerk);
    }
}
