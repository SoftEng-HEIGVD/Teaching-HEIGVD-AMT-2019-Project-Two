package spring.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

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

    public void setRating(double rating) {
        if(rating < 0.0 || rating > 5.0) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        } else {
            this.rating = rating;
        }
    }
}
