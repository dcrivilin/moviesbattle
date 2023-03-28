package br.com.adatech.moviesbattle.application.port.out;

import java.util.List;

import br.com.adatech.moviesbattle.application.domain.JogadorDomain;
import br.com.adatech.moviesbattle.application.domain.QuizDomain;
import br.com.adatech.moviesbattle.application.domain.RankingDomain;

public interface QuizPortOut {
	
	void persistirQuiz(QuizDomain quiz);
	
	Long buscarIdUltimoQuiz();
	
	boolean existeQuizEmAndamento(JogadorDomain jogador);
	
	QuizDomain buscarPorId(Long id);
	
	void somarPontos(QuizDomain quiz);
	
	Integer buscarQuantidadeErro(QuizDomain quiz);
	
	void encerrarQuiz(QuizDomain quiz);
	
	List<RankingDomain> ranquearPontuacao();
}
