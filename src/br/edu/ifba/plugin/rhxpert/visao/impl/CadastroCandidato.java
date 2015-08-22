package br.edu.ifba.plugin.rhxpert.visao.impl;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.edu.ifba.plugin.rhxpert.controle.ControleCandidato;
import br.edu.ifba.plugin.rhxpert.modelo.ModeloCandidato;
import br.edu.ifba.plugin.rhxpert.modelo.bd.estatico.Candidato;
import br.edu.ifba.plugin.rhxpert.visao.ICadastroCandidato;
import br.edu.ifba.plugin.rhxpert.visao.ValidaCampos;

@ManagedBean(name = "cad_candidato")
@ViewScoped
public class CadastroCandidato implements ICadastroCandidato {
	// mensagenss
	private static final String ERRO_CPF_NAO_INFORMADO = "O CPF deve ser informado.";
	private static final String ERRO_CPF_INVALIDO = "CPF inválido.";
	private static final String ERRO_CPF_REPETIDO = "Já existe um candidato cadastrado com este CPF.";
	private static final String ERRO_EMAIL_INVALIDO = "E-mail inválido.";
	private static final String ERRO_EMAIL_NAO_INFORMADO = "O e-mail deve ser informado.";
	private static final String ERRO_NOME_NAO_INFORMADO = "O nome deve ser informado.";
	private static final String ERRO_TELEFONE_NAO_INFORMADO = "O telefone deve ser informado.";
	private static final String ERRO_ENDERECO_NAO_INFORMADO = "O endereço deve ser informado.";
	private static final String ERRO_NUMERO_NAO_INFORMADO = "O número deve ser informado.";
	private static final String ERRO_CEP_NAO_INFORMADO = "O CEP deve ser informado.";
	private static final String ERRO_BAIRRO_NAO_INFORMADO = "O bairro deve ser informado.";
	private static final String ERRO_CIDADE_NAO_INFORMADA = "A cidade deve ser informada.";
	private static final String ERRO_UF_NAO_INFORMADA = "A UF deve ser informada.";
	private static final String ERRO_ESCOLARIDADE_NAO_INFORMADA = "A escolaridade deve ser informada.";
	
	public boolean gravado = false;
	public boolean errogravacao = false;
	public boolean naoencontrado = false;
	
	private boolean valido = true;

	private String id = "";
	private Candidato candidato = new Candidato();
	
	public CadastroCandidato() {
		ExternalContext contexto = FacesContext.getCurrentInstance()
				.getExternalContext();
		id = (String) contexto.getSessionMap().get("idCandidato");

		if ((id != null) && (!id.isEmpty())) {
			recuperarCandidato();
		}
	}
	
