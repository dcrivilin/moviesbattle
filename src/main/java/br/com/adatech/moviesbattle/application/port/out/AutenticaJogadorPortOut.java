package br.com.adatech.moviesbattle.application.port.out;

import br.com.adatech.moviesbattle.application.domain.JogadorDomain;

public interface AutenticaJogadorPortOut {

	JogadorDomain consultarUsuario(String usuario, String senha);
}
