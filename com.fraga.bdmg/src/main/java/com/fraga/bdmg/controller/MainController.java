package com.fraga.bdmg.controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.fraga.bdmg.application.Controller;
import com.fraga.bdmg.model.BemImovel;
import com.fraga.bdmg.populate.BemImovelPopulate;
import com.fraga.bdmg.populate.PessoaFisicaPopulate;
import com.fraga.bdmg.populate.PessoaJuridicaPopulate;
import com.fraga.bdmg.view.MainView;

public class MainController implements Controller {

	private PessoaFisicaController pfController;
	private PessoaJuridicaController pjController;
	private BemImovelController biController;
	private Long contId;

	public MainController() {
		this.contId = 5L;// valor iniciado em 5 devido a já ter sido cadastrado objetos (pf/pj) no metodo populate
		pfController = new PessoaFisicaController();
		pjController = new PessoaJuridicaController();
		biController = new BemImovelController();
	}

	public void iniciar() {//Inicia o funcionamento
		pfController.setContId(contId); //o identificador para pessoa fisica e pessoa juridica e unico. faz o set no valor dos controllers sempre que necessario
		pjController.setContId(contId);//o identificador para pessoa fisica e pessoa juridica e unico. faz o set no valor dos controllers sempre que necessario
		populateLists();   //Dados inserido manualmente para fins de teste do sistema
		consoleInicio();
	}

	public void consoleInicio() {

		int entrada = 0;
		do {
			MainView.menuPrincipal();
			try {
				entrada = lerEntrada();
				switch (entrada) {
				case 1: //Menu Pessoa Fisica
					pfController.setMainController(this);
					pfController.setContId(contId);
					pfController.iniciar();
					break;

				case 2://Menu Pessoa Juridica/Empresa
					pjController.setMainController(this);
					pjController.setContId(contId);
					pjController.iniciar();
					break;

				case 0:
					MainView.alertaMsg("Até mais!");
					break;
				default:
					MainView.alertaMsg("Opcao invalida.");
					break;
				}
			} catch (IOException e) {
				MainView.ioExceptionMsg("Valor digitado inválido! Tente de novo.");
			}
			catch(InputMismatchException e){
				MainView.ioExceptionMsg("Entrada de dados invalida." + e);
			}
			pfController.setContId(contId);//o identificador para pessoa fisica e pessoa juridica e unico. faz o set no valor dos controllers sempre que necessario
			pjController.setContId(contId);//o identificador para pessoa fisica e pessoa juridica e unico. faz o set no valor dos controllers sempre que necessario
		} while (entrada != 0);
	}

	public int lerEntrada() throws IOException {
		Scanner ler = new Scanner(System.in);
		return ler.nextInt();
	}

	private void populateLists() {
		List<BemImovel> bensImoveis = BemImovelPopulate.populate();
		pfController.setLista(PessoaFisicaPopulate.populate(bensImoveis));
		pjController.setLista(PessoaJuridicaPopulate.populate(pfController.getLista(), bensImoveis));
	}

	public PessoaFisicaController getPfController() {
		return pfController;
	}

	public void setPfController(PessoaFisicaController pfController) {
		this.pfController = pfController;
	}

	public PessoaJuridicaController getPjController() {
		return pjController;
	}

	public void setPjController(PessoaJuridicaController pjController) {
		this.pjController = pjController;
	}

	public BemImovelController getBiController() {
		return biController;
	}

	public void setBiController(BemImovelController biController) {
		this.biController = biController;
	}

	public Long getContId() {
		return contId;
	}

	public void setContId(Long contId) {
		this.contId = contId;
	}

}
