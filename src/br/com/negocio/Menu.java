package br.com.negocio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import br.com.modelo.Caixa;
import br.com.modelo.Endereco;
import br.com.modelo.Entrada;
import br.com.modelo.Saida;
import br.com.modelo.Telefone;
import br.com.modelo.Transacao;
import br.com.modelo.Usuario;

public class Menu {
	private final String ARQUIVO_USUARIOS;
	private Controle ctrl;
	public Usuario usuario = null;
	
	public Menu() {
		this.ctrl = new Controle();
		this.ARQUIVO_USUARIOS = Usuario.ARQUIVO_USUARIOS;
	}
	
	public int menu() {
		System.out.println("\n+---------Menu---------+");
		System.out.println(" [1] Nova transacao");
		System.out.println(" [2] Excluir transacao");
		System.out.println(" [3] Relatorios");
		System.out.println(" [4] Cadastrar usuário");
		System.out.println(" [5] Ver perfil");
		System.out.println(" [6] Sobre");
		System.out.println(" [0] Sair");
		System.out.println("+----------------------+");
		System.out.print("Selecione uma opçao: ");
		return ctrl.opcao();
	}
	
	public void cadastrar() throws IOException {
		String login, senha, caixa;
		String nome, email, telefone;
		String cidade,
			   bairro,
			   rua,
			   numero,
			   complemento;
		int tipo;
		
		System.out.println("Os campos com (*) são obrigatótrios");
		
		login = ler_login();
		senha = ler_campo_obg("(*)Senha: ");
		
		if(usuario != null && confirmarS("Deseja que este usuario tenha acesso aos mesmos dados que você"))
			caixa = usuario.getCaixa();
		else
			caixa = "." + login + ".csv";
		
		nome = ler_campo_obg("(*)Nome: ");
		email = ler_campo_obg("(*)E-mail: ");
		
		tipo = ler_tipo_int(Usuario.TIPOS);

		telefone = ler_telefone();
		
		cidade = ler_campo("Cidade: ");
		bairro = ler_campo("Bairro: ");
		rua = ler_campo("Rua: ");
		numero = ler_campo("Numero: ");
		complemento = ler_campo("Complemento: ");
		
		Telefone tel = new Telefone(telefone);
		Endereco end = new Endereco(cidade, bairro, rua, numero, complemento);
		Usuario user = new Usuario(login, senha, caixa, nome, email, tipo, tel, end);
		
		System.out.println(user);
		if( confirmarS("Deseja cadastrar o cliente") ) {
			user.salvarCSV();
			if(usuario != null)
				login(login, senha);
		}
	}
	
	public void ver_perfil() {
		System.out.println(usuario);
	}
	
	public boolean init(String[] args) throws IOException {
		if( args.length >= 2 && login(args[0], args[1])) {
				return true;
		}
		else if(confirmarN("Deseja efetuar cadastro")) {
				cadastrar();
				
				System.out.println("\nVocê pode logar durante a execução do programa");
				System.out.println("Uso: java -jar caixa.jar <login> <senha>\n");
				return true;
		}
		return false;
	}
	
	public boolean login(String login, String senha) throws IOException {
		BufferedReader arquivo;
		try {
			arquivo = new BufferedReader( new FileReader(ARQUIVO_USUARIOS) );
		}catch(IOException e) {
			System.out.println("Não existem usuários para fazer login, por favor cadastre-se");
			if( confirmarS("Deseja efetuar cadastro") )
				cadastrar();
			return false;
		}
		String line = arquivo.readLine();
		while(line  != null) {
			String[] row = line.split(",");
			if ( row[0].equals(login) && row[1].equals(senha) ) {
				usuario = Usuario.fromCSV(row);
				System.out.println("Login efetuado com sucesso, " + usuario.getNome());
				arquivo.close();
				return true;
			}
			line = arquivo.readLine();
		}
		System.out.println("Login ou senha inválidos");
		arquivo.close();
		return false;
	}
	
	public boolean login() throws IOException {
		String login, senha;
		// Login
		login = ler_campo_obg("Login: ");
		// Senha
		senha = ler_senha();
		
		return login(login, senha);
	}
	
	private String ler_campo(String msg) {
		System.out.print(msg);
		return ctrl.texto();
	}
	
	private String ler_campo_obg(String msg) {
		String campo;
		do {
			System.out.print(msg);
			campo = ctrl.texto();
		}while(campo.isEmpty());
		return campo;
	}
	
	private String ler_login() throws IOException {
		String login;
		do {
			System.out.print("(*)Login: ");
			login = ctrl.texto();
		}while(!validar_login(login));
		return login;
	}
	
