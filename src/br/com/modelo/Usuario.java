package br.com.modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Usuario {
	public static final String ARQUIVO_USUARIOS = "usuarios.csv";
	
	private final String LOGIN;
	private final String SENHA;
	private String caixa;
	
	private String nome;
	private String email;
	private boolean tipo; // PJ (T) / PF (F)
	
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
	
	public void toCSV() throws IOException {
		boolean saving = true;
		String line  = LOGIN + "," +
					   SENHA + "," +
					   caixa + "," +
					   nome + "," +
					   email + "," +
					   tel.getNumero() + "," +
					   endereco.toCSV() + "\n";
		while(saving) {
			try {
				Files.write(Paths.get(ARQUIVO_USUARIOS), line.getBytes(), StandardOpenOption.APPEND);
			}catch(IOException  e) {
				FileWriter file = new FileWriter(ARQUIVO_USUARIOS);
				file.close();
				saving = !saving;
			}
			saving = !saving;
		}
	}
}
