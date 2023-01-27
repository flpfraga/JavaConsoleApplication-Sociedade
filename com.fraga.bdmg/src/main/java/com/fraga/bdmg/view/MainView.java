package com.fraga.bdmg.view;

public class MainView {
	
	public static void menuPrincipal() {
			System.out.println("Menu Principal");
			System.out.println("Selecione uma opcao e tecle:");
			System.out.println("1 - Menu Pessoa Fisica");
			System.out.println("2 - Menu Empresas");
			System.out.println("0 - Sair");
			System.out.println("Para calculo do comprometimento financeiro, acesso Menu Empresa e depois opcao 5");
			System.out.println();
	}

	public static void sucessoMsg(String msg) {
		System.out.println("Cadastro realizado com sucesso! " + msg);
	}
	public static void alertaMsg(String msg) {
		System.out.println(msg);
	}
	public static void ioExceptionMsg(String msg) {
		System.out.println("Valor inserido inválido");
		System.out.println(msg);
	}
	
}
