package br.com.modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import br.com.negocio.Controle;

public class Usuario {
	public static final String ARQUIVO_USUARIOS = ".usuarios.csv";
	public static final String[] TIPOS = {"Pessoa Juridica", "Pessoa Fisica"};
	
	private final String LOGIN;
	private final String SENHA;
	private String caixa;
	
	private String nome;
	private String email;
	private boolean tipo; // PF (F) / PJ (T)
	
	private Telefone tel;
	private Endereco endereco;
	
	public Usuario(String LOGIN, String SENHA, String caixa, String nome, String email, int tipo, Telefone tel, Endereco endereco) {
		this.LOGIN = LOGIN;
		this.SENHA = SENHA;
		this.caixa = caixa;
		this.nome = nome;
		this.email = email;
		this.tipo = (tipo == 1)? true : false;
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
	
	public boolean isPj(){
		return tipo;
	}
	
	public boolean isPf() {
		return !tipo;
	}
	
	public int getTipo_int() {
		return (tipo)? 1: 0;
	}
	
	public void toCSV() throws IOException {
		boolean saving = true;
		String line  = LOGIN + "," +
					   SENHA + "," +
					   caixa + "," +
					   nome  + "," +
					   email + "," +
					   getTipo_int() + "," +
					   Controle.attrOuEspaco(tel.getNumero()) + "," +
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
	
	public static Usuario fromCSV(String[] row) {
		if(row.length != 12)
			System.err.println("Erro na leitura do arquivo");
		String login = row[0];
		String senha = row[1];
		String caixa = row[2];
		String nome  = row[3];
		String email = row[4];
		int tipo = Integer.parseInt(row[5]);
		Telefone tel = new Telefone(row[6].trim());
		Endereco end = new Endereco(row[7].trim(), row[8].trim(), row[9].trim(), row[10].trim(), row[11].trim());
		return new Usuario(login, senha, caixa, nome, email, tipo, tel, end);
	}
}
