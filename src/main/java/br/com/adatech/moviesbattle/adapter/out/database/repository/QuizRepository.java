package br.com.adatech.moviesbattle.adapter.out.database.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.adatech.moviesbattle.adapter.out.database.entity.QuizEntity;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, Long>{

	@Query(value = "select max(id) from quiz", nativeQuery = true)
	Optional<Long> findMaxId();
	
	Optional<QuizEntity> findByJogadorIdAndSituacao(Long idJogador, String situacao);
	
	@Query(value = " SELECT ROW_NUMBER() OVER (ORDER BY ((SUM(PONTUACAO) * 100) / SUM(QUIZ.QTD_RODADA))  DESC) posicao "
			+ " , QUIZ.JOGADOR_ID idJogador "
			+ " ,  COUNT(QUIZ.ID) * ((SUM(PONTUACAO) * 100) / SUM(QUIZ.QTD_RODADA)) pontuacao "
			+ "    FROM QUIZ "
			+ "   WHERE QUIZ.SITUACAO = 'ENCERRADO' "
			+ "     AND QUIZ. PONTUACAO IS NOT NULL "
			+ "   GROUP BY QUIZ.JOGADOR_ID "
			+ "   ORDER BY pontuacao DESC", nativeQuery = true)
	Optional<List<Tuple>> findRanking();
}