	private void recuperarCandidato() {
		naoencontrado = false;
		ModeloCandidato modelo = new ModeloCandidato();
		ControleCandidato controle = new ControleCandidato();

		controle.setCadastroCandidato(this);
		controle.setModeloCandidato(modelo);

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
	public Candidato getCandidato() {
		return candidato;
	}

	@Override
	public void atualizarCandidatoEncontrado(Candidato candidato) {
		this.candidato = candidato;
	}
	
	public void gravar() {
		gravado = false;
		errogravacao = false;
		
		validaCampos(candidato);
		if(valido){
			gravarCandidato();
		}
	}

	private void gravarCandidato() {
		ModeloCandidato modelo = new ModeloCandidato();
		ControleCandidato controle = new ControleCandidato();

		controle.setCadastroCandidato(this);
		controle.setModeloCandidato(modelo);

		controle.gravar();
	}

	@Override
	public void notificarCandidatoNaoEncontrado() {
		naoencontrado = true;

	}

	@Override
	public void notificarCandidatoGravado(Candidato candidato) {
		gravado = true;
		valido = true;
	}
	
	public boolean getGravado() {
		return gravado;
	}
	public boolean getErrogravacao() {
		return errogravacao;
	}
	public boolean getNaoencontrado() {
		return naoencontrado;
	}


	@Override
	public void notificarErroGravacao() {
		errogravacao = true;
	}
	
	private void verificaCPF(){
		ModeloCandidato modelo = new ModeloCandidato();
		ControleCandidato controle = new ControleCandidato();

		controle.setCadastroCandidato(this);
		controle.setModeloCandidato(modelo);

		controle.verificaCPF();
	}
	
	public void notificarCPFRepetido(){
		FacesContext.getCurrentInstance().addMessage("form:cpf",new FacesMessage(ERRO_CPF_REPETIDO,ERRO_CPF_REPETIDO));
		valido = false;
	}
	
	private void validaCampos(Candidato candidato){
		valido = true;
		
		//validações
		//nome
		if(candidato.getNome()==null || candidato.getNome().isEmpty()){
			FacesContext.getCurrentInstance().addMessage("form:nome",new FacesMessage(ERRO_NOME_NAO_INFORMADO,ERRO_NOME_NAO_INFORMADO));
			valido = false;
		}
		//cpf
		if ((candidato.getCpf() == null) || (candidato.getCpf().isEmpty())) {
			FacesContext.getCurrentInstance().addMessage("form:cpf",new FacesMessage(ERRO_CPF_NAO_INFORMADO,ERRO_CPF_NAO_INFORMADO));
			valido = false;
		}if(!ValidaCampos.isCPF(candidato.getCpf())){
			FacesContext.getCurrentInstance().addMessage("form:cpf",new FacesMessage(ERRO_CPF_INVALIDO,ERRO_CPF_INVALIDO));
			valido = false;
		}else{
			verificaCPF();
		}
		//email
		if((candidato.getEmail() == null) || (candidato.getEmail().isEmpty())) {
			FacesContext.getCurrentInstance().addMessage("form:email",new FacesMessage(ERRO_EMAIL_NAO_INFORMADO,ERRO_EMAIL_NAO_INFORMADO));
			valido = false;
		}else if(!ValidaCampos.isEmail(candidato.getEmail())){
			FacesContext.getCurrentInstance().addMessage("form:email",new FacesMessage(ERRO_EMAIL_INVALIDO,ERRO_EMAIL_INVALIDO));
			valido = false;
		}
		//telefone
		if(candidato.getTelefone()==null || candidato.getTelefone().isEmpty()){
			FacesContext.getCurrentInstance().addMessage("form:telefone",new FacesMessage(ERRO_TELEFONE_NAO_INFORMADO,ERRO_TELEFONE_NAO_INFORMADO));
			valido = false;
		}
		//endereco
		if(candidato.getEndereco().getEndereco()==null || candidato.getEndereco().getEndereco().isEmpty()){
			FacesContext.getCurrentInstance().addMessage("form:endereco",new FacesMessage(ERRO_ENDERECO_NAO_INFORMADO,ERRO_ENDERECO_NAO_INFORMADO));
			valido = false;
		}
		if(candidato.getEndereco().getNumero()==null || candidato.getEndereco().getNumero().isEmpty()){
			FacesContext.getCurrentInstance().addMessage("form:numero",new FacesMessage(ERRO_NUMERO_NAO_INFORMADO,ERRO_NUMERO_NAO_INFORMADO));
			valido = false;
		}
		if(candidato.getEndereco().getCep()==null || candidato.getEndereco().getCep().isEmpty()){
			FacesContext.getCurrentInstance().addMessage("form:cep",new FacesMessage(ERRO_CEP_NAO_INFORMADO,ERRO_CEP_NAO_INFORMADO));
			valido = false;
		}
		if(candidato.getEndereco().getBairro()==null || candidato.getEndereco().getBairro().isEmpty()){
			FacesContext.getCurrentInstance().addMessage("form:bairro",new FacesMessage(ERRO_BAIRRO_NAO_INFORMADO,ERRO_BAIRRO_NAO_INFORMADO));
			valido = false;
		}
		if(candidato.getEndereco().getCidade()==null || candidato.getEndereco().getCidade().isEmpty()){
			FacesContext.getCurrentInstance().addMessage("form:cidade",new FacesMessage(ERRO_CIDADE_NAO_INFORMADA,ERRO_CIDADE_NAO_INFORMADA));
			valido = false;
		}
		if(candidato.getEndereco().getUf()==null || candidato.getEndereco().getUf().isEmpty()){
			FacesContext.getCurrentInstance().addMessage("form:uf",new FacesMessage(ERRO_UF_NAO_INFORMADA,ERRO_UF_NAO_INFORMADA));
			valido = false;
		}
		//escolaridade
		if(candidato.getEscolaridade()==null || candidato.getEscolaridade().isEmpty()){
			FacesContext.getCurrentInstance().addMessage("form:escolaridade",new FacesMessage(ERRO_ESCOLARIDADE_NAO_INFORMADA,ERRO_ESCOLARIDADE_NAO_INFORMADA));
			valido = false;
		}
	}

}
