package br.com.app;

import java.io.IOException;
import br.com.modelo.Caixa;
import br.com.negocio.Menu;

public class App {

	public static void main(String[] args) throws IOException {
		Menu menu = new Menu();
		Caixa caixa = new Caixa();
		boolean logado = menu.init(args);
		int op = -1;
		while(!logado) {
			logado = menu.login();
		}
		// App menu
		while(logado) {
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
