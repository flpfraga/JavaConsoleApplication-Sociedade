package com.fraga.bdmg.data.model;

import java.util.List;

import org.hibernate.validator.constraints.br.CNPJ;

import lombok.Data;

@Data
public class PessoaJuridica extends Pessoa{

	private static final long serialVersionUID = 1L;
	
	@CNPJ(message=("CNPJ invalido. Digit 14 numeros sem pontos, espacos ou tracos."))
	private String cnpj;
	
	private EstruturaSocietaria estruturaSocietaria;
	
	public PessoaJuridica() {
		super();
		
	}

	public PessoaJuridica(String nome, List<BemImovel> bensImoveis, String cnpj,
			EstruturaSocietaria estruturaSocietaria) {
		super(nome, bensImoveis);
		this.cnpj = cnpj;
		this.estruturaSocietaria = estruturaSocietaria;
	}

	public void printPessoaJuridica() {
		System.out.println("Pessoa Juridica");
		print();
		System.out.println("CNPJ "+ this.cnpj);
		System.out.println("Bens e Imoveis de " + this.getNome());
		printBensImoveis();
		System.out.println("Socios de " + this.getNome());
		estruturaSocietaria.print();
		System.out.println();
	}
	
	public void printNomeComCnpj() {
		print();
		System.out.println("CNPJ "+ this.cnpj);
	}
	
	
}
