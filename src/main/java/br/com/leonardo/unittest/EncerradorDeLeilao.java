package br.com.leonardo.unittest;

import java.util.Calendar;
import java.util.List;

import br.com.leonardo.unittest.DAO.LeilaoDAO;
import br.com.leonardo.unittest.dominio.Carteiro;
import br.com.leonardo.unittest.dominio.Leilao;

public class EncerradorDeLeilao {
	
	private int total = 0;
	private final LeilaoDAO dao;
	private final Carteiro carteiro;
	
	public EncerradorDeLeilao(LeilaoDAO leilaoDAO, Carteiro carteiro) {
		this.dao 		= leilaoDAO;
		this.carteiro 	= carteiro;
	}

	public void encerra(){
		List<Leilao> todosLeiloesCorrentes = dao.correntes();
		
		for(Leilao leilao: todosLeiloesCorrentes){
			try{
				if(comecouSemanaPassada(leilao)){
					leilao.encerra();
					total++;
					dao.atualiza(leilao);
					
					//agora enviamos email
					carteiro.envia(leilao);
				}
			}catch(Exception e){
				//salvo a exceção e o loop continuas
			}
		}
		
	}

	private boolean comecouSemanaPassada(Leilao leilao) {
		return diasEntre(leilao.getData(), Calendar.getInstance()) >= 7;
	}

	private int diasEntre(Calendar inicio, Calendar fim) {
		Calendar data = (Calendar) inicio.clone();
		int diasNoIntervalo = 0;
		while(data.before(fim)){
			data.add(Calendar.DAY_OF_MONTH, 1);
			diasNoIntervalo++;
		}
		return diasNoIntervalo;
	}
	
	public int getTotalEncerrados(){
		return total;
	}
	
}
