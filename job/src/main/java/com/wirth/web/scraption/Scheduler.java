package com.wirth.web.scraption;

import com.wirth.web.scraption.data.entity.Disponibilidade;
import com.wirth.web.scraption.data.entity.HistoricoDisponibilidade;
import com.wirth.web.scraption.data.entity.Status;
import com.wirth.web.scraption.data.mapper.HistoricoDisponibilidadeMapper;
import com.wirth.web.scraption.data.service.DataService;
import com.wirth.web.scraption.scraping.ExtractHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class Scheduler {

    @Autowired
    private DataService dataService;

    @Autowired
    private ExtractHtml extractHtml;


    @Scheduled(fixedDelay = 1000*60*5)
    public void verifica() throws IOException {
        for (List<String> coluna : extractHtml.extract()) {

            Disponibilidade disponibilidade = Disponibilidade.builder()
                    .autorizador(coluna.get(0))
                    .autorizacao(Status.fromValue(coluna.get(1)))
                    .retornoAutorizacao(Status.fromValue(coluna.get(2)))
                    .inutilizacao(Status.fromValue(coluna.get(3)))
                    .consultaProtocolo(Status.fromValue(coluna.get(4)))
                    .statusServico(Status.fromValue(coluna.get(5)))
                    .tempoMedio(coluna.get(6))
                    .consultaCadastro(Status.fromValue(coluna.get(7)))
                    .recepcaoEvento(Status.fromValue(coluna.get(8)))
                    .build();

            dataService.save(disponibilidade);
            HistoricoDisponibilidade historico = HistoricoDisponibilidadeMapper.INSTANCE.from(disponibilidade);
            dataService.save(historico);
        }
    }
}
