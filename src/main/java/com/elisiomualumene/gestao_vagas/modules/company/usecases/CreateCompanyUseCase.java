package com.elisiomualumene.gestao_vagas.modules.company.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elisiomualumene.gestao_vagas.exceptions.FoundException;
import com.elisiomualumene.gestao_vagas.modules.company.entities.CompanyEntity;
import com.elisiomualumene.gestao_vagas.modules.company.repository.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent(company -> {
                    throw new FoundException("A Empresa já existe");
                });

        var password = passwordEncoder.encode(companyEntity.getPassword());

        companyEntity.setPassword(password);

        var company = this.companyRepository.save(companyEntity);

        return company;
    }
}
