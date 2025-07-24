package entidades;

import funcionarios.Medico;

public class Atendimento {
    private String data;
    private Medico medico;
    private Paciente paciente;
    private Triagem avaliacao;
    private String status;
    private String receituario;

    public Atendimento(String data, Medico medico, Paciente paciente, Triagem avaliacao) {
        this.data = data;
        this.medico = medico;
        this.paciente = paciente;
        this.avaliacao = avaliacao;
        this.status = "Em andamento";
        this.receituario = "";
    }

    public void adicionarDiagnostico(String diagnostico) {
        if (avaliacao != null) {
            avaliacao.setDiagnostico(diagnostico);
        } else {
            System.out.println("Avaliação não disponível.");
        }
    }

    public void darAlta() {
        this.status = "Alta";
        System.out.println("Paciente recebeu alta.");
    }

    public void gerarReceituario() {
        if (avaliacao != null && avaliacao.getDiagnostico() != null) {
            this.receituario = "Receituário baseado no diagnóstico: " + avaliacao.getDiagnostico();
        } else {
            this.receituario = "Sem diagnóstico disponível.";
        }
    }

    public void encaminharExame(Exame exame) {
        if (paciente != null) {
            paciente.adicionarExame(exame);
            System.out.println("Exame encaminhado para paciente.");
        }
    }

    public void encaminharEnfermaria(Medicamento medicamento) {
        if (paciente != null) {
            paciente.administrarMedicamento(medicamento);
            System.out.println("Paciente encaminhado para enfermaria com medicamento.");
        }
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Triagem getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Triagem avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getStatus() {
        return status;
    }

    public String getReceituario() {
        return receituario;
    }
}
