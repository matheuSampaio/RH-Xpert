package br.edu.ifba.plugin.rhxpert.visao;

import java.util.List;

import br.edu.ifba.plugin.rhxpert.modelo.bd.estatico.Candidato;

public interface IPesquisaCandidato {
	
	public String getId();
	public String getCpf();
	public String getEmail();
	public String getNome();
	
	public void atualizarCandidatosEncontrados(List<Candidato> candidatos);

	public void notificarCandidatosNaoEncontrados();
	
	public void notificarCandidatoRemovido();
	
	public void notificarErroRemocao();
	
	

}
