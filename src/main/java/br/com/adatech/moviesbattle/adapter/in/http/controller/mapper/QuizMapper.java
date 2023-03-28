package br.com.adatech.moviesbattle.adapter.in.http.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.adatech.moviesbattle.adapter.in.http.controller.model.request.QuizModelRequest;
import br.com.adatech.moviesbattle.adapter.in.http.controller.model.response.QuizModelResponse;
import br.com.adatech.moviesbattle.application.domain.QuizDomain;

@Mapper
public interface QuizMapper {

	QuizMapper INSTANCE = Mappers.getMapper(QuizMapper.class);
	
	@Mapping(source = "jogador.id", target = "idJogador")
	QuizModelResponse mapModel(QuizDomain quizDomain);
	
	QuizDomain mapDomain(QuizModelRequest quizModelRequest);
}