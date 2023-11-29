package com.elisiomualumene.gestao_vagas.utils;

import lombok.Data;

@Data
public class CustomDataResponse<T> {
    private String message;
    private T data;
}
