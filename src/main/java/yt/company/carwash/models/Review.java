package yt.company.carwash.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Getter
@Setter
public class Review extends BaseModel {

    @OneToOne
    private Client client;
    @OneToOne
    private Company company;
    @Column(columnDefinition="TEXT")
    private String review;
    private Date date;
}
