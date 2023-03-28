package br.com.adatech.moviesbattle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.adatech.moviesbattle.application.service.filme.CadastraFilmes;

@SpringBootApplication
public class MoviesbattleApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(MoviesbattleApplication.class, args);

		CadastraFilmes cadastraFilmes = run.getBean(CadastraFilmes.class);
		cadastraFilmes.carregarFilmes();

//		OrquestracaoQuizService orquestrador = run.getBean(OrquestracaoQuizService.class);

//		// INICIAR QUIZ
//		QuizDomain quiz = orquestrador.iniciarQuiz(getJogador());
//
//		// SOLICITAR NOVA RODADA
//		RodadaDomain rodada1 = orquestrador.solicitarNovaRodada(quiz, getJogador());
//
//		// ESCOLHER RESPOSTA CERTA
//		if (rodada1.getPrimeiroFilme().getPontuacao() > rodada1.getSegundoFilme().getPontuacao()) {
//			rodada1.setFilmeEscolhido(rodada1.getPrimeiroFilme());
//		} else {
//			rodada1.setFilmeEscolhido(rodada1.getSegundoFilme());
//		}
//
//		// RESPONDE RODADA CERTA
//		orquestrador.responderRodada(rodada1);
//		
//		/*******************/
//		// SOLICITAR NOVA RODADA
//		RodadaDomain rodada2 = orquestrador.solicitarNovaRodada(quiz, getJogador());
//
//		// ESCOLHER RESPOSTA ERRADA
//		if (rodada2.getPrimeiroFilme().getPontuacao() < rodada2.getSegundoFilme().getPontuacao()) {
//			rodada2.setFilmeEscolhido(rodada2.getPrimeiroFilme());
//		} else {
//			rodada2.setFilmeEscolhido(rodada2.getSegundoFilme());
//		}
//
//		// RESPONDE RODADA ERRADA
//		orquestrador.responderRodada(rodada2);
//
//		/*******************/
//		// SOLICITAR NOVA RODADA
//		RodadaDomain rodada3 = orquestrador.solicitarNovaRodada(quiz, getJogador());
//
//		// ESCOLHER RESPOSTA ERRADA
//		if (rodada3.getPrimeiroFilme().getPontuacao() < rodada3.getSegundoFilme().getPontuacao()) {
//			rodada3.setFilmeEscolhido(rodada3.getPrimeiroFilme());
//		} else {
//			rodada3.setFilmeEscolhido(rodada3.getSegundoFilme());
//		}
//
//		// RESPONDE RODADA ERRADA
//		orquestrador.responderRodada(rodada3);
//		
//		orquestrador.responderRodada(rodada3);
//
//		/*******************/
//		// SOLICITAR NOVA RODADA
//		RodadaDomain rodada4 = orquestrador.solicitarNovaRodada(quiz, getJogador());
//
//		// ESCOLHER RESPOSTA ERRADA
//		if (rodada4.getPrimeiroFilme().getPontuacao() < rodada4.getSegundoFilme().getPontuacao()) {
//			rodada4.setFilmeEscolhido(rodada4.getPrimeiroFilme());
//		} else {
//			rodada4.setFilmeEscolhido(rodada4.getSegundoFilme());
//		}
//
//		// RESPONDE RODADA ERRADA
//		orquestrador.responderRodada(rodada4);
//		
//
//		// ENCERRAR QUIZ
//		orquestrador.encerrarQuiz(quiz);
//
//		System.out.println("");

	}

//	private static JogadorDomain getJogador() {
//		JogadorDomain jogadorDomain = new JogadorDomain();
//		jogadorDomain.setId((long) 1);
//		jogadorDomain.setNome("Danilo");
//		jogadorDomain.setSenha("1234");
//		jogadorDomain.setUsuario("Crivilin");
//
//		return jogadorDomain;
//	}
}