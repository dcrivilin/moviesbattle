package br.com.adatech.moviesbattle.adapter.out.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adatech.moviesbattle.adapter.out.database.entity.FilmeEntity;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeEntity, Long> {
}
