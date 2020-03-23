package br.com.modelo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Caixa{
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
		Double ent = 0.0, saida = 0.0;
		final int ent_size = entradas.size();
		final int saidas_size = saidas.size();
		
		String s = "\n+------------ RELATORIO ------------+\n"
				 +   "+----------- " + key + " -----------+\n";
		for(int i = 0; i < ent_size || i < saidas_size; ++i) {
			if(i < ent_size) {
				curr = entradas.get(i).toString();
				ent += entradas.get(i).valueOf();
			}
			else
				curr = "";
			s += String.format("|%-30s |", curr);
			if(i < saidas_size) {
				curr = saidas.get(i).toString();
				saida += saidas.get(i).valueOf();
			}
			else
				curr = "";
			s += String.format("%-40s|\n", curr);
		}
		s += "|TOTAL" + String.format("%25s | ", ent.toString());
		s += "TOTAL" + String.format("%35s|", saida.toString()) + "\n";
		return s;
	}
}
