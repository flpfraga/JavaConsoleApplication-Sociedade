package com.fraga.bdmg.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.br.CNPJ;

import lombok.Data;
import lombok.experimental.FieldNameConstants.Exclude;

@Data
public class PessoaJuridica extends Pessoa{

	private static final long serialVersionUID = 1L;
	
	@CNPJ(message=("CNPJ invalido. Digit 14 numeros sem pontos, espacos ou tracos."))
	private String cnpj;
	
	private List<Pessoa> estruturaSocietaria;
	
	public PessoaJuridica() {
		super();
		estruturaSocietaria = new ArrayList<>();
	}

	public PessoaJuridica(String nome, List<BemImovel> bensImoveis, String cnpj,
			List<Pessoa> estruturaSocietaria) {
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
		estruturaSocietaria.forEach(e -> e.print());
		System.out.println();
	}
	
	public void printNomeComCnpj() {
		print();
		System.out.println("CNPJ "+ this.cnpj);
	}
	
	public void addSocio(Pessoa pessoa) {
		estruturaSocietaria.add(pessoa);
	}
}
