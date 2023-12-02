package com.elisiomualumene.gestao_vagas.modules.company.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elisiomualumene.gestao_vagas.exceptions.FoundException;
import com.elisiomualumene.gestao_vagas.modules.company.entities.JobEntity;
import com.elisiomualumene.gestao_vagas.modules.company.repository.CompanyRepository;
import com.elisiomualumene.gestao_vagas.modules.company.repository.JobRepository;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {
        companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(() -> {
            throw new FoundException("Empresa não encontrada");
        });
        return this.jobRepository.save(jobEntity);
    }
}