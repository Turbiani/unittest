package br.com.leonardo.unittest.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LeilaoTest {
	
	@Test
	public void deveReceberUmLance(){
		Leilao leilao = new Leilao("Macbook Por 15");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLances(){
		Leilao leilao = new Leilao("Macbook Por 15");
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
		leilao.propoe(new Lance(new Usuario("Steve Wozniak"), 3000));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000, leilao.getLances().get(1).getValor(), 0.00001);
		
	}
	
	
	//TODO - IMPLANTAR TESTES PARA NOVAS FUNCIONALIDADES - DEPOIS IMPLANTAR UM MODULO WEB A ISSO AQUI
	//TODO - PARA QUE EU CONSIGA INTEGRAR O JENKINS COM O HEROKU E FECHAR O TESTE INTEGRADO:
	//TODO - DEVELOPMENT, BUILD/TEST, DEPLOY
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario(){
		Leilao leilao = new Leilao("Macbook Pro 15");
		Usuario usuario = new Usuario("Steve Jobs");
		
		leilao.propoe(new Lance(usuario, 2000));
		leilao.propoe(new Lance(usuario, 3000));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
		
	}
	
	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario(){
		Leilao leilao = new Leilao("Macbook Pro 15");
		Usuario stevejobs = new Usuario("Steve Jobs");
		Usuario billGates = new Usuario("Bill Gates");
		
		leilao.propoe(new Lance(stevejobs, 2000));
		leilao.propoe(new Lance(billGates, 3000));
		leilao.propoe(new Lance(stevejobs, 4000));
		leilao.propoe(new Lance(billGates, 5000));
		leilao.propoe(new Lance(stevejobs, 6000));
		leilao.propoe(new Lance(billGates, 7000));
		leilao.propoe(new Lance(stevejobs, 8000));
		leilao.propoe(new Lance(billGates, 9000));
		leilao.propoe(new Lance(billGates, 10000));
		leilao.propoe(new Lance(stevejobs, 11000));
		
		//deve ser ignorado
		leilao.propoe(new Lance(stevejobs, 12000));
		
		assertEquals(10, leilao.getLances().size());
		
		int ultimo = leilao.getLances().size() - 1;
		Lance ultimoLance = leilao.getLances().get(ultimo);
		
		//TODO - IMPLANTAR ESSA CORRECAO NA CLASSE TESTADA
		assertEquals(11000, ultimoLance.getValor(), 0.00001);
		
	}
	
	
}
