package br.com.adatech.moviesbattle.adapter.out.database.mapper;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.adatech.moviesbattle.adapter.out.database.entity.FilmeEntity;
import br.com.adatech.moviesbattle.application.domain.FilmeDomain;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FilmeMapperDataProvider {
	
	FilmeMapperDataProvider INSTANCE = Mappers.getMapper(FilmeMapperDataProvider.class);
	
	FilmeEntity mapEntity(FilmeDomain filmeDomain);
	List<FilmeEntity> mapEntity(List<FilmeDomain> filmesDomain);
	
	FilmeDomain mapDomain(FilmeEntity filmeEntity);
	List<FilmeDomain> mapDomain(List<FilmeEntity> filmesEntity);
}