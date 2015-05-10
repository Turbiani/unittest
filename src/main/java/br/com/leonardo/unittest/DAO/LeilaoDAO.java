package br.com.leonardo.unittest.DAO;

import java.util.ArrayList;
import java.util.List;

import br.com.leonardo.unittest.dominio.Leilao;

public class LeilaoDAO {
	
	private static List<Leilao> leiloes = new ArrayList<Leilao>();
	
	public void atualiza(Leilao leilao) {
		//sem acao
	}
	
	public void salva(Leilao leilao){
		leiloes.add(leilao);
	}
	
	public List<Leilao> encerrados(){
		List<Leilao> filtrados = new ArrayList<Leilao>();
		for(Leilao leilao: leiloes){
			if(leilao.isEncerrado()){
				filtrados.add(leilao);
			}
		}
		
		return filtrados;
	}
	
	public List<Leilao> correntes(){
		List<Leilao> filtrados = new ArrayList<Leilao>();
		for(Leilao leilao: leiloes){
			if(!leilao.isEncerrado()){
				filtrados.add(leilao);
			}
		}
		
		return filtrados;
	}

}
