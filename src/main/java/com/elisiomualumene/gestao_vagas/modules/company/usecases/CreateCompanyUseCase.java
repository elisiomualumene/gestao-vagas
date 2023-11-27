package com.elisiomualumene.gestao_vagas.modules.company.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elisiomualumene.gestao_vagas.exceptions.FoundException;
import com.elisiomualumene.gestao_vagas.modules.company.entities.CompanyEntity;
import com.elisiomualumene.gestao_vagas.modules.company.repository.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent(company -> {
                    throw new FoundException("A Empresa já existe");
                });

        var company = this.companyRepository.save(companyEntity);

        return company;
    }
}
