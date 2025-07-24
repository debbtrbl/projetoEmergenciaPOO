package funcionarios;

import java.util.ArrayList;
import java.util.List;

import entidades.Funcionario;
import entidades.Medicamento;
import entidades.Paciente;

public class Farmaceutico extends Funcionario {
    private int crf;
    private List<Medicamento> estoque = new ArrayList<>();

    public Farmaceutico(String nome, String dataNascimento, String cpf,
                        int matricula, double salario, int ch, int crf) {
        super(nome, dataNascimento, cpf, matricula, salario, ch);
        this.crf = crf;
    }

    @Override
    public void dadosFuncionario() {
        System.out.println("Farmacêutico:");
        System.out.println("Nome: " + getNome());
        System.out.println("CRF: " + crf);
        System.out.println("Medicamentos no estoque: " + estoque.size());
    }

    public void liberarMedicamento(Paciente paciente, Medicamento medicamento) {
        if (!estoque.contains(medicamento)) {
            System.out.println("Medicamento não disponível.");
            return;
        }
        estoque.remove(medicamento);
        System.out.println("Medicamento '" + medicamento.getNome() + "' liberado para " + paciente.getNome());
        paciente.administrarMedicamento(medicamento);
    }

    public void reporEstoque(Medicamento medicamento, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            estoque.add(new Medicamento(medicamento.getNome(), medicamento.getDosagem()));
        }
        System.out.println("Adicionado " + quantidade + " unidades de '" + medicamento.getNome() + "'.");
    }

    public void listarEstoque() {
        System.out.println("Estoque atual:");
        if (estoque.isEmpty()) {
            System.out.println("Estoque vazio.");
            return;
        }

        int count = 0;
        String ultimo = "";
        for (Medicamento m : estoque) {
            if (ultimo.equals(m.getNome())) {
                count++;
            } else {
                if (count > 0) System.out.println("- " + ultimo + ": " + count + " unidades");
                ultimo = m.getNome();
                count = 1;
            }
        }
        if (count > 0) System.out.println("- " + ultimo + ": " + count + " unidades");
    }

    public int getCrf() {
        return crf;
    }

    public void setCrf(int crf) {
        this.crf = crf;
    }
}
