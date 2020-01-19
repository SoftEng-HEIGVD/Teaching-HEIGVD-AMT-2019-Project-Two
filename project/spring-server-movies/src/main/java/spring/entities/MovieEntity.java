package spring.entities;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Entity
public class MovieEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Disable setters for id
    @Setter(AccessLevel.NONE) private long id;

    @NonNull private String title;
    private String director;
    private String studio;
    private Double production;
    private Double revenue;
    @Setter(AccessLevel.NONE) private Double rating;
    private String ownerId; // Here we use the username of the user

    @OneToMany(mappedBy = "movieEntity")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Set<RoleEntity> roles;

    public void setRating(double rating) {
        if(rating < 0.0 || rating > 5.0) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        } else {
            this.rating = rating;
        }
    }
}
