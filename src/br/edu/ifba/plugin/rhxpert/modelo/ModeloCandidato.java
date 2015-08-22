package br.edu.ifba.plugin.rhxpert.modelo;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.plugin.rhxpert.modelo.bd.estatico.Candidato;
import br.edu.ifba.plugin.rhxpert.modelo.bd.estatico.CandidatoDAO;
import br.edu.ifba.plugin.rhxpert.visao.ICadastroCandidato;
import br.edu.ifba.plugin.rhxpert.visao.IPesquisaCandidato;

public class ModeloCandidato {
	
	private IPesquisaCandidato pesquisaCandidato = null;
	private ICadastroCandidato cadastroCandidato = null;
	
	public void setPesquisaCandidato(IPesquisaCandidato pesquisaCandidato) {this.pesquisaCandidato = pesquisaCandidato;}
	public void setCadastroCandidato(ICadastroCandidato cadastroCandidato) {this.cadastroCandidato = cadastroCandidato;}
	
	public void pesquisar() {
		List<Candidato> candidatos = new ArrayList<Candidato>();

		String criterio = pesquisaCandidato.getCpf();
		if (!"".equals(criterio)) {
			candidatos = CandidatoDAO.getCandidatosPorCPF(criterio);
		} else {
			criterio = pesquisaCandidato.getEmail();
			if (!"".equals(criterio)) {
				candidatos = CandidatoDAO.getCandidatosPorEmail(criterio);
			} else {
				criterio = pesquisaCandidato.getNome();
				if (!"".equals(criterio)) {
					candidatos = CandidatoDAO.getCandidatosPorNome(criterio);
				}
			}
		}

		pesquisaCandidato.atualizarCandidatosEncontrados(candidatos);

		if (candidatos.isEmpty()) {
			pesquisaCandidato.notificarCandidatosNaoEncontrados();
		}
	}
	
	public void pesquisarParaCadastro() {
		Candidato candidato = CandidatoDAO.getCandidato(cadastroCandidato.getId());

		if (candidato == null) {
			cadastroCandidato.notificarCandidatoNaoEncontrado();
		} else {
			cadastroCandidato.atualizarCandidatoEncontrado(candidato);
		}
	}
	
	public void remover() {
		CandidatoDAO.remover(Integer.parseInt(pesquisaCandidato.getId()));

		pesquisar();
		
		pesquisaCandidato.notificarCandidatoRemovido();
	}
	
	public void adicionar() {
		Candidato candidato = cadastroCandidato.getCandidato();

		candidato.setId(-1);
		if (CandidatoDAO.gravar(candidato) > 0) {
			cadastroCandidato.notificarErroGravacao();
		} else {
			cadastroCandidato.notificarCandidatoGravado(candidato);
		}
	}
	
	public void atualizar() {
		Candidato candidato = cadastroCandidato.getCandidato();

		if (CandidatoDAO.gravar(candidato) > 0) {
			cadastroCandidato.notificarErroGravacao();
		} else {
			cadastroCandidato.notificarCandidatoGravado(candidato);
		}
	}
	
	public void verificaCPF(){
		List<Candidato> candidatos = new ArrayList<Candidato>();
		String cpf = cadastroCandidato.getCandidato().getCpf();
		int id = cadastroCandidato.getCandidato().getId();
		candidatos = CandidatoDAO.getCandidatosPorCPFRepetido(cpf, id);
		if(!candidatos.isEmpty()){
			cadastroCandidato.notificarCPFRepetido();
		}
		
	}

}