	private String ler_senha() {
		return ctrl.senha("Senha: ");
	}
	
	private boolean validar_login(String login) throws IOException {
		BufferedReader arquivo;
		try {
			arquivo = new BufferedReader( new FileReader(ARQUIVO_USUARIOS) );
		}catch(IOException e) {
			return true;
		}
		
		String line = arquivo.readLine();
		
		while(line != null) {
			if( line.startsWith(login) ) {
				System.out.println("Este login nao esta disponivel");
				arquivo.close();
				return false;
			}
			line = arquivo.readLine();
		}
		
		arquivo.close();
		return true;
	}
	
	private String ler_telefone() {
		boolean valido;
		String tel;
		int len;
		do {
			valido = true;
			System.out.print("Telefone: ");
			tel = ctrl.texto();
			len = tel.length();
			if(len == 0)
				return tel;
			else if(!(len >= 8 && len <= 11)) {
				valido = false;
			}
			try {
				Integer.parseInt(tel);
			}catch(NumberFormatException e) {
				System.out.println("Por favor insira somente numeros");
				valido = false;
			}
		}while( !valido );
		return tel;
	}
	
	private int ler_tipo_int(String[] opcoes) {
		int tipo = 0;
		
		System.out.println("Escolha um dos tipos a seguir:");
		for(String opcao : opcoes) {
			System.out.printf(" [%d] %s\n", ++tipo, opcao);
		}
		
		do {
			System.out.print("Tipo: ");
			tipo = ctrl.opcao();
		}while(tipo <= 0 || tipo > opcoes.length );
		return tipo - 1; // indice comeca do 0
	}
	
	private String ler_tipo(String[] opcoes) {
		return opcoes[ ler_tipo_int(opcoes) ];
	}
	
	private double ler_valor() {
		double valor;
		do {
			System.out.print("Valor: ");
			valor = ctrl.valor();
		}while(valor  <  0);
		return valor;
	}
	
	private String ler_data() {
		String data;
		LocalDate dt = LocalDate.now();
		boolean dt_flag;
		do {
			dt_flag = false;
			System.out.print("Data(aaaa-MM-dd)(ENTER p/ hoje): ");
			data = ctrl.texto();
			if(!data.isEmpty()) {
				try {
					dt = LocalDate.parse(data); 
				}
				catch( DateTimeParseException e ){
					System.out.println("Data invalida! Tente novamente");
					dt_flag = true;
				}
			}
			else {
				return dt.toString(); // data de hoje
			}
		}while(dt_flag);
		return data;
	}
	
	private String ler_semana() {
		int dia;
		boolean dt_flag;
		String data, semana;
		do {
			dt_flag = false;
			System.out.print("Semana(aaaa-MM-SS): ");
			semana = ctrl.texto();
			if(semana.isEmpty()){
				LocalDate dt = LocalDate.now();
				data = dt.toString();
				dia = dt.getDayOfMonth();
				dia = (dia / 7) + 1;
				semana = data.substring(0, 8) + String.format("%02d", dia);
				return semana;
			}
			if(semana.length()!= 10) {
				dt_flag = true;
				continue;
			}
			
			try {
				dia = Integer.valueOf(semana.substring(8));
			}
			catch(Exception e) {
				System.out.println("Semana invalida!");
				continue;
			}
			
			dia = (dia-1) * 7 + 1;
			data = semana.substring(0, 8) + String.format("%02d", dia);;
			
			try {
				LocalDate.parse(data);
			}
			catch(DateTimeParseException e) {
				dt_flag = true;
			}
		}while(dt_flag);
		
		return semana;
	}
	
	private String ler_mes() {
		String mes; 
		boolean dt_flag;
		do {
			dt_flag = false;
			System.out.print("Mes(aaaa-MM):");
			mes = ctrl.texto();
			if(mes.isEmpty()) {
				return LocalDate.now().toString().substring(0, 7); // 2020-03
			}
			try {
				LocalDate.parse(mes+"-01");
			}catch(Exception e){
				dt_flag = true;
			}
		}while(dt_flag);
		return mes;
	}
	public void nova_transacao(Caixa caixa) throws IOException {
		String[] transacao = {"Entrada", "Saida"};
		int t = ler_tipo_int(transacao);
		if(t == 0)
			nova_entrada(caixa);
		else
			nova_saida(caixa);
	}
	public void nova_entrada(Caixa caixa) throws IOException {
		String data, tipo;
		double valor = -1;
		System.out.println("Por favor preencha todos os campos");
		
		// Tipo
		if(usuario.isPf())
			tipo = ler_tipo(Entrada.TIPOS_PF);
		else 
			tipo = ler_tipo(Entrada.TIPOS_PJ);
		
		// Valor
		valor = ler_valor();
		
		// Data
		data = ler_data();
		
		Transacao ent = new Entrada(data, valor, tipo);
		
		String key = Transacao.getKey(data);
		
		caixa.novo_registro(key, ent);
		System.out.println("A entrada \"" + ent + "\" foi inserida nos registros da semana " + key);
		ent.salvarCSV( usuario.getCaixa() );
	}
	
