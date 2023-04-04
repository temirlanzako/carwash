package yt.company.carwash.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;

@Entity
@Getter
@Setter
public class Client extends BaseModel{

    private String fullName;
    @OneToMany
    private List<CarType> carType;
    @OneToOne
    private User user;
}
