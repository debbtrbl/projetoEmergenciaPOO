package funcionarios;

import entidades.Funcionario;
import entidades.Medicamento;
import entidades.Paciente;
import util.Departamentos;

public class Enfermeiro extends Funcionario {
	private int coren;
	private Departamentos departamento; 

	@Override
	public void dadosFuncionario() {
		// TODO Auto-generated method stub
		
	}

	public Enfermeiro(String nome, String dataNascimento, String cpf,
						int matricula, double salario, int ch, int coren, Departamentos dep) {
		super(nome, dataNascimento, cpf, matricula, salario, ch);
		this.coren = coren;
		this.departamento = dep;
	}

	public Departamentos getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamentos departamento) {
		this.departamento = departamento;
	}

	public int getCoren() {
		return coren;
	}

	public void setCoren(int coren) {
		this.coren = coren;
	}
	
	public void acompanharPaciente(Paciente paciente) {
		// logica
	};

	public void administrarMedicamento(Paciente paciente, Medicamento medicamento) {
		// logica
	};
}
