package br.com.adatech.moviesbattle.application.service.login;

import org.springframework.stereotype.Service;

import br.com.adatech.moviesbattle.application.domain.JogadorDomain;
import br.com.adatech.moviesbattle.application.port.in.JogadorPortOut;

@Service
public class BuscaJogador {

	private final JogadorPortOut jogadorPortOut;

	public BuscaJogador(JogadorPortOut jogadorPortOut) {
		this.jogadorPortOut = jogadorPortOut;
	}

	public JogadorDomain buscarPorId(Long id) {
		return jogadorPortOut.buscarJogador(id);
	}
}
