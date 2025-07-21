package funcionarios;

import entidades.Funcionario;
import entidades.Medicamento;
import entidades.Paciente;

public class Farmaceutico extends Funcionario {
	private int crf;
	
	public Farmaceutico(String nome, String dataNascimento, String cpf, int matricula, double salario, int ch, int crf) {
		super(nome, dataNascimento, cpf, matricula, salario, ch);
		this.crf = crf;
	}

	@Override
	public void dadosFuncionario() {
		// TODO Auto-generated method stub
		
	};
	
	public int getCrf() {
		return crf;
	}

	public void setCrf(int crf) {
		this.crf = crf;
	}

	public void liberarMedicamento(Paciente paciente, Medicamento medicamento) {
		// logica
	};
	
	public void reporEstoque(Medicamento medicamento, int quantidade) {
		// logica
	};
	
	
}
