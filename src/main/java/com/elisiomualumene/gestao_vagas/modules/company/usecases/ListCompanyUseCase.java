package com.elisiomualumene.gestao_vagas.modules.company.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elisiomualumene.gestao_vagas.modules.company.entities.CompanyEntity;
import com.elisiomualumene.gestao_vagas.modules.company.repository.CompanyRepository;

@Service
public class ListCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    public List<CompanyEntity> execute() {
        var companies = this.companyRepository.findAll();

        return companies;
    }
}
