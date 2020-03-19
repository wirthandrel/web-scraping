package com.wirth.web.scraption.data.repository;

import com.wirth.web.scraption.data.entity.HistoricoDisponibilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;

@Repository
@Transactional
public interface HistoricoDisponibilidadeRepository extends JpaRepository<HistoricoDisponibilidade, Integer> {

    @Query("select  h from HistoricoDisponibilidade as h where  h.autorizacao = 0 or  h.retornoAutorizacao = 0 or h.inutilizacao = 0 or" +
            " h.consultaProtocolo = 0 or h.statusServico = 0 or h.consultaCadastro = 0 or h.recepcaoEvento = 0 ")
    Collection<HistoricoDisponibilidade> findMostFails();


    Collection<HistoricoDisponibilidade> findByData(LocalDate date);
}
