package models.users;

import lombok.*;

@Getter
@Setter
public class Clerk extends User{
    private final Double salary = 2000000.0;

    public Clerk(Integer clerkId,String firstname,String lastname,String username,String password){
        super(clerkId,firstname,lastname,username,password);
    }

    @Override
    public String toString() {
        return "ID: " + getId() +
                ", Full Name: " + getFirstname() + " " + getLastname() +
                ", Username: " + getUsername() +
                ", Salary: " + getSalary();
    }
}
