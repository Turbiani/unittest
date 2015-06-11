package br.com.leonardo.unittest.dominio;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;*/

//@Entity
public class Usuario2 {
	
	//@Id @GeneratedValue
	private int id;
	private String nome;
	private String email;

	protected Usuario2() {}
	
	public Usuario2(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
