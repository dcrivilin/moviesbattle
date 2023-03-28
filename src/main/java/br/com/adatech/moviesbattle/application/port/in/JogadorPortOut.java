package br.com.adatech.moviesbattle.application.port.in;

import br.com.adatech.moviesbattle.application.domain.JogadorDomain;

public interface JogadorPortOut {

	JogadorDomain buscarJogador(Long id);
}
