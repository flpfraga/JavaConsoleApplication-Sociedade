package com.fraga.bdmg.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message = "Nome não poder nulo.")
	private String nome;

	private List<BemImovel> bensImoveis;

	public Pessoa() {
		bensImoveis = new ArrayList<>();
	}

	public Pessoa(String nome, List<BemImovel> bensImoveis) {
		super();
		this.nome = nome;
		this.bensImoveis = bensImoveis;
	}

	public void addBemImovel(BemImovel bemImovel) {
		bensImoveis.add(bemImovel);
	}

	public void print() {
		System.out.println("ID: " + this.id);
		System.out.println("Nome: " + this.nome);
	}
	public void printBensImoveis() {
		bensImoveis.forEach(b -> b.print());
	}

}
