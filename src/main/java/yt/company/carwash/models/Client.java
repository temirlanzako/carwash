package yt.company.carwash.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Entity
@Getter
@Setter
public class Client extends BaseModel{

    private String name;
    private String surname;
    private String phone;
    @OneToOne
    private City city;
    @OneToOne
    @Cascade(CascadeType.ALL)
    private User user;

}
