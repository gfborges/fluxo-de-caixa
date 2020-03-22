package br.com.negocio;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import br.com.modelo.Caixa;
import br.com.modelo.Entrada;
import br.com.modelo.Saida;
import br.com.modelo.Transacao;
import br.com.negocio.Controle;

public class Menu {
	private Controle ctrl;
	
	public Menu() {
		this.ctrl = new Controle();
	}
	
	public int menu() {
		System.out.println("Menu");
		System.out.println(" [1] Nova entrada");
		System.out.println(" [2] Nova saida");
		System.out.println(" [3] Excluir entrada");
		System.out.println(" [4] Excluir saida");
		System.out.println(" [5] Editar entrada");
		System.out.println(" [6] Editar saida");
		System.out.println(" [7] Relatorios");
		System.out.println(" [8] Cadastrar usuário");
		System.out.println(" [0] Sair");
		System.out.print("Selecione uma opçao: ");
		return ctrl.opcao();
	}
	
	private int ler_tipo(String[] opcoes) {
		int tipo;
		do {
			System.out.println("Escolha um dos tipos a seguir:");
			Transacao.listar_tipos(opcoes); // lista comecando do 1
			System.out.print("Tipo: ");
			tipo = ctrl.opcao();
		}while(tipo < 0 || tipo >= Entrada.TIPOS.length );
		return tipo - 1; // indice comeca do 0
	}
	
	private double ler_valor() {
		double valor;
		do {
			System.out.print("Valor: ");
			valor = ctrl.valor();
		}while(valor  <  0);
		return valor;
	}
	
	private String ler_data() {
		String data;
		LocalDate dt = LocalDate.now();
		boolean dt_flag;
		do {
			dt_flag = false;
			System.out.print("Data(aaaa-MM-dd): ");
			data = ctrl.texto();
			if(!data.isEmpty()) {
				try {
					dt = LocalDate.parse(data); 
				}
				catch( DateTimeParseException e){
					System.out.println("Data invalida! Tente novamente");
					dt_flag = true;
				}
			}
			else {
				return dt.toString(); // data de hoje
			}
		}while(dt_flag);
		return data;
	}
	
	public void nova_entrada(Caixa caixa) {
		String data, ano, mes, semana;
		LocalDate dt = LocalDate.now();
		int tipo = -1;
		double valor = -1;
		System.out.println("Por favor preencha todos os campos");
		
		// Tipo
		tipo = ler_tipo(Entrada.TIPOS);
		
		// Valor
		valor = ler_valor();
		
		// Data
		data = ler_data();
		
		Transacao ent = new Entrada(data, valor, tipo);
		
		ano = Integer.toString( dt.getYear() );
		mes = String.format("%02d", dt.getMonthValue() );
		semana = String.format("%02d", dt.getDayOfMonth() / 7 );
		String key = String.format("%s-%s-%s", ano, mes, semana);
		
		caixa.novo_registro(key, ent);
	}
	
	public void nova_saida(Caixa caixa) {
		String data, ano, mes, semana;
		LocalDate dt = LocalDate.now();
		int tipo = -1;
		double valor = -1;
		System.out.println("Por favor preencha todos os campos a seguir");
		
		// Tipo
		tipo = ler_tipo(Saida.TIPOS);
		
		// Valor
		valor = ler_valor();
		
		// Data
		data = ler_data();
		
		ano = Integer.toString( dt.getYear() );
		mes = String.format("%02d", dt.getMonthValue() );
		semana = String.format("%02d", dt.getDayOfMonth() / 7 );
		
		Transacao ent = new Saida(data, valor, tipo);
		String key = String.format("%s-%s-%s", ano, mes, semana);
		
		caixa.novo_registro(key, ent);
	}
	
	public void pausar() {
		System.out.print("\nPressione enter para continuar...");
		ctrl.texto();
	}
	
	public void sair() {
		System.exit(0);
	}
	
}
