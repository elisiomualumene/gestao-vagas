package com.elisiomualumene.gestao_vagas.modules.company.usecases;

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
import com.elisiomualumene.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.elisiomualumene.gestao_vagas.modules.company.dto.ResponseCompanyToken;
import com.elisiomualumene.gestao_vagas.modules.company.repository.CompanyRepository;

@Service
public class AuthCompanyUseCase {
    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseCompanyToken execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Usuário ou Senha Incorretos");
        });

        var passwordMatcher = passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passwordMatcher) {
            throw new AuthenticationException("Senha incorreta!");
        }

        var expires_In = Instant.now().plus(Duration.ofHours(2));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        
        var token = JWT.create().withIssuer("java").withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withClaim("roles", Arrays.asList("COMPANY")).withExpiresAt(expires_In)
                .withSubject(company.getId().toString()).sign(algorithm);

        var responseCompanyToken = ResponseCompanyToken.builder().token(token).expires_in(expires_In.toEpochMilli())
                .build();

        return responseCompanyToken;
    }
}
