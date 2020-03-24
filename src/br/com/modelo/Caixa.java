package br.com.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Caixa{
	private static final String CABECALHO = 
			   "\n+------------------------------  RELATORIO  -------------------------------+\n" +
			   "+------------------------------- %s -------------------------------+\n"  +
			   "+------------- ENTRADA --------------+--------------- SAIDA ---------------+\n";
	private static final String CABECALHO_MENSAL = 
			   "\n+------------------------------  RELATORIO  -------------------------------+\n" +
			   "+-------------------------------- %s ---------------------------------+\n"  +
			   "+------------- ENTRADA --------------+--------------- SAIDA ---------------+\n";
	private static final String RODAPE =
			   "+--------------------------------------------------------------------------+\n";
	
	private Hashtable< String, List<Transacao> > registro = new Hashtable< String, List<Transacao> >();
	
	public Caixa(String arquivo) throws IOException{
		this.fromCSV(arquivo);
	}
	
	public void novo_registro(String key, Transacao t){
		if(registro.get(key) == null){
			List<Transacao> l1 = new ArrayList<Transacao>();
			registro.put(key, l1);
		}
		adiciona_ordenado(key, t);
	}
	
	private void adiciona_ordenado(String key, Transacao t) {
		List<Transacao> registro = this.registro.get(key);
		if(registro.isEmpty()) { // Lista vaiza = insere
			this.registro.get(key).add(t);
		}
		else if( ( t.compareTo(registro.get(0)) ) < 0 ) { // Não tem predecessor insere no começo
			this.registro.get(key).add(0,t);
		}
		else { // Busca binaria por predecesor
			int l = 0, r = registro.size() - 1, m;
			int comp;
			while(l < r) {
				m = (l + r + 1) / 2;
				comp = t.compareTo( registro.get(m) );
				if(comp <= 0)
					r = m - 1;
				else 
					l = m;
			}
			this.registro.get(key).add(l+1, t);
		}
	}


	public Transacao get(String key, int i){
		return registro.get(key).get(i);
	}
	
	private String relatorio_corpo(List<Transacao> entradas, List<Transacao> saidas) {
		String curr, s = "";
		final int ent_size = entradas.size();
		final int saidas_size = saidas.size();
		
		for(int i = 0; i < ent_size || i < saidas_size; ++i) {
			
			if(i < ent_size)
				curr = entradas.get(i).toString();
			else
				curr = "";
			s += String.format("|%-35s |", curr);
			
			if(i < saidas_size)
				curr = saidas.get(i).toString();
			else
				curr = "";
			s += String.format("%-37s|\n", curr);
		}
		
		return s;
	}
	
	private String relatorio_rodape(double total_ents, double total_saida) {
		String s = "";
		String total = String.format("%.2f", total_ents);
		s += "| TOTAL" + String.format("%29s |", total);
		total = String.format("%.2f", total_saida );
		s += " TOTAL" + String.format("%30s |", total) + "\n";
		total = String.format("%.2f", total_ents  + total_saida);
		s += String.format("| LUCRO %-67s|%n", total);
		s += RODAPE;
		return s;
	}
	
	public String relatorio_semanal(String key) {
		List<Transacao> semana = registro.get(key);
		if(semana == null)
			return "";
		
		List<Transacao> entradas =  new ArrayList<Transacao>();
		List<Transacao> saidas = new ArrayList<Transacao>();
		double total_ents = 0.0, total_saida = 0.0;
		
		for(Transacao t : semana ) {
			if(t.isEntrada()) {
				entradas.add(t);
				total_ents += t.valueOf();
			}
			else {
				saidas.add(t);
				total_saida += t.valueOf();
			}
		}
		
		String s = String.format(CABECALHO, key);
		s += relatorio_corpo(entradas, saidas);
		s += relatorio_rodape(total_ents, total_saida);
		
		return s;
	}
	
	public String relatorio_mensal(String mes){
		String key, s;
		s = String.format(CABECALHO_MENSAL, mes);
		
		double total_ents = 0.0, total_saida = 0.0;
		List<Transacao> entradas = new ArrayList<Transacao>();
		List<Transacao> saidas = new ArrayList<Transacao>();
		
		for(int sem = 1; sem <= 4; ++sem) {
			key = mes + String.format("-%02d", sem);
			
			if(registro.get(key) == null)
				continue;
			
			for(Transacao t : registro.get(key)) {
				if(t.isEntrada()) {
					entradas.add(t);
					total_ents += t.valueOf();
				}
				else {
					saidas.add(t);
					total_saida += t.valueOf();
				}
			}
		}
		
		s += relatorio_corpo(entradas, saidas);
		s += relatorio_rodape(total_ents, total_saida);
		
		return s;
	}
	
	public void fromCSV(String nome_arquivo) throws IOException {
		BufferedReader arquivo;
		Transacao t;
		try {
			arquivo = new BufferedReader( new FileReader(nome_arquivo) );
		}catch(IOException e) {
			return;
		}
		String line = arquivo.readLine();
		while(line != null) {
			String[] row  = line.split(",");
			double valor = Double.parseDouble(row[3]);
			boolean tipo = ( row[4].equals("1") ) ? true : false;
			if(tipo)
				t = new Entrada(row[1], valor, row[2]);
			else
				t = new Saida(row[1], valor, row[2]);
			novo_registro(row[0], t);
			line = arquivo.readLine();
		}
		arquivo.close();
	}
}
