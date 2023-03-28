package br.com.adatech.moviesbattle.application.domain;

import java.util.Objects;

public class FilmeDomain implements Comparable<FilmeDomain>{
	
	private Long id;
	private String nome;
	private Double notaAvaliacao;
	private Integer quantidadeVotos;
	private Long pontuacao;

	public FilmeDomain(Long id, String nome, Double notaAvaliacao, Integer quantidadeVotos, Long pontuacao) {
		this.id = id;
		this.nome = nome;
		this.notaAvaliacao = notaAvaliacao;
		this.quantidadeVotos = quantidadeVotos;
		this.pontuacao = pontuacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getNotaAvaliacao() {
		return notaAvaliacao;
	}

	public void setNotaAvaliacao(Double notaAvaliacao) {
		this.notaAvaliacao = notaAvaliacao;
	}

	public Integer getQuantidadeVotos() {
		return quantidadeVotos;
	}

	public void setQuantidadeVotos(Integer quantidadeVotos) {
		this.quantidadeVotos = quantidadeVotos;
	}

	public Long getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Long pontuacao) {
		this.pontuacao = pontuacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmeDomain other = (FilmeDomain) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int compareTo(FilmeDomain o) {
		return Long.compare(getId(), o.getId());
	}
	
}
