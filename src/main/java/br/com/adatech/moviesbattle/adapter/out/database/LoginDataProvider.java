package br.com.adatech.moviesbattle.adapter.out.database;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.adatech.moviesbattle.adapter.out.database.entity.JogadorEntity;
import br.com.adatech.moviesbattle.adapter.out.database.exception.ExceptionDataProvider;
import br.com.adatech.moviesbattle.adapter.out.database.mapper.JogadorMapperDataProvider;
import br.com.adatech.moviesbattle.adapter.out.database.repository.JogadorRepository;
import br.com.adatech.moviesbattle.application.domain.JogadorDomain;
import br.com.adatech.moviesbattle.application.port.in.JogadorPortOut;
import br.com.adatech.moviesbattle.application.port.out.AutenticaJogadorPortOut;

@Component
public class LoginDataProvider implements AutenticaJogadorPortOut, JogadorPortOut {

	private final JogadorRepository jogadorRepository;

	public LoginDataProvider(JogadorRepository jogadorRepository) {
		this.jogadorRepository = jogadorRepository;
	}

	@Override
	public JogadorDomain consultarUsuario(String usuario, String senha) {
		Optional<JogadorEntity> optionalJogador = jogadorRepository.findByUsuarioAndSenha(usuario, senha);

		if (optionalJogador.isPresent()) {
			return JogadorMapperDataProvider.INSTANCE
					.mapDomain(jogadorRepository.findByUsuarioAndSenha(usuario, senha).get());
		}

		throw new ExceptionDataProvider("Jogador não encontrado");
	}

	@Override
	public JogadorDomain buscarJogador(Long id) {
		Optional<JogadorEntity> optionalJogador = jogadorRepository.findById(id);

		if (optionalJogador.isPresent()) {
			return JogadorMapperDataProvider.INSTANCE.mapDomain(optionalJogador.get());
		}
		throw new ExceptionDataProvider("Jogador não encontrado");
	}
}
