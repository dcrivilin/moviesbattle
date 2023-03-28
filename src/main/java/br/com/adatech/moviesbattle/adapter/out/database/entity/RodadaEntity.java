package br.com.adatech.moviesbattle.adapter.out.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rodada")
public class RodadaEntity implements Serializable{

	private static final long serialVersionUID = -6507056866710861472L;

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@ManyToOne
	private QuizEntity quiz;
	
	@ManyToOne
	private FilmeEntity primeiroFilme;
	
	@ManyToOne
	private FilmeEntity segundoFilme;
	
	@ManyToOne
	private RespostaEntity resposta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public QuizEntity getQuiz() {
		return quiz;
	}

	public void setQuiz(QuizEntity quiz) {
		this.quiz = quiz;
	}

	public FilmeEntity getPrimeiroFilme() {
		return primeiroFilme;
	}

	public void setPrimeiroFilme(FilmeEntity primeiroFilme) {
		this.primeiroFilme = primeiroFilme;
	}

	public FilmeEntity getSegundoFilme() {
		return segundoFilme;
	}

	public void setSegundoFilme(FilmeEntity segundoFilme) {
		this.segundoFilme = segundoFilme;
	}

	public RespostaEntity getResposta() {
		return resposta;
	}

	public void setResposta(RespostaEntity resposta) {
		this.resposta = resposta;
	}
}
