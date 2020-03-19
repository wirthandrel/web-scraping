package com.wirth.web.scraption.data.mapper;

import com.wirth.web.scraption.data.entity.Disponibilidade;
import com.wirth.web.scraption.data.entity.HistoricoDisponibilidade;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DisponibilidadeMapper {

    DisponibilidadeMapper INSTANCE = Mappers.getMapper(DisponibilidadeMapper.class);

    Disponibilidade from(HistoricoDisponibilidade historicoDisponibilidade);
}
