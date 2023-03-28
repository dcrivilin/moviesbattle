package br.com.adatech.moviesbattle.adapter.in.http.controller.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class RodadaModelRequest {

	@NotNull
	private Long id;
	
	@NotNull
	private Long idQuiz;
	
	@Valid
	private FilmeModelRequest filmeEscolhido;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdQuiz() {
		return idQuiz;
	}
	public void setIdQuiz(Long idQuiz) {
		this.idQuiz = idQuiz;
	}
	public FilmeModelRequest getFilmeEscolhido() {
		return filmeEscolhido;
	}
	public void setFilmeEscolhido(FilmeModelRequest filmeEscolhido) {
		this.filmeEscolhido = filmeEscolhido;
	}
}
