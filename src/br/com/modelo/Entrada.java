package br.com.modelo;

public class Entrada extends Transacao{
	public static final String[] TIPOS_PF = {"Salario", "Investimento"};
	public static final String[] TIPOS_PJ = {"Receita de vendas", "Investimento"};
	
	public Entrada(String data, double valor, String tipo){
		super(data, valor, true, tipo);
	}
	  
	@Override
	public String toString() {
		return " + " + super.toString(32);
		}
	}

