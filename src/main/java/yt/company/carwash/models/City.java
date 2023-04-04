package yt.company.carwash.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class City extends BaseModel{

    private String name;
    private String region;
    private String code;
}
