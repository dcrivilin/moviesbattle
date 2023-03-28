package br.com.adatech.moviesbattle.adapter.in.http.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.adatech.moviesbattle.adapter.in.http.controller.model.request.FilmeModelRequest;
import br.com.adatech.moviesbattle.adapter.in.http.controller.model.response.FilmeModelResponse;
import br.com.adatech.moviesbattle.application.domain.FilmeDomain;

@Mapper
public interface FilmeMapper {

	FilmeMapper INSTANCE = Mappers.getMapper(FilmeMapper.class);
	
	FilmeModelResponse mapModel(FilmeDomain filmeDomain);
	FilmeDomain mapModel(FilmeModelRequest filmeModelRequest);
}
