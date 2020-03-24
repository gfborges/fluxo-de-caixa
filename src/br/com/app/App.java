package br.com.app;

import java.io.IOException;
import br.com.modelo.Caixa;
import br.com.negocio.Menu;

public class App {

	public static void main(String[] args) throws IOException {
		Menu menu = new Menu();
		boolean logado = menu.init(args);
		int op = -1;
		while(!logado) {
			logado = menu.login();
		}
		Caixa caixa = new Caixa(menu.usuario.getCaixa());
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
				case 3: // Remover entrada / saida
					menu.remover(caixa);
					break;
				case 4: // Relatório
					menu.relatorio(caixa);
					break;
				case 5: // Cadastrar usuário
					menu.cadastrar();
					break;
				case 6: // Sobre
					menu.sobre();
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
