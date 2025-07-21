package funcionarios;

import entidades.Funcionario;
import entidades.Paciente;

public class Medico extends Funcionario {
    private String crm;
    private String especialidade;

    public Medico(String nome, String dataNascimento, String cpf, int matricula,
                  double salario, int ch, String crm, String especialidade) {
        super(nome, dataNascimento, cpf, matricula, salario, ch);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    @Override
    public void dadosFuncionario() {
 
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void atenderPaciente(Paciente paciente) {
        // criar objeto do tipo Atendimento
        System.out.println("Médico " + getNome() + " atendendo paciente " + paciente.getNome());
    }
}
