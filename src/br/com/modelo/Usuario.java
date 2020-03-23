package br.com.modelo;

public class Usuario {
	private final String LOGIN;
	private final String SENHA;
	private String caixa;
	
	private String nome;
	private String email;
	
	private Telefone tel;
	private Endereco endereco;
	
	public Usuario(String LOGIN, String SENHA, String caixa, String nome, String email, Telefone tel, Endereco endereco) {
		this.LOGIN = LOGIN;
		this.SENHA = SENHA;
		this.caixa = caixa;
		this.nome = nome;
		this.email = email;
		this.tel = tel;
		this.endereco = endereco;
	}

	public String getCaixa() {
		return caixa;
	}

	public void setCaixa(String caixa) {
		this.caixa = caixa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Telefone getTel() {
		return tel;
	}

	public void setTel(Telefone tel) {
		this.tel = tel;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getLOGIN() {
		return LOGIN;
	}

	public String getSENHA() {
		return SENHA;
	}
	
}
