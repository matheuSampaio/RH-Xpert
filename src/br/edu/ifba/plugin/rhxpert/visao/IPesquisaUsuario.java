package br.edu.ifba.plugin.rhxpert.visao;

import java.util.List;

import br.edu.ifba.plugin.rhxpert.modelo.bd.estatico.Usuario;

public interface IPesquisaUsuario {

	public String getId();
	
	public String getRg();

	public String getCpf();

	public String getNome();

	///////////////////////

	public void atualizarUsuariosEncontrados(List<Usuario> usuarios);

	public void notificarUsuariosNaoEncontrados();
	
	public void notificarUsuarioRemovido();
	
	public void notificarErroRemocao();

}
