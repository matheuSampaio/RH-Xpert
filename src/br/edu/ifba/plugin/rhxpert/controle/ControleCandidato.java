package br.edu.ifba.plugin.rhxpert.controle;

import br.edu.ifba.plugin.rhxpert.modelo.ModeloCandidato;
import br.edu.ifba.plugin.rhxpert.visao.ICadastroCandidato;
import br.edu.ifba.plugin.rhxpert.visao.IPesquisaCandidato;

public class ControleCandidato {
	
	private IPesquisaCandidato pesquisaCandidato;
	private ICadastroCandidato cadastroCandidato;
	private ModeloCandidato modeloCandidato;
	
	public void setPesquisaCandidato(IPesquisaCandidato pesquisaCandidato) {this.pesquisaCandidato = pesquisaCandidato;}
	public void setCadastroCandidato(ICadastroCandidato cadastroCandidato) {this.cadastroCandidato = cadastroCandidato;}
	public void setModeloCandidato(ModeloCandidato modeloCandidato) {this.modeloCandidato = modeloCandidato;}
	
	public void pesquisar() {
		modeloCandidato.setPesquisaCandidato(pesquisaCandidato);
		modeloCandidato.pesquisar();
	}

	public void pesquisarParaCadastro() {
		modeloCandidato.setCadastroCandidato(cadastroCandidato);
		modeloCandidato.pesquisarParaCadastro();
	}

	public void remover() {
		modeloCandidato.setPesquisaCandidato(pesquisaCandidato);
		modeloCandidato.remover();
	}

	public void gravar() {
		modeloCandidato.setCadastroCandidato(cadastroCandidato);
		
		if (cadastroCandidato.getId() == -1) {
			modeloCandidato.adicionar();
		} else {
			modeloCandidato.atualizar();
		}
	}
	
	public void verificaCPF(){
		modeloCandidato.setCadastroCandidato(cadastroCandidato);
		modeloCandidato.verificaCPF();
	}

}
