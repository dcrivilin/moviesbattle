package br.com.adatech.moviesbattle.adapter.in.http.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.adatech.moviesbattle.adapter.in.http.controller.model.request.RodadaModelRequest;
import br.com.adatech.moviesbattle.adapter.in.http.controller.model.response.RodadaModelResponse;
import br.com.adatech.moviesbattle.application.domain.RodadaDomain;

@Mapper
public interface RodadaMapper {
	
	RodadaMapper INSTANCE = Mappers.getMapper(RodadaMapper.class);
	
	@Mapping(target = "idQuiz", source = "quiz.id")
	RodadaModelResponse mapModel(RodadaDomain rodadaDomain);
	
	@Mapping(target = "quiz.id", source = "idQuiz")
	RodadaDomain mapDomain(RodadaModelRequest rodadaModelRequest);
}