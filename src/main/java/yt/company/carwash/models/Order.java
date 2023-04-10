package yt.company.carwash.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Order extends BaseModel {
    @OneToOne
    private WebClient client;
    @OneToOne
    private Company company;
    @OneToOne
    private VehicleWashType serviceType;
    @OneToOne
    private VehicleType vehicleType;
    @OneToOne
    private City city;
    private Date date;
    private Boolean isCompleted;
}
