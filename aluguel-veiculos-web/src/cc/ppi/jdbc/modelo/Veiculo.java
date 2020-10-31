package cc.ppi.jdbc.modelo;

public class Veiculo {
	
//	Atritubos
	private String renavan;
	private String nome;
	private String categoria;
	private String ano;
	private boolean locado;

//	Getters and Setters
	
	public String getRenavan() {
		return renavan;
	}
	public void setRenavan(String renavan) {
		this.renavan = renavan;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public boolean isLocado() {
		return locado;
	}
	public void setLocado(boolean locado) {
		this.locado = locado;
	}

}
