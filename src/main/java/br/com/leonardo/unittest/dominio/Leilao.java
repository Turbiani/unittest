package br.com.leonardo.unittest.dominio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	private Calendar    data;
	private boolean     encerrado;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		int total = 0;
		
		for(Lance l : this.lances){
			if(l.getUsuario().equals(lance.getUsuario())) total ++;
		}
		
		if(lances.isEmpty() ||
			(!ultimoLanceDado().getUsuario()
			.equals(lance.getUsuario())
			 && total < 5)){
			
			lances.add(lance);
			
		}
	}

	private Lance ultimoLanceDado() {
		return lances.get(lances.size()-1);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	public void encerra() {
		this.encerrado = true;
	}
	
	public boolean isEncerrado(){
		return this.encerrado;
	}

	public Calendar getData() {
		return this.data;
	}

	public void setData(Calendar data){
		this.data = data;
	}
	
}
