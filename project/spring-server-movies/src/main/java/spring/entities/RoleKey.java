package spring.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Class for representing the composite key for a role consisting of the id of the movie,
 * and the id of the actor.
 */
/*@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
@Data*/
public class RoleKey implements Serializable {

    /*@Column(name = "actor_id")
    Long actorId;

    @Column(name = "movie_id")
    Long movieId;*/
}
