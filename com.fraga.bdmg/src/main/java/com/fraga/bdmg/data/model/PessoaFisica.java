package com.fraga.bdmg.data.model;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
public class PessoaFisica extends Pessoa{

	private static final long serialVersionUID = 1L;
	
	@CPF(message="CPF invalido. Numero informado e invalido como CPF.")
	private String cpf;
	
	public PessoaFisica() {
		super();
	}

	public PessoaFisica(String nome, List<BemImovel> bensImoveis, String cpf) {
		super(nome, bensImoveis);
		this.cpf = cpf;
	}
	
	public void printPessoaFisica() {
		System.out.println("Pessoa Fisica");
		print();
		System.out.println("CPF: " + this.cpf);
		System.out.println("Bens e Imoveis de " + this.getNome());
		printBensImoveis();
		System.out.println();
	}
	
	public void printPessoaComCpf() {
		print();
		System.out.println("CPF: " + this.cpf);
	}

}
