package com.fraga.bdmg.populate;

import java.util.ArrayList;
import java.util.List;

import com.fraga.bdmg.model.BemImovel;
import com.fraga.bdmg.model.PessoaFisica;
import com.fraga.bdmg.model.PessoaJuridica;

public class PessoaJuridicaPopulate {

	public static List<PessoaJuridica> populate(List<PessoaFisica> pfs, List<BemImovel> bensImoveis) {
		
		List<PessoaJuridica> pjs = new ArrayList<>();
		
		PessoaJuridica pj = new PessoaJuridica();
		pj.setId(2L);
		pj.setNome("Oracle");
		pj.setCnpj("11031050000152");
		pj.addBemImovel(bensImoveis.get(3));
		pj.addBemImovel(bensImoveis.get(4));
		pj.addSocio(pfs.get(0));
		pj.addSocio(pj);
		pjs.add(pj);
		
		pj = new PessoaJuridica();
		pj.setId(3L);
		pj.setNome("Google");
		pj.setCnpj("11031050000152");
		pj.addBemImovel(bensImoveis.get(5));
		pj.addBemImovel(bensImoveis.get(6));
		pj.addBemImovel(bensImoveis.get(7));
		pj.addSocio(pjs.get(0));
		pj.addSocio(pj);
		pjs.add(pj);
		
		pj = new PessoaJuridica();
		pj.setId(4L);
		pj.setNome("Microsoft");
		pj.setCnpj("11031050000152");
		pj.addBemImovel(bensImoveis.get(8));
		pj.addSocio(pjs.get(0));
		pj.addSocio(pjs.get(1));
		pjs.add(pj);
		
		
		return pjs;
	}
	
}
