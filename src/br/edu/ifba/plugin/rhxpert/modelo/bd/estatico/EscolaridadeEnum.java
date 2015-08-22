package br.edu.ifba.plugin.rhxpert.modelo.bd.estatico;

public enum EscolaridadeEnum {
	
	FUNDAMENTAL (0, "Ensino Fundamental"), 
	MEDIO  (1, "Ensino Médio");
	
	protected Integer id;
	protected String nome;
	
	private EscolaridadeEnum() {
	}
	
	private EscolaridadeEnum(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Integer getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}

}
