package ch.heigvd.boozify.users.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
@Entity
public class UserEntity implements Serializable {

    @Id
    private String email;


    private Integer idUser;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean role;

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Integer getIdUser(){
        return idUser;
    }

    public void setIdUser(Integer idUser){
        this.idUser = idUser;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Boolean getRole(){
        return role;
    }

    public void setRole(Boolean role){
        this.role = role;
    }

}
