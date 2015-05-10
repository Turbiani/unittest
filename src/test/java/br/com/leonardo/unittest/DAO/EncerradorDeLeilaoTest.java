package br.com.leonardo.unittest.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import br.com.leonardo.unittest.EncerradorDeLeilao;
import br.com.leonardo.unittest.TDB.CriadorDeLeilao;
import br.com.leonardo.unittest.dominio.Leilao;

public class EncerradorDeLeilaoTest {
	
	@Test
	public void deveEncerrarLeiloesQueComecaramUmaSemanaAtras(){
		Calendar antiga = Calendar.getInstance();
		antiga.set(1999, 1, 20);
		
		Leilao leilao1 = new CriadorDeLeilao()
			.para("TV de Plasma")
		    .naData(antiga).constroi();
		
		Leilao leilao2 = new CriadorDeLeilao()
		.para("Geladeira")
	    .naData(antiga).constroi();
		
		LeilaoDAO leilaoDAO = new LeilaoDAO();
		leilaoDAO.salva(leilao1);
		leilaoDAO.salva(leilao2);
		
		//mas como passo os leiloes criados para o EncerradorDeLeiloes,
		//ja que ele busca no DAO ?
		//Vou simular a infra, simulando gravar na base, simulando o select
		
				
		EncerradorDeLeilao encerrador = new EncerradorDeLeilao(leilaoDAO);
		encerrador.encerra();
		
		List<Leilao> encerrados = leilaoDAO.encerrados();
		
		//vamos aproveitar para verificar se o tamanho da lista esta OK
		assertEquals(2, encerrados.size());
		assertTrue(leilao1.isEncerrado());
		assertTrue(leilao2.isEncerrado());
	}
	
}
