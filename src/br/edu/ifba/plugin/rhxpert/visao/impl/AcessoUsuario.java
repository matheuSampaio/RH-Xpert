package br.edu.ifba.plugin.rhxpert.visao.impl;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.edu.ifba.plugin.rhxpert.controle.ControleAcesso;
import br.edu.ifba.plugin.rhxpert.modelo.ModeloUsuario;
import br.edu.ifba.plugin.rhxpert.modelo.bd.estatico.Usuario;
import br.edu.ifba.plugin.rhxpert.modelo.bd.jpa.UsuarioSagu;
import br.edu.ifba.plugin.rhxpert.visao.IAcessoUsuario;

/**
 * Concretizacao de interface para validacao de credenciais (login + senha) de
 * acesso do usuario.
 * 
 * Esta concretizacao pode ser utilizada para validar requisitos relacionados ao
 * controle de acesso de usuarios aas funcionalidades do sistema. Nao sao
 * tratados recursos graficos/visuais de interacao do usuario (e.g. tela de
 * login). Toda a interacao eh realizada atraves do console da aplicacao.
 * 
 * @author PLUGIN
 */
@ManagedBean(name = "acesso")
public class AcessoUsuario implements IAcessoUsuario {

	private String login, senha;
	private boolean semPermissao = false;

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String getLogin() {
		return login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String getSenha() {
		return senha;
	}

	public void acessar() {
		ModeloUsuario modelo = new ModeloUsuario();
		ControleAcesso controle = new ControleAcesso();

		controle.setModeloUsuario(modelo);
		controle.setAcessoUsuario(this);

		controle.validarAcesso();
	}

	@Override
	public void atualizarUsuarioComPermissao(Usuario usuario) {
		System.out.println("Usuario com permissao de acesso = "
				+ usuario.getNome());
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect("pesquisa_candidato.ifba");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void atualizarUsuarioComPermissao(UsuarioSagu usuario) {
		System.out.println("Usuario do sagu com permissao de acesso = "
				+ usuario.getNome());
	}

	@Override
	public void notificarSemPermissao() {
		semPermissao = true;
	}

	public boolean getSemPermissao() {
		return semPermissao;
	}

}

