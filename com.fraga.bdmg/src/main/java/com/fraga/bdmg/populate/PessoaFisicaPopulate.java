package com.fraga.bdmg.populate;

import java.util.ArrayList;
import java.util.List;

import com.fraga.bdmg.data.model.BemImovel;
import com.fraga.bdmg.data.model.PessoaFisica;

public class PessoaFisicaPopulate {


	public static List<PessoaFisica> populate(List<BemImovel> bensImoveis){
		List<PessoaFisica> pessoas = new ArrayList<>();
		
		PessoaFisica pf = new PessoaFisica();
		pf.setId(0L);
		pf.setNome("Felipe Fraga");
		pf.setCpf("08151614684");
		pf.addBemImovel(bensImoveis.get(0));
		pessoas.add(pf);
		
		pf = new PessoaFisica();
		pf.setId(1L);
		pf.setNome("Maria Alcantara");
		pf.setCpf("08151614684");
		pf.addBemImovel(bensImoveis.get(1));
		pessoas.add(pf);
		
		return pessoas;
		
	}
	
}
