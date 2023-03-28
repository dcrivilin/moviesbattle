package br.com.adatech.moviesbattle.adapter.in.http.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.adatech.moviesbattle.adapter.in.http.controller.mapper.QuizMapper;
import br.com.adatech.moviesbattle.adapter.in.http.controller.mapper.RankingMapper;
import br.com.adatech.moviesbattle.adapter.in.http.controller.mapper.RodadaMapper;
import br.com.adatech.moviesbattle.adapter.in.http.controller.model.request.QuizModelRequest;
import br.com.adatech.moviesbattle.adapter.in.http.controller.model.request.RodadaModelRequest;
import br.com.adatech.moviesbattle.adapter.in.http.controller.model.response.QuizModelResponse;
import br.com.adatech.moviesbattle.adapter.in.http.controller.model.response.RankingModelResponse;
import br.com.adatech.moviesbattle.adapter.in.http.controller.model.response.RodadaModelResponse;
import br.com.adatech.moviesbattle.application.domain.JogadorDomain;
import br.com.adatech.moviesbattle.application.port.in.AutenticaJogadorPortIn;
import br.com.adatech.moviesbattle.application.port.in.QuizPortIn;

@RestController
//TODO criar interface para que a classe fique mais legível (com menos anotations)
public class QuizController {

	private final QuizPortIn quizPortIn;
	private final AutenticaJogadorPortIn autenticaJogadorPortIn;

	public QuizController(QuizPortIn quizPortIn, AutenticaJogadorPortIn autenticaJogadorPortIn) {
		this.quizPortIn = quizPortIn;
		this.autenticaJogadorPortIn = autenticaJogadorPortIn;
	}

	@GetMapping(value = "quiz")
	private ResponseEntity<QuizModelResponse> quiz(@RequestHeader("Authorization") String authentication) {

		JogadorDomain jogador = verificarDadosAutenticacao(authentication);
		return ResponseEntity.status(201).body(QuizMapper.INSTANCE.mapModel(quizPortIn.iniciarQuiz(jogador)));
	}

	@GetMapping(value = "rodada")
	private ResponseEntity<RodadaModelResponse> rodada(@RequestHeader("Authorization") String authentication,
			@RequestParam("idQuiz") Long idQuiz) {
		return ResponseEntity.status(201).body(RodadaMapper.INSTANCE
				.mapModel(quizPortIn.solicitarNovaRodada(idQuiz, verificarDadosAutenticacao(authentication))));
	}

	@PostMapping(value = "responder_rodada")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	private void responderRodada(@RequestHeader("Authorization") String authentication,
			@Valid @RequestBody RodadaModelRequest rodadaModelRequest) {
		quizPortIn.responderRodada(RodadaMapper.INSTANCE.mapDomain(rodadaModelRequest), verificarDadosAutenticacao(authentication));
	}

	@PostMapping(value = "encerrar_quiz")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	private void encerrarQuiz(@RequestHeader("Authorization") String authentication,
			@Valid @RequestBody QuizModelRequest idQuiz) {
		quizPortIn.encerrarQuiz(idQuiz.getId(), verificarDadosAutenticacao(authentication));
	}

	@GetMapping(value = "ranking")
	private ResponseEntity<List<RankingModelResponse>> ranking() {
		return ResponseEntity.status(200).body(RankingMapper.INSTANCE.mapDomain(quizPortIn.ranquearPontuacaoQuiz()));
	}

	private JogadorDomain verificarDadosAutenticacao(String authentication) {
		String pair = new String(Base64.decodeBase64(authentication.substring(6)));
		String userName = pair.split(":")[0];
		String password = pair.split(":")[1];

		return autenticaJogadorPortIn.execute(userName, password);
	}
}