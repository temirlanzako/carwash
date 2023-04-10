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
public class Company extends BaseModel {

    private String name;
    private String address;
    private String phone;
    private int capacity;
    @ManyToMany
    private List<VehicleType> vehicleTypes;
    @OneToMany
    //@JoinColumn(name = "company_id")
    private List<VehicleWashType> serviceTypes;
    @OneToMany
    private List<City> cities;
    @OneToOne
    @Cascade(CascadeType.ALL)
    private User user;
}
