package br.com.adatech.moviesbattle.application.service.quiz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.adatech.moviesbattle.application.domain.JogadorDomain;
import br.com.adatech.moviesbattle.application.domain.RankingDomain;
import br.com.adatech.moviesbattle.application.port.out.QuizPortOut;
import br.com.adatech.moviesbattle.application.service.login.BuscaJogador;

@Service
public class ConsolidarPontuacaoQuiz {
	
	private final QuizPortOut quizPortOut;
	private final BuscaJogador buscaJogador;

	public ConsolidarPontuacaoQuiz(QuizPortOut quizPortOut, BuscaJogador buscaJogador) {
		this.quizPortOut = quizPortOut;
		this.buscaJogador = buscaJogador;
	}
	
	public List<RankingDomain> ranquearPontuacaoQuiz() {
		
		List<RankingDomain> rankingRetorno = new ArrayList<>();
		
		quizPortOut.ranquearPontuacao().forEach(r -> {
			RankingDomain ranked = new RankingDomain();
			ranked = r;
			JogadorDomain jogadorDomain = buscaJogador.buscarPorId(r.getJogador().getId());
			ranked.setJogador(jogadorDomain);
			rankingRetorno.add(ranked);
		});
		
		return rankingRetorno;
	}
}