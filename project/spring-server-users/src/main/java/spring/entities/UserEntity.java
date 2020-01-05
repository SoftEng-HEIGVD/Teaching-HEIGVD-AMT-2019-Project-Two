package spring.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserEntity {

    @Id
    @NonNull private String username;
    @NonNull private String password;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isAdmin;
}
