package br.com.leonardo.unittest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import br.com.leonardo.unittest.EncerradorDeLeilao;
import br.com.leonardo.unittest.DAO.LeilaoDAO;
import br.com.leonardo.unittest.TDB.CriadorDeLeilao;
import br.com.leonardo.unittest.dominio.Carteiro;
import br.com.leonardo.unittest.dominio.Leilao;

public class EncerradorDeLeilaoTest {
	
	@Test
	public void deveEncerrarLeiloesQueComecaramUmaSemanaAtras(){
		Carteiro carteiroFalso = mock(Carteiro.class);
		
		LeilaoDAO leilaoDAO = mock(LeilaoDAO.class);
		
		Calendar antiga = Calendar.getInstance();
		antiga.set(1999, 1, 20);
		
		Leilao leilao1 = new CriadorDeLeilao()
			.para("TV de Plasma")
		    .naData(antiga).constroi();
		
		Leilao leilao2 = new CriadorDeLeilao()
		.para("Geladeira")
	    .naData(antiga).constroi();
		

		//criando o mock
		List<Leilao> leiloesAntigos = Arrays.asList(leilao1, leilao2);
		//ensinando o mock a reagir da maneira que esperamos!
		when(leilaoDAO.correntes()).thenReturn(leiloesAntigos);
		
		/*leilaoDAO.salva(leilao1);
		leilaoDAO.salva(leilao2);*/
		
		//mas como passo os leiloes criados para o EncerradorDeLeiloes,
		//ja que ele busca no DAO ?
		//Vou simular a infra, simulando gravar na base, simulando o select
		
				
		EncerradorDeLeilao encerrador = new EncerradorDeLeilao(leilaoDAO, carteiroFalso);
		encerrador.encerra();
		
		
		//vamos aproveitar para verificar se o tamanho da lista esta OK
		assertEquals(2, encerrador.getTotalEncerrados());
		assertTrue(leilao1.isEncerrado());
		assertTrue(leilao2.isEncerrado());
		
		//verificando se o metodo atualiza foi devidamente invocado ao se chamar o encerra()
		verify(leilaoDAO).atualiza(leilao2);
		
		//verificando se alem de ser chamado, quantas vezes ele deveria ter sido chamado ?
		//no nosso caso o encerra chama o dao.atualiza cada vez que percorre o foreach de acordo com a quantidade de leiloes
		//quando ele chama o atualiza, ele passa a instancia do leilao corrente, sendo assim para cada instancia o atualiza
		//é invocado uma vez...e e isso que vamos testar
		
		verify(leilaoDAO, times(1)).atualiza(leilao1);
		
		//verificando se foi chamado pelo menos uma vez
		verify(leilaoDAO, atLeastOnce()).atualiza(leilao2);
		
		//aqui passo o numero exato de vezes que o metodo deve ser chamado
		verify(leilaoDAO, atLeast(1)).atualiza(leilao1);
		
		//verificando o máximo de vezes que o metodo deveria ser chamado
		verify(leilaoDAO, atMost(1)).atualiza(leilao1);
	}
	
	@Test
	public void deveContinuarAExecucaoMesmoQuandoDaoFalha(){
		Calendar antiga = Calendar.getInstance();
		antiga.set(1999, 1, 20);
		
		Leilao leilao1 = new CriadorDeLeilao()
			.para("TV de Plasma")
		    .naData(antiga).constroi();
		
		Leilao leilao2 = new CriadorDeLeilao()
		.para("Geladeira")
	    .naData(antiga).constroi();
		
		LeilaoDAO daoFalso = mock(LeilaoDAO.class);
		when(daoFalso.correntes()).thenReturn(Arrays.asList(leilao1, leilao2));
		
		//quero que uma runtime seja lençada quando o atualiza receber a instancia leilao1
		doThrow(new RuntimeException()).when(daoFalso).atualiza(leilao1);
		
		Carteiro carteiroFalso = mock(Carteiro.class);
		
		EncerradorDeLeilao encerrador = new EncerradorDeLeilao(daoFalso, carteiroFalso);
		
		encerrador.encerra();
		
		verify(daoFalso).atualiza(leilao2);
		verify(carteiroFalso).envia(leilao2);
	}
	
}
