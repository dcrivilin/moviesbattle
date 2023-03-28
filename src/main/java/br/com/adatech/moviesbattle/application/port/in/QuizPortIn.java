package br.com.adatech.moviesbattle.application.port.in;

import java.util.List;

import br.com.adatech.moviesbattle.application.domain.JogadorDomain;
import br.com.adatech.moviesbattle.application.domain.QuizDomain;
import br.com.adatech.moviesbattle.application.domain.RankingDomain;
import br.com.adatech.moviesbattle.application.domain.RodadaDomain;

public interface QuizPortIn {

	QuizDomain iniciarQuiz(JogadorDomain jogador);
	
	RodadaDomain solicitarNovaRodada(Long idQuiz, JogadorDomain jogador);
	
	void responderRodada(RodadaDomain rodada, JogadorDomain jogador);
	
	void encerrarQuiz(Long idQuiz, JogadorDomain jogador);
	
	List<RankingDomain> ranquearPontuacaoQuiz();
}
