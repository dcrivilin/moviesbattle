package br.com.adatech.moviesbattle.adapter.in.http.controller.model.response;

public class RankingModelResponse {

	private Long posicao;
	private Long idJogador;
	private String nomeJogador;
	private Integer pontuacao;
	
	public Long getPosicao() {
		return posicao;
	}
	public void setPosicao(Long posicao) {
		this.posicao = posicao;
	}
	public Long getIdJogador() {
		return idJogador;
	}
	public void setIdJogador(Long idJogador) {
		this.idJogador = idJogador;
	}
	public String getNomeJogador() {
		return nomeJogador;
	}
	public void setNomeJogador(String nomeJogador) {
		this.nomeJogador = nomeJogador;
	}
	public Integer getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}
}