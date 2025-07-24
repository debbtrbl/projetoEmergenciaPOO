package funcionarios;

import java.util.ArrayList;
import entidades.Funcionario;
import entidades.Paciente;
import entidades.Triagem;
import entidades.Medicamento;
import util.Departamentos;

public class Enfermeiro extends Funcionario {
    private int coren;
    private Departamentos departamento;

    public Enfermeiro(String nome, String dataNascimento, String cpf,
                      int matricula, double salario, int cargaHoraria,
                      int coren, Departamentos departamento) {
        super(nome, dataNascimento, cpf, matricula, salario, cargaHoraria);
        this.coren = coren;
        this.departamento = departamento;
    }

    @Override
    public void dadosFuncionario() {
        System.out.println("Dados do Enfermeiro:");
        System.out.println("Nome: " + getNome());
        System.out.println("Nascimento: " + getDataNascimento());
        System.out.println("CPF: " + getCpf());
        System.out.println("Matrícula: " + getMatricula());
        System.out.println("Salário: R$ " + getSalario());
        System.out.println("Carga Horária: " + getCargaHorariaSemanal() + "h");
        System.out.println("COREN: " + coren);
        System.out.println("Departamento: " + departamento);
    }

    public int getCoren() {
        return coren;
    }

    public void setCoren(int coren) {
        this.coren = coren;
    }

    public Departamentos getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamentos departamento) {
        this.departamento = departamento;
    }

    public Triagem acompanharPaciente(Paciente paciente) {
        System.out.println("🩺 Enfermeiro " + getNome()
                           + " realizando triagem do paciente " 
                           + paciente.getNome());

        Triagem triagem = new Triagem(
            paciente,
            this,
            "",               
            0.0f,             
            0,                
            new ArrayList<>(),
            ""                
        );

        triagem.medirPressaoArterial();
        triagem.medirTemperatura();
        triagem.medirOxigenacao();
        triagem.adicionarSintomas();
        triagem.classificar();

        System.out.println("Classificação: " + triagem.getClassificacao());
        return triagem;
    }

    public void administrarMedicamento(Paciente paciente, Medicamento medicamento) {
        System.out.println("Enfermeiro " + getNome()
                           + " administrando " + medicamento.getNome()
                           + " ao paciente " + paciente.getNome());
        medicamento.aplicar();
        paciente.administrarMedicamento(medicamento);
    }
}
