package br.com.adatech.moviesbattle.application.service.quiz;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.adatech.moviesbattle.application.domain.JogadorDomain;
import br.com.adatech.moviesbattle.application.domain.QuizDomain;
import br.com.adatech.moviesbattle.application.domain.RodadaDomain;
import br.com.adatech.moviesbattle.application.port.out.QuizPortOut;
import br.com.adatech.moviesbattle.application.service.rodada.CriaRodada;

@Service
public class IniciaQuiz{
	
	private final QuizPortOut quizPortOut;
	private final CriaRodada criarRodada;

	public IniciaQuiz(QuizPortOut quizPortOut, CriaRodada criarRodada) {
		this.quizPortOut = quizPortOut;
		this.criarRodada = criarRodada;
	}

	public QuizDomain execute(JogadorDomain jogador) {
		
		QuizDomain quiz = instanciarQuiz(jogador);
		List<RodadaDomain> rodadas = criarRodada.execute(quiz);
		
		quiz.setRodadas(rodadas);
		quiz.setQuantidadeRodada(rodadas.size());
		cadastrarNovoQuiz(quiz);
		criarRodada.criarRodada(quiz.getRodadas());
		return quiz;
	}
	
	private QuizDomain instanciarQuiz(JogadorDomain jogador) {
		Long maxId = buscarIdUltimoQuiz();
		AtomicLong atomicLong = new AtomicLong();
		atomicLong.set(maxId+1);

		QuizDomain quiz = new QuizDomain();
		quiz.setId(atomicLong.getAndIncrement());
		quiz.setJogador(jogador);
		quiz.setSituacao("PENDENTE");
		
		return quiz;
	}
	
	private Long buscarIdUltimoQuiz() {
		return quizPortOut.buscarIdUltimoQuiz();
	}
	
	private void cadastrarNovoQuiz(QuizDomain quiz) {
		quizPortOut.persistirQuiz(quiz);
	}
}