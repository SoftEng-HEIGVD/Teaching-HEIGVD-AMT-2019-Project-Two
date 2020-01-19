package spring.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
public class UserEntity implements Serializable {

    @Id
    @NonNull private String username;
    @NonNull private String password;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isAdmin;
    private boolean isBlocked;
}
