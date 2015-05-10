package br.com.leonardo.unittest.TDB;

import java.util.Calendar;

import br.com.leonardo.unittest.dominio.Lance;
import br.com.leonardo.unittest.dominio.Leilao;
import br.com.leonardo.unittest.dominio.Usuario;

public class CriadorDeLeilao {
	private Leilao leilao;

    public CriadorDeLeilao() { }

    public CriadorDeLeilao para(String descricao) {
        this.leilao = new Leilao(descricao);
        return this;
    }

    public CriadorDeLeilao lance(Usuario usuario, double valor) {
        leilao.propoe(new Lance(usuario, valor));
        return this;
    }
    
    public CriadorDeLeilao naData(Calendar data){
    	leilao.setData(data);
		return this;
    }
    
    public Leilao constroi() { 
        return leilao;
    }
}
