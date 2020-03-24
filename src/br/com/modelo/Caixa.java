package br.com.modelo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Caixa{
	private static final String CABECALHO = 
			   "\n+-----------------------------  RELATORIO  ------------------------------+\n" +
			   "+----------------------------- %s -------------------------------+\n"  +
			   "+------------- ENTRADA ---------+------------------- SAIDA --------------+\n";
	private static final String RODAPE =
			   "+------------------------------------------------------------------------+\\n";
	private Hashtable< String, List<Transacao> > registro = new Hashtable< String, List<Transacao> >();

	public void novo_registro(String key, Transacao t){
		if(registro.get(key) == null){
			List<Transacao> l1 = new ArrayList<Transacao>();
			registro.put(key, l1);
		}
		registro.get(key).add(t);
	}

	public Transacao get(String key, int i){
		return registro.get(key).get(i);
	}
	
	public String relatorio_semanal(String key) {
		List<Transacao> semana = registro.get(key);
		if(semana == null)
			return "";
		
		List<Transacao> entradas =  new ArrayList<Transacao>();
		List<Transacao> saidas = new ArrayList<Transacao>();
		for(Transacao t : semana ) {
			if(t.isEntrada())
				entradas.add(t);
			else
				saidas.add(t);
		}
		
		String curr;
		Double total_ents = 0.0, total_saida = 0.0;
		final int ent_size = entradas.size();
		final int saidas_size = saidas.size();
//		+-----------------------------  RELATORIO  ------------------------------+
//		+--------------------------- %s ------------------------------+
//		+------------- ENTRADA -------------+--------------- SAIDA --------------+

		String s = String.format(CABECALHO, key);
		
		for(int i = 0; i < ent_size || i < saidas_size; ++i) {
			if(i < ent_size) {
				curr = entradas.get(i).toString();
				total_ents += entradas.get(i).valueOf();
			}
			else
				curr = "";
			s += String.format("|%-30s |", curr);
			if(i < saidas_size) {
				curr = saidas.get(i).toString();
				total_saida += saidas.get(i).valueOf();
			}
			else
				curr = "";
			s += String.format("%-40s|\n", curr);
		}
		
		String total = String.format("%.2f", total_ents);
		s += "| TOTAL" + String.format("%24s |", total);
		total = String.format("%.2f", total_saida );
		s += " TOTAL" + String.format("%34s|", total) + "\n";
		total = String.format("%.2f", total_ents  + total_saida);
		s += String.format("| LUCRO %-65s|%n", total);
		s += RODAPE;
		
		return s;
	}
	
}
