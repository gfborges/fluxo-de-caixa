package br.com.modelo;

public class Telefone {
	private String numero;
	
	Telefone(String numero){
		this.numero = numero;
	}

	public String getNumero() {
		int len = numero.length();
		if(len == 8)
			return numero.substring(0, 4) + "-" + numero.substring(4) ;
		if(len == 9)
			return numero.substring(0, 5) + "-" + numero.substring(5);
		if(len == 10)
			return String.format("(%s) ", numero.substring(0, 2)) + 
					String.format("%s-%s", numero.substring(2, 6), numero.substring(6));
		if(len == 11)
			return String.format("(%s) ", numero.substring(0, 2)) + 
					String.format("%s-%s", numero.substring(2, 7), numero.substring(7));
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String toString() {
		return this.getNumero();
	}
	
}
