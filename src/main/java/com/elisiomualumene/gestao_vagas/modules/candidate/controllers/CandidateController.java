package com.elisiomualumene.gestao_vagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elisiomualumene.gestao_vagas.exceptions.UserFoundException;
import com.elisiomualumene.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.elisiomualumene.gestao_vagas.modules.candidate.repository.CandidateRepository;
import com.elisiomualumene.gestao_vagas.modules.candidate.usecases.CreateCandidateUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    CreateCandidateUseCase createCandidateUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> candidate(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var candidate = this.createCandidateUseCase.execute(candidateEntity);

            return ResponseEntity.status(200).body(candidate);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}