package com.elisiomualumene.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.elisiomualumene.gestao_vagas.modules.company.entities.CompanyEntity;
import com.elisiomualumene.gestao_vagas.modules.company.usecases.CreateCompanyUseCase;
import com.elisiomualumene.gestao_vagas.modules.company.usecases.ListCompanyUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;
    @Autowired
    private ListCompanyUseCase listCompanyUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> CreateCompany(@Valid @RequestBody CompanyEntity companyEntity) {
        try {
            var company = this.createCompanyUseCase.execute(companyEntity);

            return ResponseEntity.ok().body(company);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> ListCompany() {
        try {
            var companies = this.listCompanyUseCase.execute();

            return ResponseEntity.ok().body(companies);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
