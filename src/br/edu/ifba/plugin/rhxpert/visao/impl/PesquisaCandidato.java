package br.edu.ifba.plugin.rhxpert.visao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.edu.ifba.plugin.rhxpert.controle.ControleCandidato;
import br.edu.ifba.plugin.rhxpert.modelo.ModeloCandidato;
import br.edu.ifba.plugin.rhxpert.modelo.bd.estatico.Candidato;
import br.edu.ifba.plugin.rhxpert.visao.IPesquisaCandidato;

@ManagedBean(name="p_candidato")
@ViewScoped
public class PesquisaCandidato implements IPesquisaCandidato {
	
	private String erro;
	private String sucesso;
	
	private String id = "";
	private String cpf = "";
	private String email = "";
	private String nome = "";
	
	private List<Candidato> candidatosEncontrados = new ArrayList<Candidato>();

	
	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void pesquisar() {
		erro = null;
		
		ModeloCandidato modelo = new ModeloCandidato();
		ControleCandidato controle = new ControleCandidato();
		
		controle.setModeloCandidato(modelo);
		controle.setPesquisaCandidato(this);
		
		controle.pesquisar();
	}

	@Override
	public void atualizarCandidatosEncontrados(List<Candidato> candidatos) {
		this.candidatosEncontrados = candidatos;
	}
	public List<Candidato> getCandidatos() {
		return candidatosEncontrados;
	}
	
	public void ver(String id){
		exibirCadastro(id);
	}
	
	public void remover(String id){
		sucesso = null;
		erro = null;
		
		this.id = id;
		ModeloCandidato modelo = new ModeloCandidato();
		ControleCandidato controle = new ControleCandidato();
		
		controle.setModeloCandidato(modelo);
		controle.setPesquisaCandidato(this);
		
		controle.remover();
	}
	
	public String getErro() {
		return erro;
	}

	public String getSucesso() {
		return sucesso;
	}
	
	public void adicionar(){
		exibirCadastro("");
	}
	
	@Override
	public void notificarCandidatosNaoEncontrados() {
		erro = "Nenhum registro encontrado.";
	}

	@Override
	public void notificarCandidatoRemovido() {
		sucesso = "Registro excluído com sucesso.";
	}

	@Override
	public void notificarErroRemocao() {
		erro = "Não foi possível excluir o registro.";
	}
	
	private void exibirCadastro(String id){
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		context.getSessionMap().put("idCandidato", id);
		try{
			context.redirect("cadastro_candidato.ifba");
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
