package com.wirth.web.scraption.data.service;

import com.wirth.web.scraption.data.entity.Disponibilidade;
import com.wirth.web.scraption.data.entity.Estado;
import com.wirth.web.scraption.data.entity.HistoricoDisponibilidade;
import com.wirth.web.scraption.data.mapper.DisponibilidadeMapper;
import com.wirth.web.scraption.data.repository.DisponibilidadeRepository;
import com.wirth.web.scraption.data.repository.HistoricoDisponibilidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataService {

    @Autowired
    private DisponibilidadeRepository disponibilidadeRepository;

    @Autowired
    private HistoricoDisponibilidadeRepository historicoDisponibilidadeRepository;

    public void save(Disponibilidade disponibilidade) {
        disponibilidadeRepository.save(disponibilidade);
    }

    public void save(HistoricoDisponibilidade historicoDisponibilidade) {

        historicoDisponibilidadeRepository.save(historicoDisponibilidade);
    }

    public List<Disponibilidade> disponibilidadeAtual() {
        return disponibilidadeRepository.findAll();
    }

    public Disponibilidade findBy(String estado) {
        return disponibilidadeRepository.findByAutorizador(estado);
    }

    public List<Disponibilidade> findBy(LocalDate date) {

        Collection<HistoricoDisponibilidade> historicos = historicoDisponibilidadeRepository.findByData(date);

        return historicos.stream().map(h -> DisponibilidadeMapper.INSTANCE.from(h)).collect(Collectors.toList());
    }

    public Estado findEstadoComMaisFalhas() {
        Map<String, Long> map = historicoDisponibilidadeRepository.
                findMostFails()
                .stream()
                .collect(Collectors.groupingBy(h -> h.getAutorizador(), Collectors.counting()));

        Long maior = 0L;
        String estado = null;
        for (Map.Entry<String, Long> entry : map.entrySet()) {

            if (maior < entry.getValue()) {
                maior = entry.getValue();
                estado = entry.getKey();
            }

        }
        return new Estado(estado);
    }
}
