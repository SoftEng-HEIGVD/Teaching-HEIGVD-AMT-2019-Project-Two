package spring.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

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
