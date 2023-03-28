package br.com.adatech.moviesbattle.application.service.filme;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.adatech.moviesbattle.application.domain.FilmeDomain;
import br.com.adatech.moviesbattle.application.port.out.BuscaFilmesQuizPortOut;
import br.com.adatech.moviesbattle.application.port.out.BuscaFilmesWebScrapingPortOut;

@Service
public class BuscaFilmes {

	private final BuscaFilmesWebScrapingPortOut buscaFilmesPortOut;
	private final BuscaFilmesQuizPortOut BuscaFilmesQuizPortOut;

	public BuscaFilmes(BuscaFilmesWebScrapingPortOut buscaFilmesPortOut, BuscaFilmesQuizPortOut BuscaFilmesQuizPortOut) {
		this.buscaFilmesPortOut = buscaFilmesPortOut;
		this.BuscaFilmesQuizPortOut = BuscaFilmesQuizPortOut;
	}

	public List<FilmeDomain> buscarFilmesImbd() {
		return buscaFilmesPortOut.buscarFilmes();
	}
	
	public List<FilmeDomain> buscarFilmesQuiz() {
		return BuscaFilmesQuizPortOut.buscarFilmes();
	}
}
