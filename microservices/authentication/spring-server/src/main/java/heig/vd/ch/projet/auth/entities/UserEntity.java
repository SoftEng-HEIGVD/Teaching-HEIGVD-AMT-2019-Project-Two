package heig.vd.ch.projet.auth.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by François Burgener
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "User")
public class UserEntity implements Serializable {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "firstname")
    private String firstname;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "role")
    private String role;
}
