package yt.company.carwash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import yt.company.carwash.models.Company;
import yt.company.carwash.services.CompanyService;

import java.security.cert.CertPath;

@SpringBootApplication
public class CarwashApplication {
	public static void main(String[] args) {

		SpringApplication.run(CarwashApplication.class, args);
	}

}
