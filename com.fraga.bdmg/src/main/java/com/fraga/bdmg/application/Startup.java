package com.fraga.bdmg.application;

public class Startup {

	
	
	public static void main(String[] args) {
		
		Controller mainController = Controller.criaMainController();
		mainController.iniciar();
		
	}

}
