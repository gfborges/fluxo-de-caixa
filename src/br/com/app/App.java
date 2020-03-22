package br.com.app;

import br.com.negocio.Menu;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(true) {
			int op = -1;
			Menu.menu();
			switch (op){
				case 1: // Nova entrada
					break;
				case 2: // Nova saida
					break;
				case 3: // Editar entrada
					break;
				case 4: // Editar saida
					break;
				case 5: // Remover entrada
					break;
				case 6: // Remover saida
					break;
				case 7: // Relatório
					break;
				case 8: // Cadastrar usuário
					break;
				case 0:
					System.exit(0);
					break;
				default:
					System.out.println("Opção invalida");
			}
		}
	}

}
