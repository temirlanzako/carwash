package yt.company.carwash.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yt.company.carwash.models.Companies;
import yt.company.carwash.repository.CompaniesRepository;

import java.util.List;

@Service
public class CompaniesService {

    private final CompaniesRepository companiesRepository;

    public CompaniesService(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    public List<Companies> getAllCompanies() {
        return this.companiesRepository.findAll();
    }

    public Companies get(int id) {
        return this.companiesRepository.getReferenceById(id);
    }

    public Companies createCompany(Companies companies) {
        return this.companiesRepository.save(companies);
    }

    public Companies updateCompany(Companies companies) {
        return this.companiesRepository.save(companies);
    }

    public void deleteCompany(int companyId) {
        this.companiesRepository.deleteById(companyId);
    }
}
