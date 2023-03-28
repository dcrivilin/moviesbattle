package br.com.adatech.moviesbattle.adapter.in.http.controller.model.request;

import javax.validation.constraints.NotNull;

public class QuizModelRequest {
	
	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}