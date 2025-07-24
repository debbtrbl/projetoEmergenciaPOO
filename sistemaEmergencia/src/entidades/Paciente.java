package entidades;

import java.util.ArrayList;
import java.util.List;

public class Paciente extends Pessoa {
    private List<Atendimento> historicoMedico = new ArrayList<>();
    private List<Exame> exames = new ArrayList<>();
    private List<Medicamento> medicamentosRecebidos = new ArrayList<>();
    private List<String> observacoes = new ArrayList<>();

    public Paciente(String nome, String dataNascimento, String cpf) {
        super(nome, dataNascimento, cpf);
    }

    public void dadosPaciente() {
        System.out.println("Nome: " + getNome());
        System.out.println("Data de Nascimento: " + getDataNascimento());
        System.out.println("CPF: " + getCpf());
        System.out.println("Classificações em atendimentos:");
        for (Atendimento a : historicoMedico) {
            System.out.println("- " + a.getAvaliacao().getClassificacao());
        }
    }

    public void listarAtendimentos() {
        System.out.println("📋 Histórico de Atendimentos:");
        if (historicoMedico.isEmpty()) {
            System.out.println("Nenhum atendimento registrado.");
        } else {
            for (Atendimento a : historicoMedico) {
                System.out.println("Atendimento em " + a.getData() + " | Status: " + a.getStatus());
                System.out.println("Diagnóstico: " + (a.getAvaliacao().getDiagnostico() != null 
                    ? a.getAvaliacao().getDiagnostico() 
                    : "Não informado"));
                System.out.println("Receituário: " + a.getReceituario());
                System.out.println("Classificação: " + a.getAvaliacao().getClassificacao());
                System.out.println("-----------------------------------");
            }
        }
    }

    public void adicionarAtendimento(Atendimento atendimento) {
        historicoMedico.add(atendimento);
        System.out.println("Atendimento adicionado ao histórico.");
    }

    public void adicionarExame(Exame exame) {
        exames.add(exame);
        System.out.println("Exame '" + exame.getNome() + "' registrado.");
    }

    public void administrarMedicamento(Medicamento medicamento) {
        medicamentosRecebidos.add(medicamento);
        System.out.println("Medicamento '" + medicamento.getNome() + "' administrado.");
    }

    public void listarExames() {
        System.out.println("Exames realizados:");
        for (Exame e : exames) {
            System.out.println("- " + e.getNome() + " | Realizado: " + (e.isEstaPronto() ? "Sim" : "Não"));
        }
    }
    
    public List<Exame> getExames() {
        return exames;
    }

    public void listarMedicamentos() {
        System.out.println("Medicamentos recebidos:");
        for (Medicamento m : medicamentosRecebidos) {
            System.out.println("- " + m.getNome() + " | Dosagem: " + m.getDosagem());
        }
    }

    public void adicionarObservacao(String obs) {
        this.observacoes.add(obs);
    }

    public void listarObservacoes() {
        System.out.println("\nObservações do paciente:");
        if (observacoes.isEmpty()) {
            System.out.println("Nenhuma observação registrada.");
        } else {
            for (String obs : observacoes) {
                System.out.println("- " + obs);
            }
        }
    }
}
