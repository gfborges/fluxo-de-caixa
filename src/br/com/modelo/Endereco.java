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
	
	public String toCSV() {
		String line = Controle.attrOuEspaco(cidade) + "," +
					  Controle.attrOuEspaco(bairro) + "," +
					  Controle.attrOuEspaco(rua)    + "," +
					  Controle.attrOuEspaco(numero) + "," +
					  Controle.attrOuEspaco(complemento);
		return line;
	}
}
