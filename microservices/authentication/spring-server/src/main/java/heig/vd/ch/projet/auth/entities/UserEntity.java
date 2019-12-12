package heig.vd.ch.projet.auth.entities;

import heig.vd.ch.projet.auth.api.model.Roles;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by François Burgener
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity implements Serializable {
    @Id
    private String email;
    private String lastname;
    private String firstname;
    private String password;
    private Roles role;
}
