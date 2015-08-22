package br.edu.ifba.plugin.rhxpert.visao;

import br.edu.ifba.plugin.rhxpert.modelo.bd.estatico.Candidato;

public interface ICadastroCandidato {
	
	public int getId();
	public Candidato getCandidato();
	
	
	public void atualizarCandidatoEncontrado(Candidato candidato);
	public void notificarCandidatoNaoEncontrado();
	public void notificarCandidatoGravado(Candidato candidato);
	public void notificarErroGravacao();
	public void notificarCPFRepetido();
	
	

}
