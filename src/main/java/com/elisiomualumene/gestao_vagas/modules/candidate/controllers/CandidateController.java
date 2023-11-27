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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/")
    public ResponseEntity<Object> candidate(@Valid @RequestBody CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent(user -> {
                    throw new UserFoundException("Usuário já Existe");
                });
        var candidate = this.candidateRepository.save(candidateEntity);
        return ResponseEntity.status(200).body(candidate);

    }
}