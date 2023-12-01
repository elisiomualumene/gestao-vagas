package com.elisiomualumene.gestao_vagas.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elisiomualumene.gestao_vagas.modules.company.entities.JobEntity;
import com.elisiomualumene.gestao_vagas.modules.company.usecases.CreateJobUseCase;
import com.elisiomualumene.gestao_vagas.modules.company.usecases.ListJobUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company/job")
public class JobController {
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @Autowired
    private ListJobUseCase listJobUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> CreateJob(@Valid @RequestBody JobEntity jobEntity, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");
        try {
            jobEntity.setCompanyId(UUID.fromString(companyId.toString()));
            var job = this.createJobUseCase.execute(jobEntity);
            return ResponseEntity.ok().body(job);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> listJobs() {
        try {
            var jobs = this.listJobUseCase.execute();
            return ResponseEntity.ok().body(jobs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
