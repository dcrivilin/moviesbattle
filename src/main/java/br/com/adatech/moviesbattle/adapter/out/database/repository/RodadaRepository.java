package br.com.adatech.moviesbattle.adapter.out.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.adatech.moviesbattle.adapter.out.database.entity.RodadaEntity;

@Repository
public interface RodadaRepository extends JpaRepository<RodadaEntity, Long>{

	@Query(value = "select max(id) from rodada", nativeQuery = true)
	Optional<Long> findMaxId();
	
	Optional<List<RodadaEntity>> findByQuizIdAndRespostaId(Long idQuiz, Long idResposta);
	
	Optional<RodadaEntity> findFirstByQuizIdAndRespostaIdOrderByIdAsc(Long idQuiz, Long idResposta);
	
	@Query(value = "select count(*) from rodada"
			+ " where quiz_id = ?"
			+ " and resposta_id = ?", nativeQuery = true)
	Optional<Integer> findByQuantidadeErrosQuiz(Long idQuiz, Long idResposta);
}
