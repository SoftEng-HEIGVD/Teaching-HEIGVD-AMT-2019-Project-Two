package spring.entities;

import lombok.*;

import javax.persistence.*;

@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Entity
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private ActorEntity actorEntity;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movieEntity;

    /*@EmbeddedId
    RoleKey id;*/

    /*@ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    MovieEntity movieEntity;

    @ManyToOne
    @MapsId("actorId")
    @JoinColumn(name = "actor_id")
    ActorEntity actorEntity;*/

    String roleName;
    int awards;
    boolean awarded;
}
