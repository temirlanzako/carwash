package yt.company.carwash.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class OrderBase extends BaseModel {
    @OneToOne
    private Client client;
    @OneToOne
    private Company company;
    @OneToOne
    private VehicleWashType serviceType;
    @OneToOne
    private VehicleType vehicleType;
    @OneToOne
    private City city;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") // 2023-04-10 13:19:09.000000
    private Timestamp timestamp;
    private Boolean isCompleted;
}
