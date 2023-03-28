package br.com.adatech.moviesbattle.application.service.filme;

import org.springframework.stereotype.Service;

import br.com.adatech.moviesbattle.application.port.out.BuscaFilmesWebScrapingPortOut;
import br.com.adatech.moviesbattle.application.port.out.CadastraFilmesPortOut;

@Service
public class CadastraFilmes {
	
	private final CadastraFilmesPortOut cadastraFilmesPortOut;
	private final BuscaFilmesWebScrapingPortOut buscaFilmesWebScrapingPortOut;

	public CadastraFilmes(CadastraFilmesPortOut cadastraFilmesPortOut, BuscaFilmesWebScrapingPortOut buscaFilmesWebScrapingPortOut) {
		this.cadastraFilmesPortOut = cadastraFilmesPortOut;
		this.buscaFilmesWebScrapingPortOut = buscaFilmesWebScrapingPortOut;
	}

	public void carregarFilmes() {
		cadastraFilmesPortOut.cadastrarFilmes(buscaFilmesWebScrapingPortOut.buscarFilmes());
	};
}