	public void nova_saida(Caixa caixa) throws IOException {
		String data, ano, mes, semana;
		LocalDate dt = LocalDate.now();
		String tipo;
		double valor = -1;
		System.out.println("Por favor preencha todos os campos a seguir");
		
		// Tipo
		if(usuario.isPf())
			tipo = ler_tipo(Saida.TIPOS_PF);
		else
			tipo = ler_tipo(Saida.TIPOS_PJ);
		
		// Valor
		valor = ler_valor();
		
		// Data
		data = ler_data();
		dt = LocalDate.parse(data);
				
		Transacao saida = new Saida(data, valor, tipo);
		
		ano = Integer.toString( dt.getYear() );
		mes = String.format("%02d", dt.getMonthValue() );
		semana = String.format("%02d", dt.getDayOfMonth() / 7 + 1 );
		String key = String.format("%s-%s-%s", ano, mes, semana);
		
		caixa.novo_registro(key, saida);
		
		System.out.println("A entrada \"" + saida + "\" foi inserida nos registros da semana " + key);
		
		saida.salvarCSV( usuario.getCaixa() );
	}
	
	public void pausar() {
		System.out.print("\nPressione enter para continuar...");
		ctrl.texto();
	}
	
	public void sair() {
		System.exit(0);
	}
	
	public boolean confirmarS(String msg) {
		int op = -1;
		do {
			System.out.print(msg+"(S/n)? ");
			op = ctrl.bool_int(true);
		}while(op < 0);
		return op == 1;
	}
	
	public boolean confirmarN(String msg) {
		int op = -1;
		do {
			System.out.print(msg+"(s/N)? ");
			op = ctrl.bool_int(false);
		}while(op < 0);
		return  op == 1;
	}
	
	public void relatorio(Caixa caixa) throws IOException {
		int escolha;
		String[] tipos = {"Semanal", "Mensal"};
		escolha = ler_tipo_int(tipos);
		if(escolha == 0 ) {
			System.out.println("Semana que deseja imprimir o relatório(ENTER p/ essa semana)");
			String semana = ler_semana();
			String relatorio = caixa.relatorio_semanal(semana);
			System.out.println(relatorio);
			if( confirmarS("Deseja salvar este relatorio") ) {
				salvar_relatorio(semana + ".txt", relatorio);
			}
		}
		else {
			System.out.println("Mes que deseja imprimir o relatório(ENTER p/ esse mes)");
			String mes = ler_mes();
			String relatorio = caixa.relatorio_mensal(mes);
			System.out.println(relatorio);
			if( confirmarS("Deseja salvar esse relatorio") )
				salvar_relatorio(mes + ".txt", relatorio);
		}
	}
	
	public void salvar_relatorio(String arquivo, String file) throws IOException {
		BufferedWriter bw;
		bw = new BufferedWriter (new FileWriter(arquivo));
		bw.write(file);
		bw.close();
		System.out.println("'" + arquivo +"', salvo com sucesso");
	}
	
	public void remover(Caixa caixa) throws IOException {
		System.out.println("Semana que deseja remover um transacao");
		while(true) {
			String semana = ler_semana();
			System.out.println("Remova a transacao pelo seu numero(id)");
			if( caixa.listar_registros(semana) ) {
				System.out.print("Id: ");
				int i = ctrl.opcao();
				Transacao t  = caixa.remover(semana, i-1);
				if(t!= null) {
					caixa.remover_transacao_arq(t.toCSV(), usuario.getCaixa());
					System.out.println("Transacao removida com sucesso");
					return;
				}
			}
			System.out.println("Semana inválida, tente novamente");
		}
	}
	
	public void sobre() {
		System.out.println("Fluxo de caixa");
		System.out.println("Atividade de Programação Orientada a Objetos");
		System.out.println("FATEC - SJC - Prof. Me. Eng. Gerson Neto");
		System.out.println("Autores:");
		System.out.println(" * Gabriel Mendes (http://github.com/gmendess)");
		System.out.println(" * Gabriel Borges (http://github.com/gfborges)");
	}
}
