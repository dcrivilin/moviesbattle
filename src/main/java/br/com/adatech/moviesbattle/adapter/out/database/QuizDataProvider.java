package br.com.adatech.moviesbattle.adapter.out.database;

import static br.com.adatech.moviesbattle.adapter.out.database.mapper.QuizMapperDataProvider.INSTANCE;
import static org.springframework.util.ObjectUtils.isEmpty;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.springframework.stereotype.Component;

import br.com.adatech.moviesbattle.adapter.out.database.entity.QuizEntity;
import br.com.adatech.moviesbattle.adapter.out.database.entity.RankingEntity;
import br.com.adatech.moviesbattle.adapter.out.database.exception.ExceptionDataProvider;
import br.com.adatech.moviesbattle.adapter.out.database.mapper.RankingMapperDataProvider;
import br.com.adatech.moviesbattle.adapter.out.database.repository.QuizRepository;
import br.com.adatech.moviesbattle.adapter.out.database.repository.RodadaRepository;
import br.com.adatech.moviesbattle.application.domain.JogadorDomain;
import br.com.adatech.moviesbattle.application.domain.QuizDomain;
import br.com.adatech.moviesbattle.application.domain.RankingDomain;
import br.com.adatech.moviesbattle.application.port.out.QuizPortOut;

@Component
public class QuizDataProvider implements QuizPortOut {

	private final QuizRepository quizRepository;
	private final RodadaRepository rodadaRepository;

	public QuizDataProvider(QuizRepository quizRepository, RodadaRepository rodadaRepository) {
		this.quizRepository = quizRepository;
		this.rodadaRepository = rodadaRepository;
	}

	@Override
	public void persistirQuiz(QuizDomain quiz) {
		quizRepository.save(INSTANCE.mapEntity(quiz));
	}

	@Override
	public Long buscarIdUltimoQuiz() {
		Optional<Long> maxId = quizRepository.findMaxId();

		if (!maxId.isPresent())
			return (long) 0;
		return maxId.get();
	}

	@Override
	public boolean existeQuizEmAndamento(JogadorDomain jogador) {
		Optional<QuizEntity> quiz = quizRepository.findByJogadorIdAndSituacao(jogador.getId(), "PENDENTE");
		if (quiz.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public void somarPontos(QuizDomain quiz) {
		QuizEntity quizEntity = INSTANCE.mapEntity(buscarPorId(quiz.getId()));

		Integer pontuacao = isEmpty(quizEntity.getPontuacao()) ? 1 : quizEntity.getPontuacao() + 1;
		Integer quantidadeAcertos = isEmpty(quizEntity.getQuantidadeAcerto()) ? 1
				: quizEntity.getQuantidadeAcerto() + 1;

		quizEntity.setPontuacao(pontuacao);
		quizEntity.setQuantidadeAcerto(quantidadeAcertos);
		quizRepository.save(quizEntity);
	}

	@Override
	public QuizDomain buscarPorId(Long id) {
		Optional<QuizEntity> quiz = quizRepository.findById(id);
		if (quiz.isPresent()) {
			return INSTANCE.mapDomain(quiz.get());
		}
		throw new ExceptionDataProvider("Quiz não encontrado");
	}

	@Override
	public Integer buscarQuantidadeErro(QuizDomain quiz) {
		return rodadaRepository.findByQuantidadeErrosQuiz(quiz.getId(), (long) 3).get();
	}

	@Override
	public void encerrarQuiz(QuizDomain quiz) {
		persistirQuiz(quiz);
	}

	@Override
	public List<RankingDomain> ranquearPontuacao() {

		try {
			Optional<List<Tuple>> listTuple = quizRepository.findRanking();

			if (listTuple.isPresent()) {
				List<Tuple> list = listTuple.get();

				List<RankingEntity> ranking = list.stream()
						.map(t -> new RankingEntity(
								t.get(0, BigInteger.class).longValue(),
								t.get(1, BigInteger.class).longValue(), 
								t.get(2, BigInteger.class).intValue()))
						.collect(Collectors.toList());

				return RankingMapperDataProvider.INSTANCE.mapDomain(ranking);
			}
			
			//TODO fazer algo mais inteligente
			throw new ExceptionDataProvider("Não foram encontrados quizzes encerrados para o ranking");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionDataProvider("Não foram encontrados quizzes encerrados para o ranking");

		}
	}
}