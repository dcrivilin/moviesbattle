package br.com.adatech.moviesbattle.adapter.in.http.controller.model.response;

public class QuizModelResponse {
	
	private Long id;
	private String situacao;
	private Integer idJogador;
	private Integer quantidadeRodada;
	
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
	public Integer getIdJogador() {
		return idJogador;
	}
	public void setIdJogador(Integer idJogador) {
		this.idJogador = idJogador;
	}
	public Integer getQuantidadeRodada() {
		return quantidadeRodada;
	}
	public void setQuantidadeRodada(Integer quantidadeRodada) {
		this.quantidadeRodada = quantidadeRodada;
	}
}