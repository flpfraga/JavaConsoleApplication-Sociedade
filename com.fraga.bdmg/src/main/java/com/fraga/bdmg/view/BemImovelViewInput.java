package com.fraga.bdmg.view;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.fraga.bdmg.application.ViewInput;
import com.fraga.bdmg.model.BemImovel;

public class BemImovelViewInput implements ViewInput<BemImovel>{

	public BemImovel formIntput() throws IOException, InputMismatchException {
		Scanner ler = new Scanner(System.in).useLocale(Locale.ENGLISH);
		BemImovel bi = new BemImovel();
		System.out.println("Cadastro de Bens Imoveis:");
		System.out.println("Digite o nome:");
		String nome = ler.nextLine();
		System.out.println("Digite o Valor:");
		String valor = ler.nextLine();
		bi.setNome(nome);
		bi.setValor(Double.parseDouble(valor));
		return bi;
	}
	
	public BemImovel formIntputAlterar(BemImovel bemImovel) throws IOException, InputMismatchException, NumberFormatException{
		Scanner ler = new Scanner(System.in);
		ler.useLocale(Locale.ENGLISH);
		System.out.println("Alterar Bem Imovel. Digite o novo valor nos campos que deseja alterar.");
		System.out.println("Alterar o nome:");
		String nome = ler.nextLine();
		System.out.println("Alterar o Valor:");
		String valorString = ler.nextLine();
		BemImovel novo = new BemImovel();
		if (!nome.equals("") && !nome.equals(bemImovel.getNome())) {
			novo.setNome(nome);
		}
		else {
			novo.setNome(bemImovel.getNome());
		}
		if (!valorString.equals("") && !valorString.equals(bemImovel.getValor()+"")) {
			
			novo.setValor(Double.parseDouble(valorString));
		}
		else {
			novo.setValor(bemImovel.getValor());
		}
		
		return novo;
	}
	
	public void listarTodos(List<BemImovel> lista) {
		System.out.println("Listagem de Bens Imoveis Cadastrados:");
		if (lista.size()<= 0) {
			System.out.println("Ainda nao ha bens imoveis cadastrados para esta pessoa/empresa.");
		}
		lista.forEach(b -> b.print());
	}
	
	public Long selecionar(List<BemImovel> lista) {
		lista.forEach(b -> b.print());
		System.out.println("Digite o id do Bem Imovel para alterar/deletar.");
		Scanner ler = new Scanner(System.in);
		return ler.nextLong();
	} 



	public void erroValidacao(Set<ConstraintViolation<BemImovel>> violations) {
		for (ConstraintViolation<BemImovel> violation : violations) {
			System.out.println(violation.getMessage());
		}
	}

	public void menu() {
		System.out.println("Menu Bens Imoveis");
		System.out.println("Selecione uma opcao e tecle:");
		System.out.println("1 - Adicionar Novo Bens Imoveis");
		System.out.println("2 - Mostrar Bens Imoveis");
		System.out.println("3 - Alterar Bens Imoveis");
		System.out.println("4 - Deletar Bem Imovel");
		System.out.println("0 - Sair");
		System.out.println();
	}

	public void fechaMsg() {
		System.out.println("Voltando para menu de pessoas/empresas.");
	}

	public void opcaoInvalidaMsg() {
		System.out.println("Opcao Invalida. Digite um numero entre 0 e 3.");
	}

	public void ioExceptionMsg(String msg) {
		System.out.println(msg);
	}

	public void alertaMsg(String msg) {
		System.out.println(msg);
	}

}
