package br.com.adatech.moviesbattle.application.service.rodada;

import org.springframework.stereotype.Service;

import br.com.adatech.moviesbattle.application.domain.RodadaDomain;
import br.com.adatech.moviesbattle.application.port.out.RodadaPortOut;

@Service
public class BuscarRodada {

	private final RodadaPortOut rodadaPortOut;

	public BuscarRodada(RodadaPortOut rodadaPortOut) {
		this.rodadaPortOut = rodadaPortOut;
	}
	
	public RodadaDomain buscarPorId(Long id) {
		return rodadaPortOut.buscarRodadaPorId(id);
	}
}
