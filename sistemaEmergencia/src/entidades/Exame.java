package entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import funcionarios.Enfermeiro;

public class Exame {
    private String nome;
    private Enfermeiro responsavel;
    private Paciente paciente;
    private LocalDate dataRealizacao;
    private LocalDate dataEntrega;
    private String resultado;
    private boolean estaPronto;

    private static final DateTimeFormatter FORMATO_DATA = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Exame(String nome, Enfermeiro responsavel, Paciente paciente) {
        this.nome = nome;
        this.responsavel = responsavel;
        this.paciente = paciente;
        this.estaPronto = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Enfermeiro getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Enfermeiro responsavel) {
        this.responsavel = responsavel;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public String getResultado() {
        return resultado;
    }

    public boolean isEstaPronto() {
        return estaPronto;
    }

    public void realizarExame() {
        this.dataRealizacao = LocalDate.now();
        System.out.println("Exame '" + nome + "' realizado em " 
            + dataRealizacao.format(FORMATO_DATA) 
            + " pelo enfermeiro " + responsavel.getNome());
    }

    public void gerarResultado(String resultado) {
        if (dataRealizacao == null) {
            System.out.println("Não é possível gerar resultado antes de realizar o exame.");
            return;
        }
        this.resultado = resultado;
        this.dataEntrega = LocalDate.now();
        this.estaPronto = true;
        System.out.println("Resultado do exame '" + nome + "' pronto em " 
            + dataEntrega.format(FORMATO_DATA));
    }

    public boolean verificarPronto() {
        return estaPronto;
    }

    @Override
    public String toString() {
        return "Exame{" +
               "nome='" + nome + '\'' +
               ", paciente=" + paciente.getNome() +
               ", realizado=" + (dataRealizacao != null 
                   ? dataRealizacao.format(FORMATO_DATA) 
                   : "não realizado") +
               ", pronto=" + estaPronto +
               (estaPronto 
                   ? ", entregue em " + dataEntrega.format(FORMATO_DATA) 
                   : "") +
               '}';
    }

}
