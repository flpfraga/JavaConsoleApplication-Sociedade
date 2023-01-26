package com.fraga.bdmg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fraga.bdmg.application.ViewInput;
import com.fraga.bdmg.model.BemImovel;
import com.fraga.bdmg.model.Pessoa;
import com.fraga.bdmg.model.PessoaFisica;
import com.fraga.bdmg.model.PessoaJuridica;

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
			} catch(InputMismatchException e){
				view.ioExceptionMsg("Entrada de dados invalida." + e);
			}

		} while (entrada != 0);
	}

	@Override
	public void cadastrar() {
		try {
			PessoaJuridica pf = view.formIntput();
			if (entityValidation(pf)) {
				pf.setId(contId++);
				pf.setBensImoveis(adicionarBensImoveis(pf.getBensImoveis()));
				pf.setEstruturaSocietaria(adicionarSocios(pf));
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
		PessoaJuridica pessoaJuridica = idEstaNaLista(idSelecionado);
		if (pessoaJuridica == null) {
			view.alertaMsg("O id informado não tem correspondencia com pessoas cadastradas.");
		} else {
			try {
				PessoaJuridica nova = view.formIntputAlterar(pessoaJuridica);
				if (entityValidation(nova)) {
					nova.setBensImoveis(alterarBensImoveis(pessoaJuridica.getBensImoveis()));
					nova.setEstruturaSocietaria(alterarSocios(pessoaJuridica));
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
			view.alertaMsg("Deletado.");
		}
	}
	
	public void removerEmpresaDeletadaDeSociedades(PessoaJuridica pessoaJuridica) {
		for (PessoaJuridica pj : lista) {
			List<Pessoa> socios = pj.getEstruturaSocietaria();
			for (Pessoa p : socios) {
				if (p.getId()==pessoaJuridica.getId()) {
					socios.remove(p);
				}
			}
		}
	}
	public void removerPessoasDeletadaDeSociedades(PessoaFisica pessoaFisica) {
		for (PessoaJuridica pj : lista) {
			List<Pessoa> socios = pj.getEstruturaSocietaria();
			for (Pessoa p : socios) {
				if (p.getId()==pessoaFisica.getId()) {
					socios.remove(p);
				}
			}
		}
	}

	public void comprometimentoFinanceiro() {
		Long idSelecionado = view.selecionar(lista);
		PessoaJuridica pessoaJuridica = idEstaNaLista(idSelecionado);
		if (pessoaJuridica == null) {
			view.alertaMsg("O id informado não tem correspondencia com pessoas cadastradas.");
		} else {

		}
		Map<String, BemImovel> bensImoveisSociedade = criaMapComBensImoveis(pessoaJuridica);

		Double indiceComprometimento = calculaIndiceComprometimentoFinanceiro(bensImoveisSociedade);
		view.alertaMsg("");
		view.alertaMsg("Indice de comprometimento financeiro para empresa " + pessoaJuridica.getNome() + " e "
				+ indiceComprometimento);
		view.alertaMsg("");
	}

	public Map<String, BemImovel> criaMapComBensImoveis(PessoaJuridica pessoaJuridica) {
		Map<String, BemImovel> bensImoveisSociedade = new HashMap<>();
		pessoaJuridica.getBensImoveis().forEach(b -> bensImoveisSociedade.put(b.hashCode() + "", b));
		pessoaJuridica.getEstruturaSocietaria()
				.forEach(e -> e.getBensImoveis().forEach(b -> bensImoveisSociedade.put(b.hashCode() + "", b)));

		return bensImoveisSociedade;
	}

	public Double calculaIndiceComprometimentoFinanceiro(Map<String, BemImovel> bensImoveisSociedade) {
		Iterator<Map.Entry<String, BemImovel>> iterator = bensImoveisSociedade.entrySet().iterator();
		Double indiceComprometimento = 0.0;
		while (iterator.hasNext()) {
			Map.Entry<String, BemImovel> entry = iterator.next();
			indiceComprometimento += entry.getValue().getValor();
		}
		return indiceComprometimento;
	}

	public List<Pessoa> adicionarSocios(PessoaJuridica pessoaJuridica) {
		EstruturaSocietariaController socioController = new EstruturaSocietariaController();
		socioController.setPfs(mainController.getPfController().getLista());
		socioController.setPjs(mainController.getPjController().getLista());
		socioController.setLista(pessoaJuridica.getEstruturaSocietaria());
		socioController.setPessoaJuridica(pessoaJuridica);
		socioController.adicionar();
		return socioController.getLista();
	}

	public List<Pessoa> alterarSocios(PessoaJuridica pessoaJuridica) {
		EstruturaSocietariaController socioController = new EstruturaSocietariaController();
		socioController.setPfs(mainController.getPfController().getLista());
		;
		socioController.setPjs(mainController.getPjController().getLista());
		;
		socioController.setLista(pessoaJuridica.getEstruturaSocietaria());
		socioController.setPessoaJuridica(pessoaJuridica);
		socioController.alterarSocios();
		return socioController.getLista();
	}

	public Long getContId() {
		return contId;
	}

	public void setContId(Long contId) {
		this.contId = contId;
	}

	public PessoaJuridica idEstaNaLista(Long id) {
		for (PessoaJuridica p : lista) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}

	public void insereNaLista(Long id, PessoaJuridica pessoaJuridica) {
		for (PessoaJuridica p : lista) {
			if (p.getId() == id) {
				p.setNome(pessoaJuridica.getNome());
				p.setCnpj(pessoaJuridica.getCnpj());
				p.setBensImoveis(pessoaJuridica.getBensImoveis());
				p.setEstruturaSocietaria(pessoaJuridica.getEstruturaSocietaria());
			}
		}
	}

}
