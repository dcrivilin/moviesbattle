package br.com.adatech.moviesbattle.application.service.rodada;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.adatech.moviesbattle.application.domain.FilmeDomain;
import br.com.adatech.moviesbattle.application.domain.QuizDomain;
import br.com.adatech.moviesbattle.application.domain.RespostaDomain;
import br.com.adatech.moviesbattle.application.domain.RodadaDomain;
import br.com.adatech.moviesbattle.application.port.out.RodadaPortOut;
import br.com.adatech.moviesbattle.application.service.filme.SolicitaParesDeFilme;

@Service
public class CriaRodada {
	
	private final SolicitaParesDeFilme solicitaParesDeFilme;
	private final RodadaPortOut rodadaPortOut;
	
	public CriaRodada(SolicitaParesDeFilme solicitaParesDeFilme, RodadaPortOut rodadaPortOut) {
		this.solicitaParesDeFilme = solicitaParesDeFilme;
		this.rodadaPortOut = rodadaPortOut;
	}

	public List<RodadaDomain> execute(QuizDomain quiz) {
		return criarRodadas(quiz, solicitaParesDeFilme.criarListaComParesDeFilmesRandomicos());
	}
	
	private List<RodadaDomain> criarRodadas(QuizDomain quiz, List<FilmeDomain[]> filmes) {
		Long maxId = buscarIdUltimaRodada();
		List<RodadaDomain> retorno = new ArrayList<>();
		AtomicLong atomicLong = new AtomicLong();
		atomicLong.set(maxId+1);
		
		filmes.forEach(filme -> {
			RodadaDomain rodadaDomain = new RodadaDomain();
			rodadaDomain.setId(atomicLong.getAndIncrement());
			rodadaDomain.setQuiz(quiz);
			rodadaDomain.setDescricao("Qual dos filmes tem a maior pontuação?");
			rodadaDomain.setPrimeiroFilme(filme[0]);
			rodadaDomain.setSegundoFilme(filme[1]);
			
			RespostaDomain respostaDomain = new RespostaDomain();
			respostaDomain.setId((long) 1);
			respostaDomain.setDescricao("SEM_RESPOSTA");
			rodadaDomain.setResposta(respostaDomain);
			
			retorno.add(rodadaDomain);
		});
		
		return retorno;
	}
	
	public void criarRodada(List<RodadaDomain> rodadas) {
		rodadaPortOut.persistirRodadas(rodadas);
	}
	
	private Long buscarIdUltimaRodada() {
		return rodadaPortOut.buscarIdUltimaRodada();
	}
}