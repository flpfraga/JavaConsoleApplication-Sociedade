package com.fraga.bdmg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fraga.bdmg.application.ViewInput;
import com.fraga.bdmg.data.model.BemImovel;
import com.fraga.bdmg.data.model.EstruturaSocietaria;
import com.fraga.bdmg.data.model.PessoaFisica;
import com.fraga.bdmg.data.model.PessoaJuridica;

public class PessoaJuridicaController extends ControllerBasico<PessoaJuridica> {

	private Long contId;

	public PessoaJuridicaController() {
		this.contId = 0L;
		view = ViewInput.criaPessoaJuridica();
		lista = new ArrayList<>();
	}

	@Override
	public void fim() {
		mainController.setPjController(this);
		mainController.setContId(contId);
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
					alterar();
					break;
				case 4:
					deletar();
					break;
				case 5:
					comprometimentoFinanceiro();
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
			} catch (InputMismatchException e) {
				view.ioExceptionMsg("Entrada de dados invalida." + e);
			}

		} while (entrada != 0);
	}

	@Override
	public void cadastrar() {
		try {
			PessoaJuridica pessoaJuridica = view.formIntput();
			pessoaJuridica.setId(contId++);
			
			if (entityValidation(pessoaJuridica)) {
				pessoaJuridica.setBensImoveis(adicionarBensImoveis(new ArrayList<BemImovel>()));
				EstruturaSocietaria estruturaSocietaria = (adicionarSocios(pessoaJuridica));
				String estruturaSocietariaValida = estruturaSocietaria.isValid();
				if (estruturaSocietariaValida.contentEquals("")) {
					pessoaJuridica.setEstruturaSocietaria(estruturaSocietaria);
					this.lista.add(pessoaJuridica);
					this.view.alertaMsg("Cadastro realizado com sucesso.");
				} else {
					this.view.alertaMsg(estruturaSocietariaValida);
					this.view.alertaMsg("Nao foi possivel realizar o cadastro. Tente novamente.");
				}

			} else {

				this.view.alertaMsg("Nao foi possivel realizar o cadastro. Tente novamente.");
			}
		} catch (IOException e) {
			this.view.ioExceptionMsg("Entrada de dados invalida." + e);
		}
	}

	@Override
	public void alterar() {
		Long idSelecionado = this.view.selecionar(lista);
		PessoaJuridica pessoaJuridica = idEstaNaLista(idSelecionado);
		if (pessoaJuridica == null) {
			this.view.alertaMsg("O id informado não tem correspondencia com pessoas cadastradas.");
		} else {
			try {
				PessoaJuridica nova = view.formIntputAlterar(pessoaJuridica);
				if (entityValidation(nova)) {
					nova.setBensImoveis(alterarBensImoveis(pessoaJuridica.getBensImoveis()));
					EstruturaSocietaria estruturaSocietaria = (alterarSocios(pessoaJuridica));
					String estruturaSocietariaValida = estruturaSocietaria.isValid();
					if (estruturaSocietariaValida.contentEquals("")) {
						nova.setEstruturaSocietaria(estruturaSocietaria);
						insereNaLista(idSelecionado, nova);
						this.view.alertaMsg("Cadastro realizado com sucesso.");
					} else {
						this.view.alertaMsg(estruturaSocietariaValida);
						this.view.alertaMsg("Nao foi possivel realizar a alteracao. Tente novamente.");
					}
				} else {
					this.view.alertaMsg("Nao foi possivel realizar o cadastro. Tente novamente.");
				}
			} catch (IOException e) {
				this.view.ioExceptionMsg("Entrada de dados invalida." + e);
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
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		for (PessoaJuridica pf : lista) {
			if (pf.getId() == idSelecionado) {
				pessoaJuridica = pf;
			}
		}
		if (pessoaJuridica.getId() == null) {
			view.alertaMsg("O id informado não tem correspondencia com pessoas cadastradas.");
		} else {
			lista.remove(pessoaJuridica);
			removerEmpresaDeletadaDeSociedades(pessoaJuridica);
			view.alertaMsg("Deletado.");
		}
	}

	public void removerEmpresaDeletadaDeSociedades(PessoaJuridica pessoaJuridica) {
		for (PessoaJuridica pjs : this.lista) {
			List<PessoaJuridica> listaPessoasJuridicas = pjs.getEstruturaSocietaria().getSociosJuridicos();
			listaPessoasJuridicas.remove(pessoaJuridica);
		}
	}

	public void removerPessoasDeletadaDeSociedades(PessoaFisica pessoaFisica) {
		for (PessoaJuridica pjs : this.lista) {
			List<PessoaFisica> listaPessoasFisicas = pjs.getEstruturaSocietaria().getSociosFisicos();
			listaPessoasFisicas.remove(pessoaFisica);
		}
	}

	////////////////////////////////////////////////Métodos para calculo do comprometimento financeiro\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	//Método que é chamado após escolha no Menu. Verifica se a empresa tem auto-socieade e, se sim, caucula seus bens.
	public void comprometimentoFinanceiro() {
		Long idSelecionado = view.selecionar(lista);
		PessoaJuridica pessoaJuridica = idEstaNaLista(idSelecionado);
		if (pessoaJuridica == null) {
			view.alertaMsg("O id informado não tem correspondencia com pessoas cadastradas.");
		} else {
			EstruturaSocietaria estruturaSocietaria = pessoaJuridica.getEstruturaSocietaria();
			Double indiceComprometimento = 0.0;
			if(!estruturaSocietaria.getAutoSociedade()) {
				indiceComprometimento = pessoaJuridica.getBensImoveis().stream().mapToDouble(b -> b.getValor()).sum();
			}
			indiceComprometimento += comprometimentoFinanceiro(estruturaSocietaria);
			view.alertaMsg("");
			view.alertaMsg("Indice de comprometimento financeiro para empresa " + pessoaJuridica.getNome() + ": "
					+ indiceComprometimento);
			view.alertaMsg("");
		}
	}

	//Método que percorre a estrutura societária de uma empresa e adiciona seus bens num Hash Map para não haver duplicação de itens.
	//Retorna um Double com valor do comprometimento financeiro da empresa
	public Double comprometimentoFinanceiro(EstruturaSocietaria estruturaSocietaria) {

		Map<Integer, BemImovel> bensImoveisSociedade = new HashMap<>();

		estruturaSocietaria.getSociosFisicos()
				.forEach(f -> f.getBensImoveis().forEach(b -> bensImoveisSociedade.put(b.hashCode(), b)));
		estruturaSocietaria.getSociosJuridicos()
				.forEach(f -> f.getBensImoveis().forEach(b -> bensImoveisSociedade.put(b.hashCode(), b)));

		Iterator<Map.Entry<Integer, BemImovel>> iterator = bensImoveisSociedade.entrySet().iterator();
		Double indiceComprometimento = 0.0;
		while (iterator.hasNext()) {
			Map.Entry<Integer, BemImovel> entry = iterator.next();
			indiceComprometimento += entry.getValue().getValor();
		}
		return indiceComprometimento;
	}

	public EstruturaSocietaria adicionarSocios(PessoaJuridica pessoaJuridica) {

		EstruturaSocietariaController socioController = new EstruturaSocietariaController();
		socioController.setPessoasFisicas(this.mainController.getPfController().getLista());
		socioController.setPessoasJuridicas(this.mainController.getPjController().getLista());
		socioController.setEstruturaSocietaria(new EstruturaSocietaria());
		socioController.setPessoaJuridica(pessoaJuridica);
		socioController.adicionar();
		EstruturaSocietaria estruturaSocietaria = socioController.getEstruturaSocietaria();
		return estruturaSocietaria;
	}

	public EstruturaSocietaria alterarSocios(PessoaJuridica pessoaJuridica) {
		EstruturaSocietariaController socioController = new EstruturaSocietariaController();
		socioController.setPessoasFisicas(this.mainController.getPfController().getLista());
		socioController.setPessoasJuridicas(this.mainController.getPjController().getLista());
		socioController.setEstruturaSocietaria(pessoaJuridica.getEstruturaSocietaria());
		socioController.setPessoaJuridica(pessoaJuridica);
		socioController.alterarSocios();
		EstruturaSocietaria estruturaSocietaria = socioController.getEstruturaSocietaria();
		return estruturaSocietaria;
	}

	public Long getContId() {
		return contId;
	}

	public void setContId(Long contId) {
		this.contId = contId;
	}

	public PessoaJuridica idEstaNaLista(Long id) {
		for (PessoaJuridica p : this.lista) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}

	public void insereNaLista(Long id, PessoaJuridica pessoaJuridica) {
		for (PessoaJuridica p : this.lista) {
			if (p.getId() == id) {
				p.setNome(pessoaJuridica.getNome());
				p.setCnpj(pessoaJuridica.getCnpj());
				p.setBensImoveis(pessoaJuridica.getBensImoveis());
				p.setEstruturaSocietaria(pessoaJuridica.getEstruturaSocietaria());
			}
		}
	}

}
