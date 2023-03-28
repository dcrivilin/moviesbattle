package br.com.adatech.moviesbattle.application.port.in;

import br.com.adatech.moviesbattle.application.domain.JogadorDomain;

public interface AutenticaJogadorPortIn {
	
	JogadorDomain execute(String usuario, String senha);
}