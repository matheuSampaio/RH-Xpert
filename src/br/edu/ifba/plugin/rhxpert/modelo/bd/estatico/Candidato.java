package br.edu.ifba.plugin.rhxpert.modelo.bd.estatico;

public class Candidato {
	
	private int id;
	private String cpf;
	private String nome;
	private String email;
	private Endereco endereco = new Endereco();
	private String telefone;
	private String formacao;
	private String escolaridade;
	private boolean ativo;
	
	
	public int getId() {
		return id;
	}
	public String getCpf() {
		return cpf;
	}
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getFormacao() {
		return formacao;
	}
	public String getEscolaridade() {
		return escolaridade;
	}
	public boolean getAtivo() {
		return ativo;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}
	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	

}
