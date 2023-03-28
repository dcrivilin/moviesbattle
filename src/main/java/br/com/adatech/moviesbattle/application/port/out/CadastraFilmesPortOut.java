package br.com.adatech.moviesbattle.application.port.out;

import java.util.List;

import br.com.adatech.moviesbattle.application.domain.FilmeDomain;

public interface CadastraFilmesPortOut {
	
	void cadastrarFilmes(List<FilmeDomain> filmes);
}