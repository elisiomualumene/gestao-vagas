package com.elisiomualumene.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "company")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O Username não pode ser vázio")
    @Pattern(regexp = "^(?!\\s*$).+", message = "O Campo username não pode conter espaço")
    private String username;

    @NotBlank()
    @Length(min = 10, max = 100)
    private String password;

    @NotBlank(message = "O Email não pode estar vázio")
    @Email(message = "Informe um email válido")
    private String email;

    private String website;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
