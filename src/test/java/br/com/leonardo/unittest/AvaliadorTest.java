package br.com.leonardo.unittest;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;	

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.leonardo.unittest.TDB.CriadorDeLeilao;
import br.com.leonardo.unittest.dominio.Lance;
import br.com.leonardo.unittest.dominio.Leilao;
import br.com.leonardo.unittest.dominio.Usuario;

/**
 * @author turbiani
 *
 */
public class AvaliadorTest {
	
	private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;

    @Before
    public void criaAvaliador() {
        this.leiloeiro = new Avaliador();
        this.joao = new Usuario("João");
        this.jose = new Usuario("José");
        this.maria = new Usuario("Maria");
    }
	
	@Test
    public void deveEntenderLancesEmOrdemCrescente() {

        Leilao leilao = new CriadorDeLeilao()
            .para("Playstation 3 Novo")
            .lance(joao, 250)
            .lance(jose, 300)
            .lance(maria, 400)
            .constroi();

        leiloeiro.avalia(leilao);
        
        //USANDO O HAMCREST
        assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
        assertThat(leiloeiro.getMenorLance(), equalTo(250.0));
        // assertThat(leiloeiro.getMenorLance(), equalTo(250.0));
        //SEM O USO DA BIBLIOTECA HAMCREST
        //assertEquals(400.0, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(800.0, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(250.0, leiloeiro.getMenorLance(), 0.00001);
    }
	
	@Test
    public void deveEncontrarOsTresMaioresLances() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 100.0)
                .lance(maria, 200.0)
                .lance(joao, 300.0)
                .lance(maria, 400.0)
                .constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        
        //USANDO O HAMCREST
        assertThat(maiores, hasItems(
        		new Lance(maria, 400),
        		new Lance(joao, 300),
        		new Lance(maria, 200)
        		));
        
        assertThat(maiores.get(0).getValor(), equalTo(400.0));
        assertThat(maiores.get(1).getValor(), equalTo(300.0));
        assertThat(maiores.get(2).getValor(), equalTo(200.0));
        //SEM O USO DA BIBLIOTECA HAMCREST
        assertEquals(3, maiores.size());
        assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
        assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
        assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
    }
	
	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
	    Leilao leilao = new CriadorDeLeilao()
	        .para("Playstation 3 Novo")
	        .constroi();

	    leiloeiro.avalia(leilao);
	}
	
	

}
