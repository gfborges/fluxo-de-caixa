package br.com.app;

import java.io.IOException;

import br.com.modelo.Caixa;
import br.com.negocio.Menu;

public class App {

	public static void main(String[] args) throws IOException {
		Menu menu = new Menu();
		Caixa caixa = new Caixa();
		boolean logado = false;
		int op = -1;
		if( args.length < 2 && menu.confirmarN("Deseja efetuar cadastro") ) {
			// menu.cadastrar();
			System.out.println("Uso: java -jar caixa.jar <login> <senha>");
		}
		else if( args.length > 1 && (logado = !menu.login(args[0], args[1])) ) {
			System.out.println("Login ou senha inválidos");
			menu.sair();
		}
		if(!logado) {
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
