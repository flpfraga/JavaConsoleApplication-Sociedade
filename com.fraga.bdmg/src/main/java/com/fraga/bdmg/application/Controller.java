package com.fraga.bdmg.application;

import com.fraga.bdmg.controller.MainController;

public interface Controller {
	
	static Controller criaMainController() {
		return new MainController();
	}

	void iniciar();

}