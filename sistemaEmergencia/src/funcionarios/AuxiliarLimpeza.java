package funcionarios;

import entidades.Funcionario;
import entidades.Leito;
import util.Departamentos;

public class AuxiliarLimpeza extends Funcionario {
    private String turno;
    private Departamentos areaResponsavel;

    public AuxiliarLimpeza(String nome,
                           String dataNascimento,
                           String cpf,
                           int matricula,
                           double salario,
                           int ch,
                           String turno,
                           Departamentos area) {
        super(nome, dataNascimento, cpf, matricula, salario, ch);
        this.turno = turno;
        this.areaResponsavel = area;
    }

    @Override
    public void dadosFuncionario() {
        System.out.println("🧹 Auxiliar de Limpeza:");
        System.out.println("Nome: " + getNome());
        System.out.println("Nascimento: " + getDataNascimento());
        System.out.println("CPF: " + getCpf());
        System.out.println("Matrícula: " + getMatricula());
        System.out.println("Salário: R$ " + getSalario());
        System.out.println("Carga Horária: " + getCargaHorariaSemanal() + "h");
        System.out.println("Turno: " + turno);
        System.out.println("Área Responsável: " + areaResponsavel);
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
        System.out.println(getNome() + " está realizando limpeza geral.");
    }
    public void limpar(Leito leito) {
        if (leito.isOcupado()) {
            System.out.println("Leito " + leito.getNumero() + " está ocupado, aguardando liberação.");
        } else {
            System.out.println("Limpando leito " + leito.getNumero() + "...");
            realizarLimpeza();
            System.out.println("Leito " + leito.getNumero() + " limpo!");
        }
    }
}
