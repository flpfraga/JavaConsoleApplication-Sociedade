package com.fraga.bdmg.controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import com.fraga.bdmg.application.ViewInput;
import com.fraga.bdmg.data.model.BemImovel;
import com.fraga.bdmg.data.model.PessoaFisica;

public class BemImovelController extends ControllerBasico<BemImovel> {

	private Long contId;

	public BemImovelController() {
		contId = 9L;// valor iniciado em 9 devido a já ter sido cadastrado objetos no metodo
					// populate
		view = ViewInput.criaBemImovel();

	}

	@Override
	public void fim() {
		mainController.setBiController(this);//faz set no controller do mainController para manter o sistema atualizado com alterações feitas
	}

	public void adicionar() {
		Scanner ler = new Scanner(System.in);
		String entrada = "";
		view.alertaMsg(
				"Deseja cadastrar bens imoveis para esta pessoa/empresa? Tecle N para sair. Tecle qualquer para continuar.");
		entrada = ler.nextLine();
		if (!entrada.equalsIgnoreCase("n")) {
			cadastrar();
		}

	}

	@Override
	public void cadastrar() {
		Scanner ler = new Scanner(System.in);
		String entrada = "";
		while (!entrada.equalsIgnoreCase("n")) {
			BemImovel bi = new BemImovel();
			try {
				bi = view.formIntput();
				if (entityValidation(bi)) {
					bi.setId(contId++);
					lista.add(bi);
					view.alertaMsg("Bem imovel cadastrado");
				} else {
					view.alertaMsg("Erro ao cadastrar o bem imovel.");
				}

			} catch (IOException e) {
				view.ioExceptionMsg("Entrada de dados invalida. " + e);
			} catch (InputMismatchException e) {
				view.ioExceptionMsg("Valor digitado e invalido. " + e);
			} catch (NumberFormatException e) {
				view.ioExceptionMsg("Valor digitado e invalido. " + e);
			}
			view.alertaMsg(
					"Deseja cadastrar outro bens imoveis para esta pessoa? Tecle N para sair. Tecle qualquer para continuar.");
			try {
				entrada = ler.nextLine();
			} catch (Exception e) {
				view.ioExceptionMsg("Erro com o valor digitado.Repita a operacao,");
				break;
			}
		}

	}

	public void alterarBemImovel() {
		mostrarTodos();
		consoleInicio();
	}

	@Override
	public void alterar() {
		Long idSelecionado = view.selecionar(lista);
		BemImovel bemImovel = idEstaNaLista(idSelecionado);

		if (bemImovel == null) {
			view.alertaMsg("O id informado não tem correspondencia com bens cadastrados.");
		} else {
			try {

				BemImovel novo = view.formIntputAlterar(bemImovel);
				if (entityValidation(novo)) {
					insereNaLista(idSelecionado, novo);
					view.alertaMsg("Cadastro realizado com sucesso.");
				} else {
					view.alertaMsg("Nao foi possivel realizar o cadastro. Tente novamente.");
				}
			} catch (IOException e) {
				view.ioExceptionMsg("Entrada de dados invalida." + e);
			} catch (InputMismatchException e) {
				view.ioExceptionMsg("Valor digitado e invalido. " + e);
			} catch (NumberFormatException e) {
				view.ioExceptionMsg("Valor digitado e invalido. " + e);
			}
		}
	}

	@Override
	public void deletar() {
		Long idSelecionado = view.selecionar(lista);
		BemImovel bemImovel = idEstaNaLista(idSelecionado);

		if (bemImovel != null) {
			lista.remove(bemImovel);
			view.alertaMsg("Deletado.");

		} else {
			view.alertaMsg("O id informado não tem correspondencia com bens cadastrados.");
		}
	}

	public Long getContId() {
		return contId;
	}

	public void setContId(Long contId) {
		this.contId = contId;
	}

	public BemImovel idEstaNaLista(Long id) {
		for (BemImovel b : lista) {
			if (b.getId() == id) {
				return b;
			}
		}
		return null;
	}

	public void insereNaLista(Long id, BemImovel bemImovel) {
		for (BemImovel b : lista) {
			b.print();
			if (b.getId() == id) {
				b.setNome(bemImovel.getNome());
				b.setValor(bemImovel.getValor());
			}
		}
	}
	
	
}
