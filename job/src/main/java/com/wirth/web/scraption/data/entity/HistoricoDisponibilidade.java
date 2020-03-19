package com.wirth.web.scraption.data.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HistoricoDisponibilidade implements Serializable {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Integer cod;
    private String autorizador;
    private Status autorizacao;
    private Status retornoAutorizacao;
    private Status inutilizacao;
    private Status consultaProtocolo;
    private Status statusServico;
    private String tempoMedio;
    private Status consultaCadastro;
    private Status recepcaoEvento;
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDate data;
}
