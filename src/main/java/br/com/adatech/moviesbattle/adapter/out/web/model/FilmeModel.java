package br.com.adatech.moviesbattle.adapter.out.web.model;

public class FilmeModel {
	
	private String nome;
	private Double notaAvaliacao;
	private Long quantidadeVotos;
	private Long pontuacao;

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

	public Long getQuantidadeVotos() {
		return quantidadeVotos;
	}

	public void setQuantidadeVotos(Long quantidadeVotos) {
		this.quantidadeVotos = quantidadeVotos;
	}

	public Long getPontuacao() {
		pontuacao = (long) (quantidadeVotos * notaAvaliacao);
		return pontuacao;
	}
}
