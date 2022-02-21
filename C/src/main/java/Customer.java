import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Customer {
    private Integer id;
    private String firstName,lastName,username,password,emailAddress;
    private String address;
    private Double balance;

    public Customer(Integer id,String firstName,String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Customer ID: " + getId() +
                "\nFull Name: " + getFirstName() + " " + getLastName() +
                "\nEmail: " + getEmailAddress() + " " +
                "\nAddress: " + getAddress() +
                "\nBalance: " + getBalance().toString();
    }
}
