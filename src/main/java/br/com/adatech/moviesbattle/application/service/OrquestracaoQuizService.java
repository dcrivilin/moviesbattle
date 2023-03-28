package br.com.adatech.moviesbattle.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.adatech.moviesbattle.application.domain.JogadorDomain;
import br.com.adatech.moviesbattle.application.domain.QuizDomain;
import br.com.adatech.moviesbattle.application.domain.RankingDomain;
import br.com.adatech.moviesbattle.application.domain.RespostaDomain;
import br.com.adatech.moviesbattle.application.domain.RodadaDomain;
import br.com.adatech.moviesbattle.application.exception.ServiceException;
import br.com.adatech.moviesbattle.application.port.in.QuizPortIn;
import br.com.adatech.moviesbattle.application.port.out.QuizPortOut;
import br.com.adatech.moviesbattle.application.service.quiz.ConsolidarPontuacaoQuiz;
import br.com.adatech.moviesbattle.application.service.quiz.EncerraQuiz;
import br.com.adatech.moviesbattle.application.service.quiz.IniciaQuiz;
import br.com.adatech.moviesbattle.application.service.rodada.AtualizaRespostaRodada;
import br.com.adatech.moviesbattle.application.service.rodada.BuscarRodada;
import br.com.adatech.moviesbattle.application.service.rodada.SolicitaRodada;
import br.com.adatech.moviesbattle.application.service.rodada.VerificaRespostaCorreta;

//TODO Adicionar logs na camada service
@Service
public class OrquestracaoQuizService implements QuizPortIn {

	private final QuizPortOut quizPortOut;
	private final IniciaQuiz iniciaQuiz;
	private final SolicitaRodada solicitaRodada;
	private final VerificaRespostaCorreta verificaRespostaCorreta;
	private final AtualizaRespostaRodada atualizaRespostaRodada;
	private final EncerraQuiz encerraQuiz;
	private final BuscarRodada buscarRodada;
	private final ConsolidarPontuacaoQuiz consolidarPontuacaoQuiz;
	
	public OrquestracaoQuizService(QuizPortOut quizPortOut, IniciaQuiz iniciaQuiz, SolicitaRodada solicitaRodada,
			VerificaRespostaCorreta verificaRespostaCorreta, AtualizaRespostaRodada atualizaRespostaRodada,
			EncerraQuiz encerraQuiz, BuscarRodada buscarRodada, ConsolidarPontuacaoQuiz consolidarPontuacaoQuiz) {
		this.quizPortOut = quizPortOut;
		this.iniciaQuiz = iniciaQuiz;
		this.solicitaRodada = solicitaRodada;
		this.verificaRespostaCorreta = verificaRespostaCorreta;
		this.atualizaRespostaRodada = atualizaRespostaRodada;
		this.encerraQuiz = encerraQuiz;
		this.buscarRodada = buscarRodada;
		this.consolidarPontuacaoQuiz = consolidarPontuacaoQuiz;
	}

	@Override
	public QuizDomain iniciarQuiz(JogadorDomain jogador) {
		verificarPartidaAndamentoJogador(jogador);
		return iniciaQuiz.execute(jogador);
	}

	@Override
	public RodadaDomain solicitarNovaRodada(Long idQuiz, JogadorDomain jogador) {
		QuizDomain quiz = fazerValidacaoQuiz(idQuiz, jogador);

		try {
			return solicitaRodada.execute(quiz);
		} catch (ServiceException e) {
			encerrarQuiz(quiz.getId(), jogador);
			throw new ServiceException("Não há novas rodadas. Partida encerrada.");
		}
	}

	// TODO verificar se rodada já foi respondida
	// TODO retornar um "certa resposta", "resposta errada"
	@Override
	public void responderRodada(RodadaDomain rodadaRespondida, JogadorDomain jogador) {
		fazerValidacaoQuiz(rodadaRespondida.getQuiz().getId(), jogador);

		RodadaDomain rodadaBase = buscarRodada.buscarPorId(rodadaRespondida.getId());
		rodadaBase.setFilmeEscolhido(rodadaRespondida.getFilmeEscolhido());

		if (verificaRespostaCorreta.execute(rodadaRespondida, rodadaBase)) {
			atualizaRespostaRodada.atualizarRespostaRodada(getResposta(rodadaBase, (long) 2));
			quizPortOut.somarPontos(rodadaBase.getQuiz());
		} else {
			atualizaRespostaRodada.atualizarRespostaRodada(getResposta(rodadaBase, (long) 3));
			if (quizPortOut.buscarQuantidadeErro(rodadaBase.getQuiz()) >= 3) {
				encerrarQuiz(rodadaBase.getQuiz().getId(), jogador);
				throw new ServiceException("Partida encerrada. Voce errou mais de tres vezes.");
			}
		}
	}

	@Override
	//TODO Adicionar retorno amigavel
	public void encerrarQuiz(Long idQuiz, JogadorDomain jogador) {
		
		QuizDomain quiz = fazerValidacaoQuiz(idQuiz, jogador);
		
		List<RodadaDomain> rodadasPendentes = solicitaRodada.buscaRodadasPendentes(quiz);
		rodadasPendentes.forEach(rodada -> {

			RespostaDomain respostaDomain = new RespostaDomain();
			respostaDomain.setId((long) 4);
			rodada.setResposta(respostaDomain);
		});
		quiz.setSituacao("ENCERRADO");
		atualizaRespostaRodada.atualizarRespostasRodadas(rodadasPendentes);
		encerraQuiz.execute(quiz);
	}
	
	@Override
	public List<RankingDomain> ranquearPontuacaoQuiz() {
		return consolidarPontuacaoQuiz.ranquearPontuacaoQuiz();
	}

	private RodadaDomain getResposta(RodadaDomain rodada, Long idResposta) {
		RespostaDomain respostaDomain = new RespostaDomain();
		respostaDomain.setId(idResposta);
		rodada.setResposta(respostaDomain);
		return rodada;
	}

	private void verificarPartidaAndamentoJogador(JogadorDomain jogador) {
		if (quizPortOut.existeQuizEmAndamento(jogador)) {
			throw new ServiceException("Existe uma partida em andamento para esse usuário.");
		}
	}

	private QuizDomain fazerValidacaoQuiz(Long idQuiz, JogadorDomain jogador) {
		QuizDomain quiz = quizPortOut.buscarPorId(idQuiz);
		
		if (!quiz.getSituacao().equals("PENDENTE")) {
			throw new ServiceException("A partida está encerrada");
		}
		
		if (!quiz.getJogador().getId().equals(jogador.getId())) {
			throw new ServiceException("Quiz não pertence ao jogador informado");
		}
		
		return quiz;
	}
}