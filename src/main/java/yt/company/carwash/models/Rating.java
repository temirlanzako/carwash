package yt.company.carwash.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Rating extends BaseModel {

    private float grade;
    @OneToOne
    private Company company;
}
