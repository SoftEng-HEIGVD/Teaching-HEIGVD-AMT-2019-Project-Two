package ch.heigvd.videogames.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
@Entity
public class VideogameEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String kind;
    private String name;
    private String supportedOn;

    public long getId() {
        return id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String size) {
        this.name = name;
    }

    public String getSupportedOn() {
        return supportedOn;
    }

    public void setSupportedOn(String supportedOn) {
        this.supportedOn = supportedOn;
    }
}
