package br.com.adatech.moviesbattle.application.service.rodada;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.adatech.moviesbattle.application.domain.QuizDomain;
import br.com.adatech.moviesbattle.application.domain.RodadaDomain;
import br.com.adatech.moviesbattle.application.exception.ServiceException;
import br.com.adatech.moviesbattle.application.port.out.RodadaPortOut;

@Service
public class SolicitaRodada {

	private final RodadaPortOut rodadaPortOut;

	public SolicitaRodada(RodadaPortOut rodadaPortOut) {
		this.rodadaPortOut = rodadaPortOut;
	}

	public RodadaDomain execute(QuizDomain quiz) {
		if (rodadaPortOut.existeRodadaPendente(quiz)) {
			return rodadaPortOut.buscarProximaRodada(quiz);
		}
		throw new ServiceException("Não há rodadas pendente de resposta para esse Quiz.");
	}

	public List<RodadaDomain> buscaRodadasPendentes(QuizDomain quiz) {
		return rodadaPortOut.buscarRodadasPendentes(quiz);
	}
}
