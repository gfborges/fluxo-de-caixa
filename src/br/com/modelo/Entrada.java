package br.com.modelo;

public class Entrada extends Transacao{
	  public final static String[] TIPOS =  {"Salario", "Receita de vendas", "Investimento"};

	  public Entrada(String data, double valor, int tipo){
	    super(data, valor, true, Entrada.TIPOS[tipo]);
	  }
	  
	  @Override
	  public String toString() {
	    return " + " + super.toString();
	  }
}

