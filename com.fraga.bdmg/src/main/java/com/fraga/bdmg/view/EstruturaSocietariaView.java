package com.fraga.bdmg.view;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.fraga.bdmg.application.View;
import com.fraga.bdmg.data.model.Pessoa;

public class EstruturaSocietariaView implements View<Pessoa>{

	
	
	public void ioExceptionMsg(String msg) {
		System.out.println(msg);
	}
	
	public void alertaMsg(String msg) {
		System.out.println(msg);
	}
	
	public void menu() {
		System.out.println("Menu Socios");
		System.out.println("Selecione uma opcao e tecle:");
		System.out.println("1 - Adicionar Socio");
		System.out.println("2 - Mostrar Socios ");
		System.out.println("3 - Deletar Socio");
		System.out.println("0 - Sair");
		System.out.println();
	}
	public void fechaMsg() {
		System.out.println("Voltando para menu de empresas.");
	}

	@Override
	public void listarTodos(List<Pessoa> lista) {
		lista.forEach(p -> p.print());
	}

	@Override
	public void opcaoInvalidaMsg() {
		System.out.println("Opcao Invalida. Digite um numero entre 0 e 3.");
	}


}
