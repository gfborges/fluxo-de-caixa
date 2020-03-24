package br.com.modelo;

public class Transacao{
	  protected String data;
	  protected String tipo;
	  protected double valor;
	  protected final boolean ent_saida;
	  
	  public Transacao(String data, double valor, boolean ent_saida, String tipo){
	    this.data = data;
	    this.tipo = tipo;
	    this.valor = valor;
	    this.ent_saida = ent_saida;
	  }
	  
	  public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean isEntrada() {
		return ent_saida;
	}
	
	public boolean isSaida ( ) {
		return !ent_saida;
	}

	public String toString(){
	    return this.tipo + " " + String.format("%.2f", valor);
	  }

	  public double valueOf(){
	    return valor;
	  }
	  
	  public boolean equals(Object o) {
		  if(o == this)
			  return true;
		  if(!(o instanceof Transacao))
			  return false;
		  Transacao t = (Transacao) o;
		  return (this.tipo.equals(t.getTipo()) && 
				  this.valor == t.getValor() && 
				  this.data.equals(t.getData()));
	  }
	}