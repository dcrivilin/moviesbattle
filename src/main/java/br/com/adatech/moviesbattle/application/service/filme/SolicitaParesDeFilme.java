package br.com.adatech.moviesbattle.application.service.filme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.adatech.moviesbattle.application.domain.FilmeDomain;

/**
 * Para atender as regras de igualdade dos pares foi necessário implementar 
 * os metodos hashcode e equals na classe FilmeDomain, além de implementar a interface Comparable.
 * */
@Service
public class SolicitaParesDeFilme {

	private final BuscaFilmes buscaFilmes;

	public SolicitaParesDeFilme(BuscaFilmes buscaFilmes) {
		this.buscaFilmes = buscaFilmes;
	}

	public List<FilmeDomain[]> criarListaComParesDeFilmesRandomicos() {

		List<FilmeDomain> filmes = buscaFilmes.buscarFilmesQuiz();
		List<FilmeDomain[]> listaDePares = new ArrayList<>();
		

		do {
			FilmeDomain[] arrayDeFilmes = new FilmeDomain[2];
			
			arrayDeFilmes[0] = filmes.get(new Random().nextInt(filmes.size() - 1));

			List<FilmeDomain> novaLista = filmes.stream()
					.filter(e -> !e.equals(arrayDeFilmes[0]) && e.getPontuacao() != arrayDeFilmes[0].getPontuacao())
					.collect(Collectors.toList());

			arrayDeFilmes[1] = novaLista.get(new Random().nextInt(novaLista.size() - 1));

			adicionarParNaLista(listaDePares, arrayDeFilmes);

		} while (listaDePares.size() <= 24);

		return listaDePares;

	}

	private void adicionarParNaLista(List<FilmeDomain[]> listaDePares, FilmeDomain[] arrayDeFilmes) {
		if (listaDePares.stream().filter(par -> parJaAdicionado(par, arrayDeFilmes)).collect(Collectors.toList())
				.isEmpty()) {
			listaDePares.add(arrayDeFilmes);
		}
	}

	private boolean parJaAdicionado(FilmeDomain[] array1, FilmeDomain[] array2) {
		if (array1.equals(array2)) {
			return true;
		}

		Arrays.sort(array2);

		if (array1.equals(array2)) {
			return true;
		} else {
			return false;
		}
	}
}