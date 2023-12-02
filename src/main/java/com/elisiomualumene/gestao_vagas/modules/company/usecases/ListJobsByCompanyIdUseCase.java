package com.elisiomualumene.gestao_vagas.modules.company.usecases;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elisiomualumene.gestao_vagas.modules.company.entities.JobEntity;
import com.elisiomualumene.gestao_vagas.modules.company.repository.JobRepository;

@Service
public class ListJobsByCompanyIdUseCase {

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(UUID companyId) {
        var jobs = this.jobRepository.findAllByCompanyId(companyId);

        return jobs;
    }
}
