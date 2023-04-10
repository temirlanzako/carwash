package yt.company.carwash.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Getter
@Setter
public class WebClient extends BaseModel{

    private String name;
    private String surname;
    private String phone;
    @OneToOne
    private City city;
    @OneToOne
    @Cascade(CascadeType.ALL)
    private User user;
}
