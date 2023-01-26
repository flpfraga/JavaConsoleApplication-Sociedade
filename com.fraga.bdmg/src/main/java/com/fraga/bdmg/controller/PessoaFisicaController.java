package com.fraga.bdmg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fraga.bdmg.application.ViewInput;
import com.fraga.bdmg.model.BemImovel;
import com.fraga.bdmg.model.PessoaFisica;
import com.fraga.bdmg.model.PessoaJuridica;

public class PessoaFisicaController  extends ControllerBasico<PessoaFisica>{

	private Long contId;

	public PessoaFisicaController() {
		this.contId = 0L;
		view = ViewInput.criaPessoaFisica();
		lista = new ArrayList<>();
	}
	
	@Override
	public void fim() {
		mainController.setPfController(this);
		mainController.setContId(contId);
	}

	@Override
	public void cadastrar() {
		try {
			PessoaFisica pf = view.formIntput();
			if (entityValidation(pf)) {
				pf.setId(contId++);
				pf.setBensImoveis(adicionarBensImoveis(pf.getBensImoveis()));
				lista.add(pf);
				view.alertaMsg("Cadastro realizado com sucesso.");
			} else {
				view.alertaMsg("Nao foi possivel realizar o cadastro. Tente novamente.");
			}
		} catch (IOException e) {
			view.ioExceptionMsg("Entrada de dados invalida." + e);
		}
	}

	@Override
	public void alterar() {
		Long idSelecionado = view.selecionar(lista);
		PessoaFisica pessoaFisica = idEstaNaLista(idSelecionado);
		if (pessoaFisica == null) {
			view.alertaMsg("O id informado não tem correspondencia com pessoas cadastradas.");
		} else {
			try {
				PessoaFisica nova = view.formIntputAlterar(pessoaFisica);
				System.out.println("apos o inut no controller");
				nova.printPessoaFisica();
				if (entityValidation(nova)) {
					nova.setBensImoveis(alterarBensImoveis(pessoaFisica.getBensImoveis()));
					insereNaLista(idSelecionado, nova);
					view.alertaMsg("Cadastro realizado com sucesso.");
				} else {
					view.alertaMsg("Nao foi possivel realizar o cadastro. Tente novamente.");
				}
			} catch (IOException e) {
				view.ioExceptionMsg("Entrada de dados invalida." + e);
			}
		}
	}

	private List<BemImovel> adicionarBensImoveis(List<BemImovel> bensImoveis) {

		BemImovelController biController = mainController.getBiController();
		biController.setLista(bensImoveis);
		biController.adicionar();
		return biController.getLista();

	}

	private List<BemImovel> alterarBensImoveis(List<BemImovel> bensImoveis) {

		BemImovelController biController = mainController.getBiController();
		biController.setLista(bensImoveis);
		biController.alterarBemImovel();
		return biController.getLista();
	}

	@Override
	public void deletar() {
		Long idSelecionado = view.selecionar(lista);
		PessoaFisica pessoaFisica = new PessoaFisica();
		for (PessoaFisica pf : lista) {
			if (pf.getId() == idSelecionado) {
				pessoaFisica = pf;
			}
		}
		if (pessoaFisica.getId() == null) {
			view.alertaMsg("O id informado não tem correspondencia com pessoas cadastradas.");
		} else {
			lista.remove(pessoaFisica);
			mainController.getPjController().removerPessoasDeletadaDeSociedades(pessoaFisica);
			view.alertaMsg("Deletado.");
		}
	}

	public Long getContId() {
		return contId;
	}

	public void setContId(Long contId) {
		this.contId = contId;
	}
	
	public PessoaFisica idEstaNaLista(Long id) {
		for (PessoaFisica p : lista) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}

	public void insereNaLista(Long id, PessoaFisica pessoaFisica) {
		for (PessoaFisica p : lista) {
			if (p.getId() == id) {
				p.setNome(pessoaFisica.getNome());
				p.setCpf(pessoaFisica.getCpf());
				p.setBensImoveis(pessoaFisica.getBensImoveis());
			}
		}
	}

}
