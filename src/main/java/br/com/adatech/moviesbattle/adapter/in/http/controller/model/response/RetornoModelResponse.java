package br.com.adatech.moviesbattle.adapter.in.http.controller.model.response;

public class RetornoModelResponse {
	
	private String mensagem;
	
	public RetornoModelResponse(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}