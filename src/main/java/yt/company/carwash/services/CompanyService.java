package yt.company.carwash.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yt.company.carwash.models.Company;
import yt.company.carwash.repository.CompanyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompany(Long id) {

        return this.companyRepository.findById(id).orElseThrow();
    }

    public Company createCompany(Company companies) {

        return companyRepository.save(companies);
    }

    public Company updateCompany(Company companies) {

        return companyRepository.save(companies);
    }

    public void deleteCompany(Long id) {

        companyRepository.deleteById(id);
    }
}
