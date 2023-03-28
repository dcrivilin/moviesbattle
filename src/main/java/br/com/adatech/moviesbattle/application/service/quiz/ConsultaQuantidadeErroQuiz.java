package br.com.adatech.moviesbattle.application.service.quiz;

import org.springframework.stereotype.Service;

import br.com.adatech.moviesbattle.application.domain.QuizDomain;
import br.com.adatech.moviesbattle.application.port.out.QuizPortOut;

@Service
public class ConsultaQuantidadeErroQuiz {
	
	private final QuizPortOut quizPortOut;

	public ConsultaQuantidadeErroQuiz(QuizPortOut quizPortOut) {
		this.quizPortOut = quizPortOut;
	}
	
	public int execute(QuizDomain quiz) {
		return quizPortOut.buscarQuantidadeErro(quiz);
	}
}