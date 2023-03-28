package br.com.adatech.moviesbattle.application.service.rodada;

import org.springframework.stereotype.Service;

import br.com.adatech.moviesbattle.application.domain.FilmeDomain;
import br.com.adatech.moviesbattle.application.domain.RodadaDomain;
import br.com.adatech.moviesbattle.application.exception.ServiceException;

@Service
public class VerificaRespostaCorreta {
	
	public boolean execute(RodadaDomain rodadaRespondida, RodadaDomain rodadaBase) {
		
		FilmeDomain a = rodadaBase.getPrimeiroFilme();
        FilmeDomain b = rodadaBase.getSegundoFilme();
        FilmeDomain resposta = rodadaRespondida.getFilmeEscolhido();
		
		if (a.getPontuacao() > b.getPontuacao()) {
			if (a.equals(resposta))
				return true;
			if (b.equals(resposta))
				return false;
		} else {
			if (b.equals(resposta))
				return true;
			if (a.equals(resposta))
				return false;
		}
		throw new ServiceException("Resposta escolhida fora do range de filmes exibidos.");
	}
}
