package br.com.adatech.moviesbattle.adapter.out.database.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.adatech.moviesbattle.adapter.out.database.entity.RankingEntity;
import br.com.adatech.moviesbattle.application.domain.RankingDomain;

@Mapper
public interface RankingMapperDataProvider {
	
	RankingMapperDataProvider INSTANCE = Mappers.getMapper(RankingMapperDataProvider.class);
	
	@Mapping(target = "jogador.id", source = "idJogador")
	RankingDomain mapDomain(RankingEntity rankingEntity);
	List<RankingDomain> mapDomain(List<RankingEntity> rankingEntity);
}