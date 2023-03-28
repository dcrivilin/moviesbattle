package br.com.adatech.moviesbattle.application.port.out;

import java.util.List;

import br.com.adatech.moviesbattle.application.domain.QuizDomain;
import br.com.adatech.moviesbattle.application.domain.RodadaDomain;

public interface RodadaPortOut {
	
	void persistirRodadas(List<RodadaDomain> rodadas);
	
	Long buscarIdUltimaRodada();
	
	boolean existeRodadaPendente(QuizDomain quiz);
	
	RodadaDomain buscarProximaRodada(QuizDomain quiz);
	
	void persisteRodada(RodadaDomain rodada);
	
	List<RodadaDomain> buscarRodadasPendentes(QuizDomain quiz);
	
	RodadaDomain  buscarRodadaPorId(Long id);
}
