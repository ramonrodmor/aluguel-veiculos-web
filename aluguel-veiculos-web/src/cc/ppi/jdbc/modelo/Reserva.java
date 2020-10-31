package cc.ppi.jdbc.modelo;

import java.util.Calendar;

public class Reserva {

//	Atributos
	private String codigo;
	private String renavan;
	private String cpf;
	private Calendar rdata;
	private String rhora;
	private Calendar ddata;
	private String dhora;
	private float valor;

//	Getters e Setters
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String string) {
		this.codigo = string;
	}
	public String getRenavan() {
		return renavan;
	}
	public void setRenavan(String renavan) {
		this.renavan = renavan;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Calendar getRdata() {
		return rdata;
	}
	public void setRdata(Calendar rdata) {
		this.rdata = rdata;
	}
	public String getRhora() {
		return rhora;
	}
	public void setRhora(String rhora) {
		this.rhora = rhora;
	}
	public Calendar getDdata() {
		return ddata;
	}
	public void setDdata(Calendar ddata) {
		this.ddata = ddata;
	}
	public String getDhora() {
		return dhora;
	}
	public void setDhora(String dhora) {
		this.dhora = dhora;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}

}
