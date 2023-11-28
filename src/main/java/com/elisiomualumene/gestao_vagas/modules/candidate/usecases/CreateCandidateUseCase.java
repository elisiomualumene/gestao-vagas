package com.elisiomualumene.gestao_vagas.modules.candidate.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elisiomualumene.gestao_vagas.exceptions.FoundException;
import com.elisiomualumene.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.elisiomualumene.gestao_vagas.modules.candidate.repository.CandidateRepository;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent(user -> {
                    throw new FoundException("Usuário já Existe");
                });
        
        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);

        var candidate = this.candidateRepository.save(candidateEntity);
        return candidate;
    }
}
