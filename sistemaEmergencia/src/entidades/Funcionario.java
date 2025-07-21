package entidades;

public abstract class Funcionario extends Pessoa {
	private int matricula;
	private double salario;
	private int cargaHorariaSemanal;
	
	public Funcionario(String nome, String dataNascimento, String cpf,
			int matricula, double salario, int ch) {
		super(nome, dataNascimento, cpf);
		this.matricula = matricula;
		this.salario = salario;
		this.cargaHorariaSemanal = ch;
	}

	public abstract void dadosFuncionario();

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getCargaHorariaSemanal() {
		return cargaHorariaSemanal;
	}

	public void setCargaHorariaSemanal(int cargaHorariaSemanal) {
		this.cargaHorariaSemanal = cargaHorariaSemanal;
	}
	
	
}
