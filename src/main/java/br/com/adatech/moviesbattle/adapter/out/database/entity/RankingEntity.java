package br.com.adatech.moviesbattle.adapter.out.database.entity;

public class RankingEntity {

	private Long posicao;
	private Long idJogador;
	private Integer pontuacao;
	
	public RankingEntity(Long posicao, Long idJogador, Integer pontuacao) {
		this.posicao = posicao;
		this.idJogador = idJogador;
		this.pontuacao = pontuacao;
	}
	public Long getPosicao() {
		return posicao;
	}
	public void setPosicao(Long id) {
		this.posicao = id;
	}
	public Long getIdJogador() {
		return idJogador;
	}
	public void setIdJogador(Long idJogador) {
		this.idJogador = idJogador;
	}
	public Integer getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}
}