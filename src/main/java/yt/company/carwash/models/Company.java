package yt.company.carwash.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.lang.reflect.Type;
import java.util.List;

@Entity
@Getter
@Setter
public class Company extends BaseModel {

    private String name;
    private String address;
    private String phone;
    private int totalNumberOfBoxes;
    private int boxesAvailable;
    @OneToMany
    private List<ServiceType> typesOfService;
    @OneToMany
    private List<City> cities;
    @OneToMany
    private List<CarType> carTypes;
    @OneToOne
    private User user;
}