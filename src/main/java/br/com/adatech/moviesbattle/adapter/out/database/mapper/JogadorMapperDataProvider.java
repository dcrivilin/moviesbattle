package br.com.adatech.moviesbattle.adapter.out.database.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.adatech.moviesbattle.adapter.out.database.entity.JogadorEntity;
import br.com.adatech.moviesbattle.application.domain.JogadorDomain;

@Mapper(componentModel = "spring")
public interface JogadorMapperDataProvider {
	
	JogadorMapperDataProvider INSTANCE = Mappers.getMapper(JogadorMapperDataProvider.class);
	
	JogadorDomain mapDomain(JogadorEntity jogadorEntity);
}