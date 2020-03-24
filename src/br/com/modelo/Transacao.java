package br.com.modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

public class Transacao{
	  protected String data;
	  protected String tipo;
	  protected double valor;
	  protected final boolean ent_saida; // Entrada (T) / Saida (F)
	  
	  public Transacao(String data, double valor, boolean ent_saida, String tipo){
	    this.data = data;
	    this.tipo = tipo;
	    this.valor = valor;
	    this.ent_saida = ent_saida;
	  }
	  
	  public String getData() {
		return data;
	  }

	  public void setData(String data) {
		this.data = data;
	  }
	
	  public String getTipo() {
		  return tipo;
	  }

	  public void setTipo(String tipo) {
		  this.tipo = tipo;
	  }

	  public double getValor() {
		  return valor;
	  }

	  public void setValor(double valor) {
		  this.valor = valor;
	  }

	  public boolean isEntrada() {
		  return ent_saida;
	  }

	  public boolean isSaida ( ) {
		  return !ent_saida;
	  }

	  public String toString(int spc){
		  String s = this.tipo;
		  String val = String.format("%.2f", valor);
		  s += String.format("%" + (spc - tipo.length())+ "s" , val);
		  return s;
	  }

	  public double valueOf(){
	    return valor;
	  }
	  
	  public boolean equals(Object o) {
		  if(o == this)
			  return true;
		  if(!(o instanceof Transacao))
			  return false;
		  Transacao t = (Transacao) o;
		  return (this.tipo.equals(t.getTipo()) && 
				  this.valor == t.getValor() && 
				  this.data.equals(t.getData()));
	  }
	  
	  public static String getKey(String data) {
		  String ano, mes, semana;
		  LocalDate dt = LocalDate.parse(data);
		  
		  ano = Integer.toString( dt.getYear() );
		  mes = String.format("%02d", dt.getMonthValue() );
		  semana = String.format("%02d", dt.getDayOfMonth() / 7 + 1 );
		  
		  return String.format("%s-%s-%s", ano, mes, semana);
		  
	  }
	  
	  public void toCSV(String arquivo) throws IOException {
		  boolean saving = true;
		  int saida = (ent_saida)? 1 :0;
		  String key = getKey(data);
		  String linha = key  + "," +
				  		 data + "," +
				  		 tipo + "," +
				  		 valor+ "," +
				  		 saida+ "\n";
		  
		  while(saving) {
			  try {
					Files.write(Paths.get(arquivo), linha.getBytes(), StandardOpenOption.APPEND);
					return;
				}catch(IOException e){
					FileWriter file = new FileWriter(arquivo);
					file.close();
					saving = !saving;
				}
			  saving = !saving;
		  }
	  }
	}