package spring.entities;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class ActorEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Disable setters for id
    @Setter(AccessLevel.NONE)
    private long id;

    private String firstname;
    private String lastname;
    private ExpertiseEnum expertise;
    private String ownerId;

    @OneToMany(mappedBy = "actorEntity")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<RoleEntity> roles;

    /*@OneToMany(mappedBy = "actorEntity")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Set<RoleEntity> roles;*/

    /*@ManyToMany(mappedBy = "movieRoles")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<MovieEntity> actorRoles;*/

    /**
     * TV, film or theater actor
     */
    public enum ExpertiseEnum {
        THEATER("theater"),
        TELEVISION("television"),
        FILM("film");

        private String value;

        ExpertiseEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}
