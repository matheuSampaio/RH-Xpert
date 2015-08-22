package br.edu.ifba.plugin.rhxpert.modelo.bd.estatico;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CandidatoDAO {

	private static Map<Integer, Candidato> candidatos = 
			new TreeMap<Integer, Candidato>();
	
	static {
		
		Candidato c1 =  new Candidato();
		c1.setId(1);
		c1.setCpf("7777777777");
		c1.setEmail("candidato@gmail.com");
		c1.setEndereco(new Endereco("Vitória da Conquista", "BA", "45250-000", "Avenida São João", "14", "Centro"));
		c1.setTelefone("(77) 8688-1388");
		c1.setEscolaridade("Ensino fundamental");
		c1.setNome("José Alves Nascimento");
		c1.setFormacao("Direito");
		c1.setAtivo(true);
		
		candidatos.put(c1.getId(), c1);
		
	}
	
	public static List<Candidato> getCandidatosPorCPF(String cpf){
		List<Candidato> encontrados = new ArrayList<Candidato>();
		
		for (Candidato c : candidatos.values()) {
			if (c.getCpf().equals(cpf)) {
				encontrados.add(c);
			}
		}
		
		return encontrados;
	}
	
	public static List<Candidato> getCandidatosPorCPFRepetido(String cpf, int id){
		List<Candidato> encontrados = new ArrayList<Candidato>();
		
		for (Candidato c : candidatos.values()) {
			if (c.getCpf().equals(cpf) && c.getId()!=id) {//se for outro candidato com o mesmo cpf
				encontrados.add(c);
			}
		}
		
		return encontrados;
	}
	
	public static List<Candidato> getCandidatosPorEmail(String email){
		List<Candidato> encontrados = new ArrayList<Candidato>();
		
		for (Candidato c : candidatos.values()) {
			if (c.getEmail().equals(email)) {
				encontrados.add(c);
			}
		}
		
		return encontrados;
	}
	
	public static List<Candidato> getCandidatosPorNome(String nome){
		List<Candidato> encontrados = new ArrayList<Candidato>();
		
		for (Candidato c : candidatos.values()) {
			if (c.getNome().toLowerCase().contains(nome.toLowerCase())) {
				encontrados.add(c);
			}
		}
		
		return encontrados;
	}
	
	public static Candidato getCandidato(int id){
		return candidatos.get(id);
	}
	
	public static void remover(int id) {
		candidatos.remove(id);
	}
	
	public static int gravar(Candidato candidato) {
		if (candidato.getId() != -1) { //edição
			remover(candidato.getId());
			candidatos.put(candidato.getId(), candidato);
		} else {
			int ultimoId = 0;
			for (int id : candidatos.keySet()) {
				ultimoId = id;
			}
			candidato.setAtivo(true);
			candidato.setId(ultimoId + 1);
			candidatos.put(ultimoId + 1, candidato);
		}
		
		return 0;
	}
}

