package com.fraga.bdmg.model;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BemImovel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull(message =("O nome da imovel não pode ser nulo"))
	private String nome;
	
	@Min(value = (1) , message = ("Valor deve ser maior que R$ 1.00"))
	@Digits(fraction = 2, integer = 7, message = ("O valor inteiro maximo pode ter ate 7 digitos. Valor francionario deve ter apenas 2 digitos"))
	private Double valor;
	
	public BemImovel() {
		// TODO Auto-generated constructor stub
	}
	
	public BemImovel(Long id, String nome, Double valor) {
		this.id = id;
		this.nome = nome;
		this.valor = valor;
	}
	public BemImovel(Long id, String nome, Double valor, Long idPessoa) {
		this.id = id;
		this.nome = nome;
		this.valor = valor;
	}
	
	public void print() {
		System.out.println("Bens Imoveis");
		System.out.println("ID:  " + this.id);
		System.out.println("Nome:  " + this.nome);
		System.out.println("Valor: " + this.valor);
		System.out.println();
	}

	
}
