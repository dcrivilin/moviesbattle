package br.com.adatech.moviesbattle.adapter.out.database;

import static br.com.adatech.moviesbattle.adapter.out.database.mapper.RodadaMapperDataProvider.INSTANCE;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.adatech.moviesbattle.adapter.out.database.entity.RodadaEntity;
import br.com.adatech.moviesbattle.adapter.out.database.exception.ExceptionDataProvider;
import br.com.adatech.moviesbattle.adapter.out.database.repository.RodadaRepository;
import br.com.adatech.moviesbattle.application.domain.QuizDomain;
import br.com.adatech.moviesbattle.application.domain.RodadaDomain;
import br.com.adatech.moviesbattle.application.port.out.RodadaPortOut;

@Component
// TODO adicionar logs na camada adapter.out
public class RodadaDataProvider implements RodadaPortOut {

	private final RodadaRepository rodadaRepository;

	public RodadaDataProvider(RodadaRepository rodadaRepository) {
		this.rodadaRepository = rodadaRepository;
	}

	@Override
	public void persistirRodadas(List<RodadaDomain> rodadas) {
		rodadaRepository.saveAll(INSTANCE.mapEntity(rodadas));
	}

	@Override
	public Long buscarIdUltimaRodada() {
		Optional<Long> maxId = rodadaRepository.findMaxId();

		if (!maxId.isPresent())
			return (long) 0;
		return maxId.get();
	}

	@Override
	public boolean existeRodadaPendente(QuizDomain quiz) {
		Optional<List<RodadaEntity>> rodadas = rodadaRepository.findByQuizIdAndRespostaId(quiz.getId(), (long) 1);
		return rodadas.isPresent();
	}

	@Override
	public RodadaDomain buscarProximaRodada(QuizDomain quiz) {
		return INSTANCE
				.mapDomain(rodadaRepository.findFirstByQuizIdAndRespostaIdOrderByIdAsc(quiz.getId(), (long) 1).get());
	}

	@Override
	public void persisteRodada(RodadaDomain rodada) {
		rodadaRepository.save(INSTANCE.mapEntity(rodada));
	}

	@Override
	public List<RodadaDomain> buscarRodadasPendentes(QuizDomain quiz) {
		Optional<List<RodadaEntity>> optionalList = 
				rodadaRepository.findByQuizIdAndRespostaId(quiz.getId(), (long) 1);
		
		if (optionalList.isPresent()) {
			return INSTANCE.mapDomain(optionalList.get());
		}

		throw new ExceptionDataProvider("Não há rodadas pendentes.");
	}

	@Override
	public RodadaDomain buscarRodadaPorId(Long id) {
		Optional<RodadaEntity> optionalRodada = rodadaRepository.findById(id);
		if (optionalRodada.isPresent()) {
			RodadaEntity rodadaEntity = optionalRodada.get();
			return INSTANCE.mapDomain(rodadaEntity);
		} 
		
		throw new ExceptionDataProvider("Rodada não encontrada");
	}
}