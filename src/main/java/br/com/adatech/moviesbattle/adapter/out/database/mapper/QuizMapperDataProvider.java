package br.com.adatech.moviesbattle.adapter.out.database.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.adatech.moviesbattle.adapter.out.database.entity.QuizEntity;
import br.com.adatech.moviesbattle.application.domain.QuizDomain;

@Mapper(componentModel = "spring")
public interface QuizMapperDataProvider {
	
	QuizMapperDataProvider INSTANCE = Mappers.getMapper(QuizMapperDataProvider.class);
	
	QuizDomain mapDomain(QuizEntity quizEntity);
	QuizEntity mapEntity(QuizDomain quizEntity);
}