package br.com.app;

import br.com.modelo.Caixa;
import br.com.negocio.Menu;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu menu = new Menu();
		Caixa caixa = new Caixa();
		int op = -1;
		
		while(true) {
			op = menu.menu();
			switch (op){
				case 1: // Nova entrada
					menu.nova_entrada(caixa);
					break;
				case 2: // Nova saida
					menu.nova_saida(caixa);
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
					menu.relatorio(caixa);
					break;
				case 8: // Cadastrar usuário
					break;
				case 0:
					menu.sair();
					break;
				default:
					System.out.println("Opção invalida");
			}
			menu.pausar();
		}
	}

}
