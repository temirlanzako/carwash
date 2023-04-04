package yt.company.carwash.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User extends BaseModel {

    private String email;
    private String password;
    private String fullName;
    @OneToMany
    private List<Permission> permissions;
}
