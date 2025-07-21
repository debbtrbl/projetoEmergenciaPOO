package funcionarios;

import entidades.Funcionario;
import util.Departamentos;

public class AuxiliarLimpeza extends Funcionario{
	private String turno;
    private Departamentos areaResponsavel;
    
    public AuxiliarLimpeza(String nome, String dataNascimento, String cpf, int matricula, double salario, int ch,
    		String turno, Departamentos area) {
		super(nome, dataNascimento, cpf, matricula, salario, ch);
		this.turno = turno;
		this.areaResponsavel = area;
	}

    @Override
    public void dadosFuncionario() {
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Departamentos getAreaResponsavel() {
        return areaResponsavel;
    }

    public void setAreaResponsavel(Departamentos areaResponsavel) {
        this.areaResponsavel = areaResponsavel;
    }

    public void realizarLimpeza() {
        System.out.println(getNome() + " está realizando limpeza.");
    }
}
