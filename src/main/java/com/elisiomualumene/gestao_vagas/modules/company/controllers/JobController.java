package com.elisiomualumene.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elisiomualumene.gestao_vagas.modules.company.entities.JobEntity;
import com.elisiomualumene.gestao_vagas.modules.company.usecases.CreateJobUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> CreateJob(@Valid @RequestBody JobEntity jobEntity) {
        var job = this.createJobUseCase.execute(jobEntity);
        return ResponseEntity.ok().body(job);
    }
}
