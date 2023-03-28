package br.com.adatech.moviesbattle.adapter.out.database.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.adatech.moviesbattle.adapter.out.database.entity.RodadaEntity;
import br.com.adatech.moviesbattle.application.domain.RodadaDomain;

@Mapper(componentModel = "spring")
public interface RodadaMapperDataProvider {
	
	RodadaMapperDataProvider INSTANCE = Mappers.getMapper(RodadaMapperDataProvider.class);
	
	RodadaEntity mapEntity(RodadaDomain filmeDomain);
	List<RodadaEntity> mapEntity(List<RodadaDomain> filmesDomain);
	
	RodadaDomain mapDomain(RodadaEntity rodadaEntity);
	List<RodadaDomain> mapDomain(List<RodadaEntity> rodadaEntity);
}