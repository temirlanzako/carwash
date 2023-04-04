package yt.company.carwash.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Permission extends BaseModel{

    private String name;
}
