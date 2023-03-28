package br.com.adatech.moviesbattle.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.adatech.moviesbattle.application.domain.JogadorDomain;
import br.com.adatech.moviesbattle.application.domain.QuizDomain;
import br.com.adatech.moviesbattle.application.domain.RankingDomain;
import br.com.adatech.moviesbattle.application.domain.RodadaDomain;
import br.com.adatech.moviesbattle.application.exception.ServiceException;
import br.com.adatech.moviesbattle.application.service.filme.CadastraFilmes;
import br.com.adatech.moviesbattle.application.service.login.BuscaJogador;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "spring.h2.console.enabled=true")
class OrquestracaoQuizServiceTest {

	@Autowired
	private OrquestracaoQuizService orquestracaoQuizService;

	@Autowired
	private CadastraFilmes cadastraFilmes;

	@Autowired
	private BuscaJogador buscarJogador;
	
	@BeforeEach
    public void init() {
		// carregar filmes
		cadastraFilmes.carregarFilmes();
    }

	@Test
	@Order(1)
	void responderQuizComRespostasCorretas() {

		// buscar jogador
		JogadorDomain jogadorDomain = buscarJogador.buscarPorId((long) 1);

		// solicitar Quiz
		QuizDomain quiz = orquestracaoQuizService.iniciarQuiz(jogadorDomain);

		// responder rodadas corretamente
		responderTodasRodadasCorretamente(jogadorDomain, quiz);

		// encerrar quiz
		orquestracaoQuizService.encerrarQuiz(quiz.getId(), jogadorDomain);

		// solicitar ranking
		List<RankingDomain> ranking = orquestracaoQuizService.ranquearPontuacaoQuiz();

		RankingDomain rankingDomain = ranking.get(0);

		assertFalse(ranking.isEmpty());
		assertEquals(1, rankingDomain.getPosicao());
		assertEquals("Crivilin", rankingDomain.getJogador().getUsuario());
		assertEquals(100, rankingDomain.getPontuacao());
	}

	@Test
	@Order(2)
	void partidaEncerradaPorNumeroDeErrosExcedidos() {

		// buscar jogador
		JogadorDomain jogadorDomain = buscarJogador.buscarPorId((long) 2);

		// solicitar Quiz
		QuizDomain quiz = orquestracaoQuizService.iniciarQuiz(jogadorDomain);

		ServiceException exception = assertThrows(ServiceException.class,
				() -> responderTodasRodadasIncorretamente(jogadorDomain, quiz));
		assertEquals("Partida encerrada. Voce errou mais de tres vezes.", exception.getMessage());
	}

	@Test
	@Order(3)
	void validandoJogadorNaCamadaService() {

		// buscar jogador
		JogadorDomain jogadorDomain = buscarJogador.buscarPorId((long) 1);

		// solicitar Quiz
		QuizDomain quiz = orquestracaoQuizService.iniciarQuiz(jogadorDomain);

		/** USUÁRIO CORRETO **/

		// solicitar rodada com usuário correto
		RodadaDomain rodada1 = orquestracaoQuizService.solicitarNovaRodada(quiz.getId(), jogadorDomain);

		rodada1.setFilmeEscolhido(rodada1.getPrimeiroFilme());

		// responder rodada com usuário correto
		orquestracaoQuizService.responderRodada(rodada1, jogadorDomain);

		/** USUÁRIO INCORRETO **/

		JogadorDomain jogadorDomain2 = buscarJogador.buscarPorId((long) 2);

		// solicitando rodada com usuário incorreto
		ServiceException exception = assertThrows(ServiceException.class,
				() -> responderTodasRodadasCorretamente(jogadorDomain2, quiz));
		assertEquals("Quiz não pertence ao jogador informado", exception.getMessage());

		// rodada com usuário incorreto
		ServiceException exception2 = assertThrows(ServiceException.class,
				() -> orquestracaoQuizService.responderRodada(rodada1, jogadorDomain2));
		assertEquals("Quiz não pertence ao jogador informado", exception2.getMessage());

		// encerrar quiz com usuário incorreto
		ServiceException exception3 = assertThrows(ServiceException.class,
				() -> orquestracaoQuizService.encerrarQuiz(quiz.getId(), jogadorDomain2));
		assertEquals("Quiz não pertence ao jogador informado", exception3.getMessage());
	}

	private void responderTodasRodadasCorretamente(JogadorDomain jogadorDomain, QuizDomain quiz) {
		for (int i = 0; i < quiz.getQuantidadeRodada(); i++) {

			// solicitar rodada
			RodadaDomain rodada1 = orquestracaoQuizService.solicitarNovaRodada(quiz.getId(), jogadorDomain);

			// escolher resposta correta
			if (rodada1.getPrimeiroFilme().getPontuacao() > rodada1.getSegundoFilme().getPontuacao()) {
				rodada1.setFilmeEscolhido(rodada1.getPrimeiroFilme());
			} else {
				rodada1.setFilmeEscolhido(rodada1.getSegundoFilme());
			}

			// responder resposta correta
			orquestracaoQuizService.responderRodada(rodada1, jogadorDomain);
		}
	}

	private void responderTodasRodadasIncorretamente(JogadorDomain jogadorDomain, QuizDomain quiz) {
		for (int i = 0; i < quiz.getQuantidadeRodada(); i++) {

			// solicitar rodada
			RodadaDomain rodada = orquestracaoQuizService.solicitarNovaRodada(quiz.getId(), jogadorDomain);

			// escolher resposta correta
			if (rodada.getPrimeiroFilme().getPontuacao() < rodada.getSegundoFilme().getPontuacao()) {
				rodada.setFilmeEscolhido(rodada.getPrimeiroFilme());
			} else {
				rodada.setFilmeEscolhido(rodada.getSegundoFilme());
			}

			// responder resposta correta
			orquestracaoQuizService.responderRodada(rodada, jogadorDomain);
		}
	}

}
