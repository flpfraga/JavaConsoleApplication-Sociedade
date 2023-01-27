package com.fraga.bdmg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.fraga.bdmg.application.View;
import com.fraga.bdmg.data.model.EstruturaSocietaria;
import com.fraga.bdmg.data.model.Pessoa;
import com.fraga.bdmg.data.model.PessoaFisica;
import com.fraga.bdmg.data.model.PessoaJuridica;

public class EstruturaSocietariaController extends ControllerBasico<Pessoa> {

	private PessoaJuridica pessoaJuridica;

	private View<Pessoa> view;

	private List<PessoaFisica> pessoasFisicas;

	private List<PessoaJuridica> pessoasJuridicas;

	private EstruturaSocietaria estruturaSocietaria;

	public EstruturaSocietariaController() {
		view = View.criaEstruturaSocietaria();
		this.estruturaSocietaria = new EstruturaSocietaria();
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
		List<PessoaJuridica> naoSociosJuridico = listaNaoSociosJuridicos();
		List<PessoaFisica> naoSociosFisico = listaNaoSociosFisicos();
		if (naoSociosJuridico.size() <= 0 && naoSociosFisico.size() <= 0) {
			view.alertaMsg("Nao existem pessoas ou empresas para adicionar como socios.");
		} else {
			Long idSelecionado = selecionarSocios(naoSociosFisico, naoSociosJuridico);
			var entity = pessoasJuridicas.stream().filter(s -> s.getId() == idSelecionado).findFirst();

			if (!entity.isEmpty()) {
				naoSociosJuridico.remove(entity.get());
				this.estruturaSocietaria.getSociosJuridicos().add(entity.get());
				setAutoSociedade(entity.get());
			} else {
				view.alertaMsg("O ID informado não corresponde a nenhuma empresa ou pessoa.");
			}
			view.alertaMsg("Deseja continuar a incluir socios? Tecle n para sair. Tecle qualquer para continuar.");
			try {
				entrada = ler.nextLine();
				if (!entrada.equalsIgnoreCase("n")) {
					cadastro();
				}
			} catch (Exception e) {
				view.ioExceptionMsg("Erro com o valor digitado. Repita a operacao.");
			}
		}

	}

	private void setAutoSociedade(PessoaJuridica pessoaJuridica2) {
		if (pessoaJuridica.equals(pessoaJuridica2)) {
			this.estruturaSocietaria.setAutoSociedade(true);
		}
		this.estruturaSocietaria.setAutoSociedade(true);
	}

