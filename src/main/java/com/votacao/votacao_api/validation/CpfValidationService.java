package com.votacao.votacao_api.validation;

import org.springframework.stereotype.Service;

@Service
public class CpfValidationService {

    public boolean isCpfValido(String cpf) {
        return cpf.matches("\\d{11}");
    }

}