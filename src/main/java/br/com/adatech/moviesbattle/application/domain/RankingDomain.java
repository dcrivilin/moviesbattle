package br.com.adatech.moviesbattle.application.domain;

public class RankingDomain {
	
	private Long posicao;
	private JogadorDomain jogador;
	private Integer pontuacao;
	
	public Long getPosicao() {
		return posicao;
	}
	public void setPosicao(Long id) {
		this.posicao = id;
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
}
