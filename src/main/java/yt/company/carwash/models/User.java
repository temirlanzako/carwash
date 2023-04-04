package yt.company.carwash.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Entity
@Getter
@Setter
public class User extends BaseModel {

    private String email;
    private String password;
    @ManyToMany
    private List<Permission> permissions;
}
