package com.fraga.bdmg.application;

import java.util.List;

import com.fraga.bdmg.model.Pessoa;
import com.fraga.bdmg.view.EstruturaSocietariaView;

public interface View<D> {
	
	static View<Pessoa> criaEstruturaSocietaria() {
		return new EstruturaSocietariaView();
	}
		
	void listarTodos(List<D> lista);
	
	Long selecionar(List<D> lista);
	
	void menu();
	
	void fechaMsg();
	
	void opcaoInvalidaMsg();
	
	void ioExceptionMsg(String msg);
	
	void alertaMsg(String msg);
	
}