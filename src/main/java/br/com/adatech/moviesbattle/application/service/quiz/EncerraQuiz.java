package br.com.adatech.moviesbattle.application.service.quiz;

import org.springframework.stereotype.Service;

import br.com.adatech.moviesbattle.application.domain.QuizDomain;
import br.com.adatech.moviesbattle.application.port.out.QuizPortOut;

@Service
public class EncerraQuiz {

	private final QuizPortOut quizPortOut;

	public EncerraQuiz(QuizPortOut quizPortOut) {
		this.quizPortOut = quizPortOut;
	}
	
	public void execute(QuizDomain quiz) {
		quizPortOut.encerrarQuiz(quiz);
	}
}
