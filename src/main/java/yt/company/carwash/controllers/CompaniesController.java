package yt.company.carwash.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yt.company.carwash.models.Company;
import yt.company.carwash.services.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompaniesController {

    private final CompanyService companyService;
    @GetMapping
    public ResponseEntity<Object> getAllCompanies() {
        List<Company> companiesList = companyService.getAllCompanies();
        return ResponseEntity.ok(companiesList);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCompany(@RequestBody Company companies) {
        Company companiesFromDb = companyService.createCompany(companies);
        return ResponseEntity.ok(companiesFromDb);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCompany(@RequestBody Company company) {
        try {
            Company companiesFromDb = companyService.get(company.getId());
            companyService.createCompany(company);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}
