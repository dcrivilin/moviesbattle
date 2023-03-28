package br.com.adatech.moviesbattle.application.service.rodada;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.adatech.moviesbattle.application.domain.RodadaDomain;
import br.com.adatech.moviesbattle.application.port.out.RodadaPortOut;

@Service
public class AtualizaRespostaRodada {

	private final RodadaPortOut rodadaPortOut;

	public AtualizaRespostaRodada(RodadaPortOut rodadaPortOut) {
		this.rodadaPortOut = rodadaPortOut;
	}
	
	public void atualizarRespostaRodada(RodadaDomain rodada) {
		rodadaPortOut.persisteRodada(rodada);
	}
	
	public void atualizarRespostasRodadas(List<RodadaDomain> rodadas) {
		rodadaPortOut.persistirRodadas(rodadas);
	}
}
