package com.fraga.bdmg.view;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.fraga.bdmg.application.ViewInput;
import com.fraga.bdmg.model.PessoaFisica;

public class PessoaFisicaViewInput implements ViewInput<PessoaFisica> {

	public PessoaFisica formIntput() throws IOException {
		Scanner ler = new Scanner(System.in);
		PessoaFisica pf = new PessoaFisica();
		System.out.println("Cadastro de Pessoa Fisica:");
		System.out.println("Digite o nome:");
		String nome = ler.nextLine();
		System.out.println("Digite o CPF:");
		String cpf = ler.nextLine();
		pf.setNome(nome);
		pf.setCpf(cpf);
		return pf;
	}

	public PessoaFisica formIntputAlterar(PessoaFisica pessoaFisica) throws IOException {
		Scanner ler = new Scanner(System.in);
		System.out.println("Alterar Pessoa. Digite o novo valor nos campos que deseja alterar.");
		System.out.println("Alterar o nome:");
		String nome = ler.nextLine();
		System.out.println("Alterar o CPF:");
		String cpf = ler.nextLine();
		PessoaFisica nova = new PessoaFisica();
		if (!nome.equals("") && !nome.equals(pessoaFisica.getNome())) {
			System.out.println("Alterou o nome:");
			nova.setNome(nome);
		} else {
			nova.setNome(pessoaFisica.getNome());
		}

		if (!cpf.equals("") && !cpf.equals(pessoaFisica.getCpf())) {
			System.out.println("Alterar o cpf:");
			nova.setCpf(cpf);
		} else {
			nova.setCpf(pessoaFisica.getCpf());
		}
		nova.setId(pessoaFisica.getId());
		System.out.println("nova final");
		nova.printPessoaFisica();
		return nova;
	}

	public void listarTodos(List<PessoaFisica> lista) {
		System.out.println("Listagem de Pessoas Fisicas Cadastrados:");
		if (lista.size() <= 0) {
			System.out.println("Ainda nao ha pessoas fisicas cadastradas.");
		}
		lista.forEach(p -> p.printPessoaFisica());
	}

	public Long selecionar(List<PessoaFisica> lista) {
		lista.forEach(p -> p.printPessoaComCpf());
		System.out.println("Digite o id da pessoa a ser alterada/deletada.");
		Scanner ler = new Scanner(System.in);
		return ler.nextLong();
	}

	public void erroValidacao(Set<ConstraintViolation<PessoaFisica>> violations) {
		for (ConstraintViolation<PessoaFisica> violation : violations) {
			System.out.println(violation.getMessage());
		}
	}

	public void menu() {
		System.out.println("Menu Pessoa Fisica");
		System.out.println("Selecione uma opcao e tecle:");
		System.out.println("1 - Cadastrar Pessoa Fisica");
		System.out.println("2 - Mostrar Pessoas Fisicas");
		System.out.println("3 - Alterar Pessoa Fisica");
		System.out.println("4 - Delete Pessoa Fisica");
		System.out.println("0 - Sair");
		System.out.println();
	}

	public void fechaMsg() {
		System.out.println("Voltando para menu principal");
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