	public void cadastro() {

		Scanner ler = new Scanner(System.in);
		String entrada = "";

		while (!entrada.equalsIgnoreCase("n")) {
			List<PessoaJuridica> naoSociosJuridico = listaNaoSociosJuridicos();
			List<PessoaFisica> naoSociosFisico = listaNaoSociosFisicos();
			if (naoSociosJuridico.size() <= 0 && naoSociosFisico.size() <= 0) {
				view.alertaMsg("Nao existem pessoas ou empresas para adicionar como socios.");
				break;
			} 
			else {
				Long idSelecionado = selecionarSocios(naoSociosFisico, naoSociosJuridico);
				var entityJuridica = pessoasJuridicas.stream().filter(s -> s.getId() == idSelecionado).findFirst();
				var entityFisica = pessoasFisicas.stream().filter(s -> s.getId() == idSelecionado).findFirst();
				if (entityJuridica.isEmpty() && entityFisica.isEmpty()) {
					this.view.alertaMsg("O ID informado não corresponde a nenhuma empresa ou pessoa.");
				} else if (entityJuridica.isEmpty()) {
					naoSociosFisico.remove(entityFisica.get());
					this.estruturaSocietaria.getSociosFisicos().add(entityFisica.get());

				} else {
					naoSociosJuridico.remove(entityJuridica.get());
					this.estruturaSocietaria.getSociosJuridicos().add(entityJuridica.get());
					setAutoSociedade(entityJuridica.get());
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
	}

	public void adicionar() {
		Scanner ler = new Scanner(System.in);
		String entrada = "";
		view.alertaMsg("Deseja cadastrar socios para esta empresa? Tecle N para sair. Tecle qualquer para continuar.");
		entrada = ler.nextLine();
		if (!entrada.equalsIgnoreCase("n")) {
			cadastro();
		}
	}

	public void alterarSocios() {
		mostrarTodos();
		consoleInicio();
	}

	@Override
	public void deletar() {
		Long idSelecionado = selecionar();
		var entityJuridica = pessoasJuridicas.stream().filter(s -> s.getId() == idSelecionado).findFirst();
		var entityFisica = pessoasFisicas.stream().filter(s -> s.getId() == idSelecionado).findFirst();
		if (entityJuridica.isEmpty() && entityFisica.isEmpty()) {
			view.alertaMsg("O ID informado não corresponde a nenhuma empresa ou pessoa.");
		} else if (entityJuridica.isEmpty()) {
			this.estruturaSocietaria.getSociosFisicos().remove(entityFisica.get());
		} else {
			this.estruturaSocietaria.getSociosJuridicos().remove(entityJuridica.get());
			setAutoSociedade(entityJuridica.get());
		}

	}

	private List<PessoaFisica> listaNaoSociosFisicos() {

		List<PessoaFisica> naoSociosFisicos = new ArrayList<>();
		List<PessoaFisica> sociosFisicos = this.estruturaSocietaria.getSociosFisicos();

		for (PessoaFisica p : this.pessoasFisicas) {
			if (!sociosFisicos.contains(p) && !naoSociosFisicos.contains(p)) {
				naoSociosFisicos.add(p);
			}
		}
		return naoSociosFisicos;
	}

	private List<PessoaJuridica> listaNaoSociosJuridicos() {

		List<PessoaJuridica> naoSociosJuridicos = new ArrayList<>();
		List<PessoaJuridica> sociosJuridicos = estruturaSocietaria.getSociosJuridicos();

		for (PessoaJuridica p : this.pessoasJuridicas) {
			if (!sociosJuridicos.contains(p) && !naoSociosJuridicos.contains(p)) {
				naoSociosJuridicos.add(p);
			}
		}
		return naoSociosJuridicos;
	}

	public Long selecionarSocios(List<PessoaFisica> pessoasFisicas, List<PessoaJuridica> pessoasJuridicas) {
		view.alertaMsg("Lista de nao socios");
		List<Pessoa> fisicas = new ArrayList<>(pessoasFisicas);
		List<Pessoa> juridicas = new ArrayList<>(pessoasJuridicas);
		this.view.listarTodos(fisicas);
		this.view.listarTodos(juridicas);
		Scanner ler = new Scanner(System.in);
		System.out.println("Digite o ID da pessoa ou empresa ");
		return ler.nextLong();
	}

	public Long selecionar() {
		mostrarTodos();
		Scanner ler = new Scanner(System.in);
		System.out.println("Digite o ID da pessoa ou empresa ");
		return ler.nextLong();
	}

	public void mostrarTodos() {
		List<Pessoa> fisicas = new ArrayList<>(this.estruturaSocietaria.getSociosFisicos());
		List<Pessoa> juridicas = new ArrayList<>(this.estruturaSocietaria.getSociosJuridicos());
		this.view.listarTodos(fisicas);
		this.view.listarTodos(juridicas);
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public EstruturaSocietaria getEstruturaSocietaria() {
		return estruturaSocietaria;
	}

	public void setEstruturaSocietaria(EstruturaSocietaria estruturaSocietaria) {
		this.estruturaSocietaria = estruturaSocietaria;
	}

	public List<PessoaFisica> getPessoasFisicas() {
		return pessoasFisicas;
	}

	public void setPessoasFisicas(List<PessoaFisica> pessoasFisicas) {
		this.pessoasFisicas = pessoasFisicas;
	}

	public List<PessoaJuridica> getPessoasJuridicas() {
		return pessoasJuridicas;
	}

	public void setPessoasJuridicas(List<PessoaJuridica> pessoasJuridicas) {
		this.pessoasJuridicas = pessoasJuridicas;
	}

}
