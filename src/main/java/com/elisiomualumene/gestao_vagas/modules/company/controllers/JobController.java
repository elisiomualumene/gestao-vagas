package com.elisiomualumene.gestao_vagas.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elisiomualumene.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.elisiomualumene.gestao_vagas.modules.company.entities.JobEntity;
import com.elisiomualumene.gestao_vagas.modules.company.usecases.CreateJobUseCase;
import com.elisiomualumene.gestao_vagas.modules.company.usecases.ListJobUseCase;
import com.elisiomualumene.gestao_vagas.modules.company.usecases.ListJobsByCompanyIdUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company/job")
@Tag(name = "Vagas", description = "Informações sobre Vagas")
@SecurityRequirement(name = "jwt_auth")
public class JobController {
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @Autowired
    private ListJobUseCase listJobUseCase;

    @Autowired
    private ListJobsByCompanyIdUseCase listJobsByCompanyIdUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    @ApiResponses(@ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = JobEntity.class))
    }))
    @Operation(summary = "Criação de uma nova vaga")
    public ResponseEntity<Object> CreateJob(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");

        try {
            var jobEntity = JobEntity.builder()
                    .benefits(createJobDTO.getBenefits())
                    .companyId(UUID.fromString(companyId.toString()))
                    .description(createJobDTO.getDescription())
                    .level(createJobDTO.getLevel())
                    .build();

            var result = this.createJobUseCase.execute(jobEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Listagem de todas as vagas Cadastradas")
    public ResponseEntity<Object> listJobs() {
        try {
            var jobs = this.listJobUseCase.execute();
            return ResponseEntity.ok().body(jobs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{companyId}")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Listagem das Vagas pelo ID da Empresa")
    public ResponseEntity<Object> ListByCompanyId(@PathVariable UUID companyId) {
        try {
            var jobs = this.listJobsByCompanyIdUseCase.execute(companyId);

            return ResponseEntity.ok().body(jobs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
