package com.fraga.bdmg.controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.fraga.bdmg.application.View;
import com.fraga.bdmg.application.ViewInput;

public abstract class ControllerBasico<D> {

	protected ViewInput<D> view;
	
	protected List<D> lista;
	
	protected MainController mainController;
	
	public void iniciar() {
		consoleInicio();
		fim();
	}
	
	public void fim(){
		
	}
	
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
			catch(InputMismatchException e){
				view.ioExceptionMsg("Entrada de dados invalida." + e);
			}

		} while (entrada != 0);
	}
	
	protected int lerEntrada() throws IOException {
		Scanner ler = new Scanner(System.in);
		return ler.nextInt();
	}
	
	public void cadastrar() {
		// TODO Auto-generated method stub
		
	}
	
	public void mostrarTodos() {
		view.listarTodos(lista);
	}
	
	public void alterar() {
		// TODO Auto-generated method stub
		
	}
	
	public void deletar() {
		// TODO Auto-generated method stub
		
	}

	public void adicionar() {
		// TODO Auto-generated method stub
		
	}

	public boolean entityValidation(D entity) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<D>> violations = validator.validate(entity);
		if (violations.size() <= 0) {
			return true;
		}
		view.erroValidacao(violations);
		return false;
	}

	public ViewInput<D> getView() {
		return view;
	}

	public void setView(ViewInput<D> view) {
		this.view = view;
	}

	public List<D> getLista() {
		return lista;
	}

	public void setLista(List<D> lista) {
		this.lista = lista;
	}

	public MainController getMainController() {
		return mainController;
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	
	
	
	
}
