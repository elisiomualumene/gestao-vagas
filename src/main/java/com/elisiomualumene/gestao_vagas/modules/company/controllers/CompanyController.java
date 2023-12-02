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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
@Tag(name = "Empresa", description = "Informações da Empresa")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;
    @Autowired
    private ListCompanyUseCase listCompanyUseCase;

    @PostMapping("/")
    @Operation(summary = "Criar uma nova Empresa")
    public ResponseEntity<Object> CreateCompany(@Valid @RequestBody CompanyEntity companyEntity) {
        try {
            var company = this.createCompanyUseCase.execute(companyEntity);

            return ResponseEntity.ok().body(company);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @Operation(summary = "Listagem de todas empresas cadastradas")
    public ResponseEntity<Object> ListCompany() {
        try {
            var companies = this.listCompanyUseCase.execute();

            return ResponseEntity.ok().body(companies);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
