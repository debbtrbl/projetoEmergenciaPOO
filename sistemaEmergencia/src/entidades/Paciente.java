package entidades;

import java.util.ArrayList;
import java.util.List;

public class Paciente extends Pessoa {
	private List<Atendimento> historicoMedico = new ArrayList<>();
	
	public Paciente(String nome, String dataNascimento, String cpf) {
		super(nome, dataNascimento, cpf);
	}
	
	public void dadosPaciente() {
	}

	
	public void listarAtendimentos() {
	    for (Atendimento a : historicoMedico) {
	        System.out.println(a);
	    }
	}

	
	public void adicionarAtendimento(Atendimento atendimento) {
	    historicoMedico.add(atendimento);
	}

	
}
