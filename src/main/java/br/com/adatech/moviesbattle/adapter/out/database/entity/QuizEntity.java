package br.com.adatech.moviesbattle.adapter.out.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "quiz")
public class QuizEntity implements Serializable{

	private static final long serialVersionUID = 7252560098616924473L;
	
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "situacao")
	private String situacao;
	
	@ManyToOne
	private JogadorEntity jogador;
	
	@Column(name = "pontuacao")
	private Integer pontuacao;
	
	@Column(name = "qtd_acerto")
	private Integer quantidadeAcerto;
	
	@Column(name = "qtd_rodada")
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

	public JogadorEntity getJogador() {
		return jogador;
	}

	public void setJogador(JogadorEntity jogador) {
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
}
