package br.com.leonardo.unittest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Calendar;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import br.com.leonardo.unittest.DAO.LeilaoDAO;
import br.com.leonardo.unittest.TDB.CriadorDeLeilao;
import br.com.leonardo.unittest.dominio.Leilao;
import br.com.leonardo.unittest.dominio.Pagamento;
import br.com.leonardo.unittest.dominio.RepositorioDePagamentos;
import br.com.leonardo.unittest.dominio.Usuario;

public class GeradorDePagamentoTest {

	@Test
	public void deveGerarPagamentoParaUmLeilaoEncerrado() {
		LeilaoDAO leilaoDAO = mock(LeilaoDAO.class);
		RepositorioDePagamentos pagamentos =  mock(RepositorioDePagamentos.class);
		Avaliador avaliador = mock(Avaliador.class);
		
		Leilao leilao = new CriadorDeLeilao()
			.para("Playstation")
			.lance(new Usuario("Jose da Silva"), 2000.0)
			.lance(new Usuario("Maria pereira"), 3000.0)
			.constroi();
		
		when(leilaoDAO.encerrados()).thenReturn(Arrays.asList(leilao));
		when(avaliador.getMaiorLance()).thenReturn(3000.0);
		
		GeradorDePagamento gerador = new GeradorDePagamento(pagamentos, 
				leilaoDAO, 
				avaliador);
		
		gerador.gera();
		
		//preciso pegar a instancia de pagamento criada dentro do metodo gera(), assim poderei validar se criou com o valor do meu
		//maior lance
		
		//criamos o ArgumentCaptor que sabe capturar um Pagamento
		ArgumentCaptor<Pagamento> argumento = ArgumentCaptor.forClass(Pagamento.class);
		//capturamos o pagamento que foi passado para o metodo salvar;
		verify(pagamentos).salva(argumento.capture());
		
		//agora peguei a instancia de pagamento que foi passada ao metodo salva de repositorio de pagamentos no metodo gera() do gerador
		//de pagamentos
		
		Pagamento pagamento = argumento.getValue();
		//Deve ter gerado pagamento para o maior lance
		assertEquals(3000.0, pagamento.getValor(), 0.0001);
		
		
	}
	
	@Test
	public void deveEmpurrarParaProximoDiaUtil() {
		LeilaoDAO leilaoDAO = mock(LeilaoDAO.class);
		RepositorioDePagamentos pagamentos =  mock(RepositorioDePagamentos.class);
		Avaliador avaliador = mock(Avaliador.class);
		
		Leilao leilao = new CriadorDeLeilao()
			.para("Playstation")
			.lance(new Usuario("Jose da Silva"), 2000.0)
			.lance(new Usuario("Maria pereira"), 3000.0)
			.constroi();
		
		when(leilaoDAO.encerrados()).thenReturn(Arrays.asList(leilao));
		when(avaliador.getMaiorLance()).thenReturn(3000.0);
		
		GeradorDePagamento gerador = new GeradorDePagamento(pagamentos, 
				leilaoDAO, 
				avaliador);
		
		gerador.gera();
		
		//preciso pegar a instancia de pagamento criada dentro do metodo gera(), assim poderei validar se criou com o valor do meu
		//maior lance
		
		//criamos o ArgumentCaptor que sabe capturar um Pagamento
		ArgumentCaptor<Pagamento> argumento = ArgumentCaptor.forClass(Pagamento.class);
		//capturamos o pagamento que foi passado para o metodo salvar;
		verify(pagamentos).salva(argumento.capture());
		
		//agora peguei a instancia de pagamento que foi passada ao metodo salva de repositorio de pagamentos no metodo gera() do gerador
		//de pagamentos
		
		Pagamento pagamento = argumento.getValue();
		//Deve ter gerado pagamento para o maior lance
		assertEquals(3000.0, pagamento.getValor(), 0.0001);
		
		//Verificando se o pagamento foi estendido caso o pagamento esteja sendo realizado no final de semana
		assertEquals(Calendar.MONDAY, pagamento.getData().get(Calendar.DAY_OF_WEEK));
				
	}

}
