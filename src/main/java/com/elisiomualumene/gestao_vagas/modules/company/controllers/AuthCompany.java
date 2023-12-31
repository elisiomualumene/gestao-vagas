package com.elisiomualumene.gestao_vagas.modules.company.controllers;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elisiomualumene.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.elisiomualumene.gestao_vagas.modules.company.usecases.AuthCompanyUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/company")
@Tag(name = "Autenticação da Empresa")
public class AuthCompany {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/auth")
    @Operation(summary = "Autenticação da empresa usando username e senha")
    public ResponseEntity<Object> authCompany(@RequestBody AuthCompanyDTO authCompanyDTO)
            throws AuthenticationException {
        try {
            var company = this.authCompanyUseCase.execute(authCompanyDTO);

            return ResponseEntity.ok().body(company);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
