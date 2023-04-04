package yt.company.carwash.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ServiceType extends BaseModel{

    private String name;
}
