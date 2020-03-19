package com.wirth.web.scraption.data.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Disponibilidade implements Serializable {

    @Id
    private String autorizador;
    private Status autorizacao;
    private Status retornoAutorizacao;
    private Status inutilizacao;
    private Status consultaProtocolo;
    private Status statusServico;
    private String tempoMedio;
    private Status consultaCadastro;
    private Status recepcaoEvento;


}
