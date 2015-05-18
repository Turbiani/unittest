package br.com.leonardo.unittest;

import java.util.Calendar;
import java.util.List;

import br.com.leonardo.unittest.DAO.LeilaoDAO;
import br.com.leonardo.unittest.dominio.Leilao;
import br.com.leonardo.unittest.dominio.Pagamento;
import br.com.leonardo.unittest.dominio.RepositorioDePagamentos;

public class GeradorDePagamento {
	
	private final RepositorioDePagamentos pagamentos;
	private final LeilaoDAO leilaoDAO;
	private final Avaliador avaliador;
	
	public GeradorDePagamento(RepositorioDePagamentos pagamentos,
			LeilaoDAO leilaoDAO, Avaliador avaliador) {
		super();
		this.pagamentos = pagamentos;
		this.leilaoDAO = leilaoDAO;
		this.avaliador = avaliador;
	}
	
	public void gera(){
		List<Leilao> leiloesEncerrados = leilaoDAO.encerrados();
		
		for (Leilao leilao : leiloesEncerrados) {
			avaliador.avalia(leilao);
			
			Pagamento novoPagamento =
					new Pagamento(avaliador.getMaiorLance(), 
							primeiroDiaUtil());
			pagamentos.salva(novoPagamento);
		}
	}

	private Calendar primeiroDiaUtil() {
		Calendar calendario = Calendar.getInstance();
		int diaDaSemana     = calendario.get(Calendar.DAY_OF_WEEK);
		
		if(diaDaSemana == Calendar.SATURDAY){
			calendario.add(Calendar.DAY_OF_MONTH, 2);
		}else if(diaDaSemana == Calendar.SUNDAY){
			calendario.add(Calendar.DAY_OF_MONTH, 1);
		}
		return calendario;
	}

}
