package br.edu.ifba.plugin.rhxpert.modelo;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.plugin.rhxpert.modelo.bd.estatico.Usuario;
import br.edu.ifba.plugin.rhxpert.modelo.bd.estatico.UsuarioDAO;
import br.edu.ifba.plugin.rhxpert.modelo.bd.jpa.UsuarioSagu;
import br.edu.ifba.plugin.rhxpert.modelo.bd.jpa.UsuarioSaguDAO;
import br.edu.ifba.plugin.rhxpert.visao.IAcessoUsuario;
import br.edu.ifba.plugin.rhxpert.visao.ICadastroUsuario;
import br.edu.ifba.plugin.rhxpert.visao.IPesquisaUsuario;

/**
 * Classe de Modelo especializada em manipular informacoes relacionadas aa
 * validacao de acesso do usuario.
 * 
 * Os modelos sao ativos, i.e. acionam automaticamente acoes na interface.
 * 
 * @author PLUGIN
 */
public class ModeloUsuario {

	private static final boolean UTILIZAR_SAGU = false;

	private IAcessoUsuario acessoUsuario = null;
	private IPesquisaUsuario pesquisaUsuario = null;
	private ICadastroUsuario cadastroUsuario = null;

	public void setAcessoUsuario(IAcessoUsuario acesso) {
		this.acessoUsuario = acesso;
	}

	public void setPesquisaUsuario(IPesquisaUsuario pesquisa) {
		this.pesquisaUsuario = pesquisa;
	}

	public void setCadastroUsuario(ICadastroUsuario cadastro) {
		this.cadastroUsuario = cadastro;
	}

	public void validarAcesso() {
		if (UTILIZAR_SAGU) {
			List<UsuarioSagu> usuarios = new UsuarioSaguDAO()
					.getUsuariosPorLoginSenha(acessoUsuario.getLogin(),
							acessoUsuario.getSenha());
			if (usuarios.isEmpty()) {
				acessoUsuario.notificarSemPermissao();
			} else {
				UsuarioSagu usuario = usuarios.get(0);
				acessoUsuario.atualizarUsuarioComPermissao(usuario);
			}
		} else {
			List<Usuario> usuarios = UsuarioDAO.getUsuariosPorLoginSenha(
					acessoUsuario.getLogin(), acessoUsuario.getSenha());
			if (usuarios.isEmpty()) {
				acessoUsuario.notificarSemPermissao();
			} else {
				Usuario usuario = usuarios.get(0);
				acessoUsuario.atualizarUsuarioComPermissao(usuario);
			}
		}

	}

	public void pesquisar() {
		List<Usuario> usuarios = new ArrayList<Usuario>();

		String criterio = pesquisaUsuario.getCpf();
		if (!criterio.equals("")) {
			usuarios = UsuarioDAO.getUsuariosPorCPF(criterio);
		} else {
			criterio = pesquisaUsuario.getRg();
			if (!criterio.equals("")) {
				usuarios = UsuarioDAO.getUsuariosPorRG(criterio);
			} else {
				criterio = pesquisaUsuario.getNome();
				if (!criterio.equals("")) {
					usuarios = UsuarioDAO.getUsuariosPorNome(criterio);
				}
			}
		}

		pesquisaUsuario.atualizarUsuariosEncontrados(usuarios);

		if (usuarios.isEmpty()) {
			pesquisaUsuario.notificarUsuariosNaoEncontrados();
		}
	}

	public void pesquisarParaCadastro() {
		Usuario usuario = UsuarioDAO.getUsuario(cadastroUsuario.getId());

		if (usuario == null) {
			cadastroUsuario.notificarUsuarioNaoEncontrado();
		} else {
			cadastroUsuario.atualizarUsuarioEncontrado(usuario);
		}
	}

	public void remover() {
		UsuarioDAO.remover(Integer.parseInt(pesquisaUsuario.getId()));

		pesquisar();
		
		pesquisaUsuario.notificarUsuarioRemovido();
	}

	public void adicionar() {
		Usuario usuario = cadastroUsuario.getUsuario();

		usuario.setId(-1);
		if (UsuarioDAO.gravar(usuario) > 0) {
			cadastroUsuario.notificarErroGravacao();
		} else {
			cadastroUsuario.notificarUsuarioGravado(usuario);
		}
	}

	public void atualizar() {
		Usuario usuario = cadastroUsuario.getUsuario();

		if (UsuarioDAO.gravar(usuario) > 0) {
			cadastroUsuario.notificarErroGravacao();
		} else {
			cadastroUsuario.notificarUsuarioGravado(usuario);
		}
	}

}
