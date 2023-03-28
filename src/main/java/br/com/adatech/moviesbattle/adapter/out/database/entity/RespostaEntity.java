package br.com.adatech.moviesbattle.adapter.out.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resposta")
public class RespostaEntity implements Serializable{

	private static final long serialVersionUID = 7885554429395361330L;

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;

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
}
