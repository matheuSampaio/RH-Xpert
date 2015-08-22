package br.edu.ifba.plugin.rhxpert.modelo.bd.estatico;

public class Endereco {
	
	private String cidade;
	private String uf;
	private String cep;
	private String endereco;
	private String numero;
	private String bairro;
	
	public Endereco() {}
	
	public Endereco(String cidade, String uF, String cEP, String endereco, String numero, String bairro) {
		this.cidade = cidade;
		this.uf = uF;
		this.cep = cEP;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
	}
	
	
	public String getCidade() {
		return cidade;
	}
	public String getUf() {
		return uf;
	}
	public String getCep() {
		return cep;
	}
	public String getEndereco() {
		return endereco;
	}
	public String getNumero() {
		return numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	
	@Override
	public String toString() {
		return endereco+", "+numero+" "+cep+" - "+bairro+", "+cidade+" "+uf;
	}
	
	

}
