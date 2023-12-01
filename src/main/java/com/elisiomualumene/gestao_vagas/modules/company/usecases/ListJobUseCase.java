package com.elisiomualumene.gestao_vagas.modules.company.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elisiomualumene.gestao_vagas.modules.company.entities.JobEntity;
import com.elisiomualumene.gestao_vagas.modules.company.repository.JobRepository;

@Service
public class ListJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute() {
        var jobs = this.jobRepository.findAll();

        return jobs;
    }
}
