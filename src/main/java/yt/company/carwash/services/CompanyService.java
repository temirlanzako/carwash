package yt.company.carwash.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import yt.company.carwash.models.Company;
import yt.company.carwash.repository.CompanyRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyService {

    private CompanyRepository companyRepository;


    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company get(int id) {
        return companyRepository.getReferenceById(id);
    }

    public Company createCompany(Company companies) {
        return companyRepository.save(companies);
    }

    public Company updateCompany(Company companies) {
        return companyRepository.save(companies);
    }

    public void deleteCompany(int companyId) {
        companyRepository.deleteById(companyId);
    }
}
