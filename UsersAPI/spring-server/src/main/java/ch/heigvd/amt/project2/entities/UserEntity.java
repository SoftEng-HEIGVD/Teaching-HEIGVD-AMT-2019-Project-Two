package ch.heigvd.amt.project2.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String firstname;
    @Getter @Setter
    private String lastname;
    @Getter @Setter
    @Column(nullable=false)
    private String username;
    @Column(nullable=false)
    @Getter @Setter
    private String password;
    @Column(unique=true, nullable=false)
    @Getter @Setter
    private String email;
    @Column(nullable=false)
    @Getter @Setter
    private String role;
}
