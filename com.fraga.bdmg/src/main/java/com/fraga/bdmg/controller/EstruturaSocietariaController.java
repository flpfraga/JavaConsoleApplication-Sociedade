package com.fraga.bdmg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.fraga.bdmg.application.View;
import com.fraga.bdmg.model.Pessoa;
import com.fraga.bdmg.model.PessoaFisica;
import com.fraga.bdmg.model.PessoaJuridica;

public class EstruturaSocietariaController extends ControllerBasico<Pessoa>{

	private PessoaJuridica pessoaJuridica;
	
	private View<Pessoa> view;
	
	private List<PessoaJuridica> pjs;
	
	private List<PessoaFisica> pfs;
	
	public EstruturaSocietariaController() {
		view = View.criaEstruturaSocietaria();
	}
	
	@Override
	public void consoleInicio() {
		int entrada = 0;
		do {
			view.menu();
			try {
				entrada = lerEntrada();
				switch (entrada) {
				case 1:
					cadastrar();
					break;
				case 2:
					mostrarTodos();
					break;
				case 3:
					deletar();
					break;
				case 0:
					view.fechaMsg();
					break;
				default:
					view.alertaMsg("Opcao invalida. Digite um numero de 0 a 4.");
					break;
				}
			} catch (IOException e) {
				view.ioExceptionMsg("Entrada de dados invalida." + e);
			}

		} while (entrada != 0);
	}

	@Override
	public void cadastrar() {
		Scanner ler = new Scanner(System.in);
		String entrada = "";
		List<Pessoa> naoSocios = concatenarListas();
		
		while (!entrada.equalsIgnoreCase("n")) {
			naoSocios = removerSociosListaNaoSocios(naoSocios);
			Long idSelecionado = view.selecionar(naoSocios);
			Optional<Pessoa> pessoa = naoSocios.stream().filter(s -> s.getId()==idSelecionado).findFirst();
			if (!pessoa.isEmpty()) {
				naoSocios.remove(pessoa.get());
				lista.add(pessoa.get());
			} else {
				view.alertaMsg("O ID informado não corresponde a nenhuma empresa ou pessoa.");
			}
			view.alertaMsg("Deseja continuar a incluir socios? Tecle n para sair. Tecle qualquer para continuar.");
			try {
				entrada = ler.nextLine();
			} catch (Exception e) {
				view.ioExceptionMsg("Erro com o valor digitado. Repita a operacao.");
				break;
			}
			
		}
	}
	
	@Override
	public void mostrarTodos() {
		view.listarTodos(lista);
	}
	
	public void adicionar() {
		Scanner ler = new Scanner(System.in);
		String entrada = "";
		view.alertaMsg("Deseja cadastrar socios para esta empresa? Tecle N para sair. Tecle qualquer para continuar.");
		entrada = ler.nextLine();
		if (!entrada.equalsIgnoreCase("n")) {
			cadastrar();
		}
	}
	
	public void alterarSocios() {
		mostrarTodos();
		consoleInicio();
	}

	@Override
	public void deletar() {
		Long idSelecionado = view.selecionar(lista);
		
		Optional<Pessoa> pessoa = lista.stream().filter(s -> s.getId()==idSelecionado).findFirst();
		if (!pessoa.isEmpty()) {
			lista.remove(pessoa.get());
			
		} else {
			System.out.println("O ID informado não corresponde a nenhuma empresa ou pessoa.");
		}
	}
	public List<Pessoa> concatenarListas() {
		
		List<Pessoa> pessoas = new ArrayList<>();
		pessoas.add(this.pessoaJuridica);
		for (PessoaFisica p : pfs) {
			pessoas.add(p);
		}
		for (PessoaJuridica p : pjs) {
			pessoas.add(p);
		}
		
		return removerElementosRepetidos(pessoas);
	}
	
	public List<Pessoa> removerElementosRepetidos(List<Pessoa> naoSocios){
		List<Pessoa> pessoas = new ArrayList<>();
		
		for (Pessoa p : naoSocios) {
			if (!pessoas.contains(p)) {
				pessoas.add(p);
			}
		}
		return pessoas;
	}
	
	public List<Pessoa> removerSociosListaNaoSocios(List<Pessoa> naoSocios){

		for (Pessoa p : lista) {

			naoSocios.remove(p);
		}
		
		return naoSocios;
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}
	
	public List<PessoaJuridica> getPjs() {
		return pjs;
	}

	public void setPjs(List<PessoaJuridica> pjs) {
		this.pjs = pjs;
	}

	public List<PessoaFisica> getPfs() {
		return pfs;
	}

	public void setPfs(List<PessoaFisica> pfs) {
		this.pfs = pfs;
	}
	
}
