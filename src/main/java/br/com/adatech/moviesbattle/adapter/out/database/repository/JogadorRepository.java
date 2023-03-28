package br.com.adatech.moviesbattle.adapter.out.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adatech.moviesbattle.adapter.out.database.entity.JogadorEntity;

@Repository
public interface JogadorRepository extends JpaRepository<JogadorEntity, Long>{
	
	Optional<JogadorEntity> findByUsuarioAndSenha(String usuario, String senha);
}