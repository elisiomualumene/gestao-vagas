package com.elisiomualumene.gestao_vagas.utils;

import lombok.Data;

@Data
public class CustomResponse<T> {
    private String message;
    private T token;
}