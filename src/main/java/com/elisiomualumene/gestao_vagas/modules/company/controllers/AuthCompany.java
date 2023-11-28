package com.elisiomualumene.gestao_vagas.modules.company.controllers;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elisiomualumene.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.elisiomualumene.gestao_vagas.modules.company.usecases.AuthCompanyUseCase;

@RestController
@RequestMapping("/auth")
public class AuthCompany {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/company")
    public ResponseEntity<Object> authCompany(@RequestBody AuthCompanyDTO authCompanyDTO)
            throws AuthenticationException {
        var company = this.authCompanyUseCase.execute(authCompanyDTO);

        return ResponseEntity.ok().body(company);
    }
}
