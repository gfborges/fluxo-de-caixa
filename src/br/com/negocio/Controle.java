package br.com.negocio;

import java.util.Scanner;

public class Controle {
	private Scanner scan;
	
	public Controle() {
		this.scan = new Scanner(System.in);
	}
	
	public int opcao() {
		int i = -1;
		if(scan.hasNextInt()) {
			i = scan.nextInt();
			scan.nextLine();
		}
		return i;
	}
	
	public String texto() {
		String s = scan.nextLine().trim();
		return s;
	}
	
	public double valor() {
		double d = -1;
		if(scan.hasNextDouble()) {
			d = scan.nextDouble();
			scan.nextLine();
		}
		return d;
	}
	
	public int bool_int(boolean vazio) {
		String in = this.texto().toLowerCase();
		if( in.isEmpty() )
			return  vazio? 1 : 0;
		boolean valido = in.startsWith("s") || in.startsWith("n");
		if(valido)
			return in.startsWith("s") ? 1 : 0;
		return -1;
	}
}