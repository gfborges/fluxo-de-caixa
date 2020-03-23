package br.com.modelo;

public class Saida extends Transacao{
	  public final static String[] TIPOS =  {"Energia Elétrica", "Gás", "Alimentacao (refeitório)", "Alimentacao(fora de casa)", "Combustível", "Outros" };
	  
	  public Saida(String data,double valor, int tipo){
	    super(data, valor, false, Saida.TIPOS[tipo]);
	  }

	  public String  toString(){
	    return " - " + super.toString();
	  }

	  public double valueOf(){
	    return -1 * super.valueOf();
	  }
}
