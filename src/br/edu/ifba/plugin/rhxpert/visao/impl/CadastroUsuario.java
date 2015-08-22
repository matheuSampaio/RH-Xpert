package br.edu.ifba.plugin.rhxpert.visao.impl;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.edu.ifba.plugin.rhxpert.controle.ControleUsuario;
import br.edu.ifba.plugin.rhxpert.modelo.ModeloUsuario;
import br.edu.ifba.plugin.rhxpert.modelo.bd.estatico.Usuario;
import br.edu.ifba.plugin.rhxpert.visao.ICadastroUsuario;

@ManagedBean(name = "cadusuario")
@ViewScoped
public class CadastroUsuario implements ICadastroUsuario {

	private static final String ERRO_CPF_NAO_INFORMADO = "Cpf deve ser informado!";

	public boolean gravado = false;

	private String id = "";
	private Usuario usuario = new Usuario();

	public CadastroUsuario() {
		ExternalContext contexto = FacesContext.getCurrentInstance()
				.getExternalContext();
		id = (String) contexto.getSessionMap().get("idUsuario");

		if ((id != null) && (!id.isEmpty())) {
			recuperarUsuario();
		}
	}

	private void recuperarUsuario() {
		ModeloUsuario modelo = new ModeloUsuario();
		ControleUsuario controle = new ControleUsuario();

		controle.setCadastroUsuario(this);
		controle.setModeloUsuario(modelo);

		controle.pesquisarParaCadastro();
	}

	@Override
	public int getId() {
		int iid = -1;

		if ((id != null) && (!id.isEmpty())) {
			iid = Integer.parseInt(id);
		}

		return iid;
	}

	@Override
	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public void atualizarUsuarioEncontrado(Usuario usuario) {
		this.usuario = usuario;
	}

	public void gravar() {
		gravado = false;

		if ((usuario.getCpf() == null) || (usuario.getCpf().isEmpty())) {
			FacesContext.getCurrentInstance().addMessage(
					"form:cpf",
					new FacesMessage(ERRO_CPF_NAO_INFORMADO,
							ERRO_CPF_NAO_INFORMADO));
		} else {
			gravarUsuario();
		}
	}

	private void gravarUsuario() {
		ModeloUsuario modelo = new ModeloUsuario();
		ControleUsuario controle = new ControleUsuario();

		controle.setCadastroUsuario(this);
		controle.setModeloUsuario(modelo);

		controle.gravar();
	}

	@Override
	public void notificarUsuarioNaoEncontrado() {
		// TODO Auto-generated method stub
	}

	@Override
	public void notificarUsuarioGravado(Usuario usuario) {
		gravado = true;
	}

	public boolean getGravado() {
		return gravado;
	}

	@Override
	public void notificarErroGravacao() {
		// TODO Auto-generated method stub

	}

}
