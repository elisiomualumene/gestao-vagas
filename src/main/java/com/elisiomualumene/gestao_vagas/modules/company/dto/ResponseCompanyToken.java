package com.elisiomualumene.gestao_vagas.modules.company.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCompanyToken {
    private String token;
    private Long expires_in;
}
