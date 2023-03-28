package br.com.adatech.moviesbattle.adapter.in.http.controller.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.adatech.moviesbattle.adapter.in.http.controller.model.response.RankingModelResponse;
import br.com.adatech.moviesbattle.application.domain.RankingDomain;

@Mapper
public interface RankingMapper {
	
	RankingMapper INSTANCE = Mappers.getMapper(RankingMapper.class);
	
	@Mapping(target = "nomeJogador", source = "jogador.nome")
	@Mapping(target = "idJogador", source = "jogador.id")
	RankingModelResponse mapDomain(RankingDomain rankingDomain);
	List<RankingModelResponse> mapDomain(List<RankingDomain> rankingDomain);
}