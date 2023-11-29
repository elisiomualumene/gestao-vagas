package com.elisiomualumene.gestao_vagas.modules.candidate.usecases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.elisiomualumene.gestao_vagas.modules.candidate.dto.AuthCandidateDTO;
import com.elisiomualumene.gestao_vagas.modules.candidate.repository.CandidateRepository;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret2}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String execute(AuthCandidateDTO authCandidateDTO) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsername(authCandidateDTO.username()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Usuário ou Senha Incorretos");
        });

        var passwordMatcher = passwordEncoder.matches(authCandidateDTO.password(), candidate.getPassword());

        if (!passwordMatcher) {
            throw new AuthenticationException("Usuário ou Senha incorreta!");
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("java2023").withClaim("roles", Arrays.asList("CANDIDATE"))
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(candidate.getId().toString()).sign(algorithm);

        return token;

    }
}
