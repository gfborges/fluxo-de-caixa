package br.com.modelo;

import br.com.negocio.Controle;

public class Endereco {
	private String cidade;
	private String bairro;
	private String rua;
	private String numero;
	private String complemento;
	
	public Endereco(String cidade, String bairro, String rua, String numero, String complemento) {
		super();
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String toString() {
		String s = "";
		if(!cidade.isEmpty())
			s +=  "Cidade: " + cidade + "\n";
		if(!bairro.isEmpty())
			s +=  "Bairro: " + bairro + "\n";
		if(!rua.isEmpty())
			s +=  "Rua: " + rua + "\n";
		if(!numero.isEmpty())
			s +=  "Numero: " + numero + "\n";
		if(!complemento.isEmpty())
			s +=  "Complemento: " + complemento + "\n";
		return s;
	}
	
	public String toCSV() {
		String line = Controle.attrOuEspaco(cidade) + "," +
					  Controle.attrOuEspaco(bairro) + "," +
					  Controle.attrOuEspaco(rua)    + "," +
					  Controle.attrOuEspaco(numero) + "," +
					  Controle.attrOuEspaco(complemento);
		return line;
	}
}
