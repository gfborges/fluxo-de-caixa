package br.com.modelo;

public class Saida extends Transacao{
	  public final static String[] TIPOS_PF =  {"Energia Elétrica", "Gás", "Alimentacao", "Combustível", "Outros" };
	  public final static String[] TIPOS_PJ =  {"Energia Elétrica", "Gás", "Alimentacao (refeitorio)", "Combustível", "Outros" };
	  
	  public Saida(String data,double valor, String tipo){
	    super(data, valor, false, tipo);
	  }

	  public String  toString(){
	    return " - " + super.toString();
	  }

	  public double valueOf(){
	    return -1 * super.valueOf();
	  }
}
