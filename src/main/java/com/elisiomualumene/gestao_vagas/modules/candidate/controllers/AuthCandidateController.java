package com.elisiomualumene.gestao_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elisiomualumene.gestao_vagas.modules.candidate.dto.AuthCandidateDTO;
import com.elisiomualumene.gestao_vagas.modules.candidate.usecases.AuthCandidateUseCase;
import com.elisiomualumene.gestao_vagas.utils.CustomResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação do Candidato")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/candidate")
    @Operation(summary = "Autenticação do Candidato usando username e senha")
    public ResponseEntity<Object> AuthCandidate(@RequestBody AuthCandidateDTO data) {
        try {
            var candidate = this.authCandidateUseCase.execute(data);

            CustomResponse<String> response = new CustomResponse<>();

            response.setMessage("Login do candidato efetuado com sucesso");
            response.setToken(candidate);

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
