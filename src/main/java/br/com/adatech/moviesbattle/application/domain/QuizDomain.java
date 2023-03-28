package br.com.adatech.moviesbattle.application.domain;

import java.io.Serializable;
import java.util.List;

public class QuizDomain implements Serializable{

	private static final long serialVersionUID = -2498100324108320295L;
	
	private Long id;
	private String situacao;
	private JogadorDomain jogador;
	private Integer pontuacao;
	private Integer quantidadeAcerto;
	private Integer quantidadeRodada;
    private List<RodadaDomain> rodadas;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public JogadorDomain getJogador() {
		return jogador;
	}

	public void setJogador(JogadorDomain jogador) {
		this.jogador = jogador;
	}

	public Integer getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Integer getQuantidadeAcerto() {
		return quantidadeAcerto;
	}

	public void setQuantidadeAcerto(Integer quantidadeAcerto) {
		this.quantidadeAcerto = quantidadeAcerto;
	}

	public Integer getQuantidadeRodada() {
		return quantidadeRodada;
	}

	public void setQuantidadeRodada(Integer quantidadeRodada) {
		this.quantidadeRodada = quantidadeRodada;
	}

	public List<RodadaDomain> getRodadas() {
		return rodadas;
	}

	public void setRodadas(List<RodadaDomain> rodadas) {
		this.rodadas = rodadas;
	}
}
