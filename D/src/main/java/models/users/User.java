package models.users;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public abstract class User {
    Integer id;
    String firstname,lastname,username,password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
