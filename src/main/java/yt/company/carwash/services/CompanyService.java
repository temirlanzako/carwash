package yt.company.carwash.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yt.company.carwash.models.*;
import yt.company.carwash.repository.CompanyRepository;
import yt.company.carwash.repository.RatingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserService userService;



    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompany(Long id) {
        return this.companyRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Company not found"));
    }

    public Company createCompany(Company company, Long userId) {
        Company checkPhone = companyRepository.findByPhone(company.getPhone());
        if(checkPhone == null) {
            Company checkAddress = companyRepository.findByAddress(company.getAddress());
            if(checkAddress == null) {
                Company comp = new Company();
                comp.setName(company.getName());
                comp.setAddress(company.getAddress());
                comp.setPhone(company.getPhone());
                comp.setCapacity(company.getCapacity());
                comp.setVehicleTypes(company.getVehicleTypes());
                comp.setServiceTypes(company.getServiceTypes());
                comp.setCities(company.getCities());
                comp.setUser(userService.getUser(userId));
                companyRepository.save(comp);
                return comp;
            } else {
                throw new IllegalArgumentException("Company with such ADDRESS is already exists" + checkAddress.getAddress());
            }
        } else {
            throw new IllegalArgumentException("Company with such PHONE NUMBER is already exists" + checkPhone.getPhone());
        }
    }
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }


}
