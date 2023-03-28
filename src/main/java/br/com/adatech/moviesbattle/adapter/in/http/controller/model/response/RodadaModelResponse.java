package br.com.adatech.moviesbattle.adapter.in.http.controller.model.response;

public class RodadaModelResponse {
	
	private Long id;
	private String descricao;
	private Long idQuiz;
	private FilmeModelResponse primeiroFilme;
	private FilmeModelResponse segundoFilme;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String pergunta) {
		this.descricao = pergunta;
	}
	public Long getIdQuiz() {
		return idQuiz;
	}
	public void setIdQuiz(Long idQuiz) {
		this.idQuiz = idQuiz;
	}
	public FilmeModelResponse getPrimeiroFilme() {
		return primeiroFilme;
	}
	public void setPrimeiroFilme(FilmeModelResponse primeiroFilme) {
		this.primeiroFilme = primeiroFilme;
	}
	public FilmeModelResponse getSegundoFilme() {
		return segundoFilme;
	}
	public void setSegundoFilme(FilmeModelResponse segundoFilme) {
		this.segundoFilme = segundoFilme;
	}
}