package yt.company.carwash.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yt.company.carwash.models.Companies;
import yt.company.carwash.services.CompaniesService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    private final CompaniesService companiesService;

    public CompaniesController(CompaniesService companiesService) {
        this.companiesService = companiesService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllCompanies() {
        List<Companies> companiesList = this.companiesService.getAllCompanies();
        return ResponseEntity.ok(companiesList);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCompany(@RequestBody Companies companies) {
        Companies companiesFromDb = this.companiesService.createCompany(companies);
        return ResponseEntity.ok(companiesFromDb);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCompany(@RequestBody Companies companies) {
        try {
            Companies companiesFromDb = this.companiesService.get(companies.getId());
            this.companiesService.createCompany(companies);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCompany(@PathVariable int id) {
        this.companiesService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}
