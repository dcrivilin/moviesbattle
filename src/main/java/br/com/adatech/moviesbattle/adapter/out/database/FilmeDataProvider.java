package br.com.adatech.moviesbattle.adapter.out.database;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.adatech.moviesbattle.adapter.out.database.mapper.FilmeMapperDataProvider;
import br.com.adatech.moviesbattle.adapter.out.database.repository.FilmeRepository;
import br.com.adatech.moviesbattle.application.domain.FilmeDomain;
import br.com.adatech.moviesbattle.application.port.out.BuscaFilmesQuizPortOut;
import br.com.adatech.moviesbattle.application.port.out.CadastraFilmesPortOut;

@Component
public class FilmeDataProvider implements CadastraFilmesPortOut, BuscaFilmesQuizPortOut{
	
	private final FilmeRepository filmeRepository;
	private final FilmeMapperDataProvider filmeMapperDataProvider;
	
	public FilmeDataProvider(FilmeRepository filmeRepository, FilmeMapperDataProvider filmeMapperDataProvider) {
		this.filmeRepository = filmeRepository;
		this.filmeMapperDataProvider = filmeMapperDataProvider;
	}

	@Override
	public void cadastrarFilmes(List<FilmeDomain> filmes) {
		filmeRepository.saveAll(filmeMapperDataProvider.mapEntity(filmes));
	}

	@Override
	public List<FilmeDomain> buscarFilmes() {
		return filmeMapperDataProvider.mapDomain(filmeRepository.findAll());
	}
}