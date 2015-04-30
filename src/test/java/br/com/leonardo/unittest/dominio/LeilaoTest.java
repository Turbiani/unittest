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
	
}
