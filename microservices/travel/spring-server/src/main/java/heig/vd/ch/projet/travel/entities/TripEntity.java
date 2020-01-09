package heig.vd.ch.projet.travel.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Trip")
public class TripEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTrip")
    private Integer idTrip;

    @Column(name = "visited", columnDefinition = "TINYINT(1)")
    private boolean visited;

    @Column(name = "date")
    private Date date;

    @Column(name = "User_email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "Reason_idReason")
    private ReasonEntity reasonEntity;

    @ManyToOne
    @JoinColumn(name = "Country_idCountry")
    private  CountryEntity countryEntity;
}
