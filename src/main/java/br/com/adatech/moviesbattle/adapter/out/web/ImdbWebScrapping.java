package br.com.adatech.moviesbattle.adapter.out.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import br.com.adatech.moviesbattle.application.domain.FilmeDomain;
import br.com.adatech.moviesbattle.application.port.out.BuscaFilmesWebScrapingPortOut;

/**
 * Essa classe foi retirada do código fonte abaixo:
 * https://github.com/rflima11/movies-battle/blob/master/src/main/java/br/com/letscode/moviebattle/service/impl/ImdbWebScrapping.java
 * 
 * Foi acrescentado os votos para montar a pontuação do filme no quiz.
 * Regra: "O jogador deve tentar acertar .... a portuação é composta pela nota multiplicada pelo total de votos"
 * **/
@Component
public class ImdbWebScrapping implements BuscaFilmesWebScrapingPortOut {
	
	@Override
	public List<FilmeDomain> buscarFilmes() {
		try {
            var document = Jsoup
            		.connect("https://www.imdb.com/search/title/?title_type=movie&genres=action&sort=user_rating,desc&explore=title_type,genres")
            		.get();

            List<String> titulos = document.getElementsByClass("lister-item-header")
                    .stream()
                    .map(e -> (Element) e.childNode(3))
                    .map(Element::text)
                    .collect(Collectors.toList());

            List<Double> notas = document.getElementsByClass("inline-block ratings-imdb-rating")
                    .stream()
                    .map(e -> e.childNode(3).childNode(0))
                    .map(e -> Double.parseDouble(e.outerHtml()))
                    .collect(Collectors.toList());
            
            List<Integer> votos = document.getElementsByClass("sort-num_votes-visible")
                    .stream()
                    .map(e -> e.childNode(3).childNode(0))
                    .map(e -> Integer.parseInt(e.outerHtml().replace(",", "")))
                    .collect(Collectors.toList());
            
            return montarListaFilmes(titulos, notas, votos);
        } catch (IOException e1) {
        	//TODO Criar exception WebScrepping
			e1.printStackTrace();
		}
		return null; 
	}
	
	private List<FilmeDomain> montarListaFilmes(List<String> titulos, List<Double> notas, List<Integer> votos){
		List<FilmeDomain> filmes =  new ArrayList<FilmeDomain>();
		
		for (int i = 0; i < titulos.size(); i++) {
			String titulo = titulos.get(i);
			Double nota = notas.get(i); 
			Integer voto = votos.get(i);
			Long pontuacao = (long) (voto * nota);
			
			filmes.add(new FilmeDomain((long) i+1, titulo, nota, voto, pontuacao));
        }
		
		return filmes;
	}
}
