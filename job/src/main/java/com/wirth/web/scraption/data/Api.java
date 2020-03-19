package com.wirth.web.scraption.data;

import com.wirth.web.scraption.data.entity.Disponibilidade;
import com.wirth.web.scraption.data.entity.Estado;
import com.wirth.web.scraption.data.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class Api {

    @Autowired
    private DataService service;

    @GetMapping(value = "/disponibilidade/atual", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disponibilidade>> GetById() {
        List<Disponibilidade> disponibilidade = service.disponibilidadeAtual();
        if (disponibilidade.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(disponibilidade, HttpStatus.OK);
    }

    @GetMapping(value = "/disponibilidade/{estado}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Disponibilidade> GetById(@PathVariable(value = "estado") String estado) {
        Disponibilidade disponibilidade = service.findBy(estado);
        if (disponibilidade != null)
            return new ResponseEntity<>(disponibilidade, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/disponibilidade/por/{data}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disponibilidade>> getByDate(@PathVariable(value = "data") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data) {
        List<Disponibilidade> disponibilidade = service.findBy(data);
        if (disponibilidade.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(disponibilidade, HttpStatus.OK);
    }

    @GetMapping(value = "/disponibilidade/indisponibilidade", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Estado> getIndisponibilidade() {
        Estado estado = service.findEstadoComMaisFalhas();
        if (estado != null) {
            return new ResponseEntity<Estado>(estado, HttpStatus.OK);
        }
        return new ResponseEntity<Estado>(HttpStatus.NO_CONTENT);
    }
}
