package com.wirth.web.scraption.data.entity;

import java.util.Arrays;
import java.util.Optional;

public enum Status {

    VERMELHO("imagens/bola_vermelho_P.png"),
    AMARELO("imagens/bola_amarela_P.png"),
    VERDE("imagens/bola_verde_P.png"),
    INDEFINIDO("");

    String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public static Status fromValue(String descricao) {
        Optional<Status> statusOpt = Arrays.stream(Status.values()).filter(status ->
                status.descricao.equals(descricao)
        ).findFirst();

        if (statusOpt.isPresent()) {
            return statusOpt.get();
        }
        return Status.INDEFINIDO;
    }
}
