package br.com.adatech.moviesbattle.application.domain;

public class RodadaDomain{

	private Long id;
	private String descricao;
	private QuizDomain quiz;
	private FilmeDomain primeiroFilme;
	private FilmeDomain segundoFilme;
	private FilmeDomain filmeEscolhido;
	private RespostaDomain resposta;

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

	public QuizDomain getQuiz() {
		return quiz;
	}

	public void setQuiz(QuizDomain quiz) {
		this.quiz = quiz;
	}

	public FilmeDomain getPrimeiroFilme() {
		return primeiroFilme;
	}

	public void setPrimeiroFilme(FilmeDomain primeiroFilme) {
		this.primeiroFilme = primeiroFilme;
	}

	public FilmeDomain getSegundoFilme() {
		return segundoFilme;
	}

	public void setSegundoFilme(FilmeDomain segundoFilme) {
		this.segundoFilme = segundoFilme;
	}
	
	public FilmeDomain getFilmeEscolhido() {
		return filmeEscolhido;
	}

	public void setFilmeEscolhido(FilmeDomain filmeEscolhido) {
		this.filmeEscolhido = filmeEscolhido;
	}

	public RespostaDomain getResposta() {
		return resposta;
	}

	public void setResposta(RespostaDomain resposta) {
		this.resposta = resposta;
	}
}
