package br.com.leonardo.unittest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.leonardo.unittest.DAO.LeilaoDAO;
import br.com.leonardo.unittest.dominio.Leilao;

public class EncerradorDeLeilao {
	
	private int total = 0;
	
	public void encerra(){
		LeilaoDAO dao = new LeilaoDAO();
		List<Leilao> todosLeiloesCorrentes = new ArrayList<Leilao>();
		
		for(Leilao leilao: todosLeiloesCorrentes){
			if(comecouSemanaPassada(leilao)){
				leilao.encerra();
				total++;
				dao.atualiza(leilao);
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
