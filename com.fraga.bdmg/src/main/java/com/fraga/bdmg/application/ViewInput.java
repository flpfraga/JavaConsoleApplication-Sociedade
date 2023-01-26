package com.fraga.bdmg.application;

import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.fraga.bdmg.model.BemImovel;
import com.fraga.bdmg.model.PessoaFisica;
import com.fraga.bdmg.model.PessoaJuridica;
import com.fraga.bdmg.view.BemImovelViewInput;
import com.fraga.bdmg.view.PessoaFisicaViewInput;
import com.fraga.bdmg.view.PessoaJuridicaViewInput;

public interface ViewInput<D> extends View<D>{
	
	static ViewInput<BemImovel> criaBemImovel() {
		return new BemImovelViewInput();
	}
	static ViewInput<PessoaFisica> criaPessoaFisica() {
		return new PessoaFisicaViewInput();
	}
	static ViewInput<PessoaJuridica> criaPessoaJuridica() {
		return new PessoaJuridicaViewInput();
	}

	D formIntput() throws IOException;
	
	D formIntputAlterar(D entity) throws IOException;
	
	void erroValidacao(Set<ConstraintViolation<D>> violations);
	
	
	
}