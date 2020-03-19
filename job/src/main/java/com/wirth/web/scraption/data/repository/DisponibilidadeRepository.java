package com.wirth.web.scraption.data.repository;

import com.wirth.web.scraption.data.entity.Disponibilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Integer> {

    Disponibilidade findByAutorizador(String estado);

}
