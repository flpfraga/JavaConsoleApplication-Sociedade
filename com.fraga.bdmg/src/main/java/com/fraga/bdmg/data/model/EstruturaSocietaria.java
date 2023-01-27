package com.fraga.bdmg.data.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class EstruturaSocietaria {

	private List<PessoaFisica> sociosFisicos;
	
	private List<PessoaJuridica> sociosJuridicos;
	
	private Boolean autoSociedade;
	
	public EstruturaSocietaria() {
		this.sociosFisicos = new ArrayList<>();
		this.sociosJuridicos = new ArrayList<>();
		this.autoSociedade = false;
	}
	
	public void addSocioFisico(PessoaFisica pessoaFisica) {
		this.sociosFisicos.add(pessoaFisica);
	}
	public void addSocioJuridico(PessoaJuridica pessoaJuridica) {
		this.sociosJuridicos.add(pessoaJuridica);
	}
	
	public void print() {
		System.out.println("Socios Fisicos");
		sociosFisicos.forEach(s -> s.print());
		System.out.println("Socios Juridicos");
		sociosJuridicos.forEach(s -> s.print());
	}
	public String isValid() {
		if (sociosFisicos.isEmpty() && sociosJuridicos.isEmpty()) {
			
			return "Deve-se ter no minimo uma pessoa física ou juridica na estrutura societaria de uma empresa.";
		}
		return "";
	}
	
}
