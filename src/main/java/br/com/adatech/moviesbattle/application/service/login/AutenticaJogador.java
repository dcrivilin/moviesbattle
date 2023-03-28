package br.com.adatech.moviesbattle.application.service.login;

import org.springframework.stereotype.Service;

import br.com.adatech.moviesbattle.application.domain.JogadorDomain;
import br.com.adatech.moviesbattle.application.port.in.AutenticaJogadorPortIn;
import br.com.adatech.moviesbattle.application.port.out.AutenticaJogadorPortOut;

@Service
public class AutenticaJogador implements AutenticaJogadorPortIn{

	private final AutenticaJogadorPortOut autenticaPortOut;

	public AutenticaJogador(AutenticaJogadorPortOut autenticaPortOut) {
		this.autenticaPortOut = autenticaPortOut;
	}
	
	@Override
	public JogadorDomain execute(String usuario, String senha) {
		return autenticaPortOut.consultarUsuario(usuario, senha);
	}
}