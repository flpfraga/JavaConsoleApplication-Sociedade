package com.fraga.bdmg.view;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.fraga.bdmg.application.ViewInput;
import com.fraga.bdmg.model.PessoaFisica;
import com.fraga.bdmg.model.PessoaJuridica;

public class PessoaJuridicaViewInput implements ViewInput<PessoaJuridica> {

	public PessoaJuridica formIntput() throws IOException {
		Scanner ler = new Scanner(System.in);
		PessoaJuridica pj = new PessoaJuridica();
		System.out.println("Cadastro de Empresa:");
		System.out.println("Digite o nome:");
		String nome = ler.nextLine();
		System.out.println("Digite o CNPJ:");
		String cnpj = ler.nextLine();
		pj.setNome(nome);
		pj.setCnpj(cnpj);
		return pj;
	}

	public PessoaJuridica formIntputAlterar(PessoaJuridica pessoaJuridica) throws IOException {
		Scanner ler = new Scanner(System.in);
		System.out.println("Alterar Empresa. Digite o novo valor nos campos que deseja alterar.");
		System.out.println("Alterar o nome:");
		String nome = ler.nextLine();
		System.out.println("Alterar o CNPJ:");
		String cnpj = ler.nextLine();
		PessoaJuridica nova = new PessoaJuridica();
		if (!nome.equals("") && !nome.equals(pessoaJuridica.getNome())) {
			nova.setNome(nome);
		} else {
			nova.setNome(pessoaJuridica.getNome());
		}
		if (!cnpj.equals("") && !cnpj.equals(pessoaJuridica.getCnpj())) {
			nova.setCnpj(cnpj);
		} else {
			nova.setCnpj(pessoaJuridica.getCnpj());
		}
		return nova;
	}

	public void listarTodos(List<PessoaJuridica> lista) {
		System.out.println("Listagem de Empresas Cadastrados:");
		if (lista.size() <= 0) {
			System.out.println("Ainda nao ha empresas cadastradas.");
		}
		lista.forEach(p -> p.printPessoaJuridica());
	}

	public Long selecionar(List<PessoaJuridica> lista) {
		lista.forEach(p -> p.printNomeComCnpj());
		System.out.println("Digite o id da empresa para alterar/deletar.");
		Scanner ler = new Scanner(System.in);
		return ler.nextLong();
	}

	public void erroValidacao(Set<ConstraintViolation<PessoaJuridica>> violations) {
		for (ConstraintViolation<PessoaJuridica> violation : violations) {
			System.out.println(violation.getMessage());
		}
	}

	@Override
	public void menu() {
		System.out.println("Menu Empresas");
		System.out.println("Selecione uma opcao e tecle:");
		System.out.println("1 - Cadastrar Nova Empresa");
		System.out.println("2 - Mostrar Empresas");
		System.out.println("3 - Alterar Empresas");
		System.out.println("4 - Delete Empresa");
		System.out.println("5 - Comprometimento financeiro");
		System.out.println("0 - Sair");
		System.out.println();

	}

	public void fechaMsg() {
		System.out.println("Voltando para menu principal");
	}

	public void opcaoInvalidaMsg() {
		System.out.println("Opcao Invalida. Digite um numero entre 0 e 4.");
	}

	public void ioExceptionMsg(String msg) {
		System.out.println(msg);
	}

	public void alertaMsg(String msg) {
		System.out.println(msg);
	}
}
