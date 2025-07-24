package entidades;

public class Leito {
    private int numero;
    private boolean ocupado;
    private Paciente paciente; 

    public Leito(int numero) {
        this.numero = numero;
        this.ocupado = false;
        this.paciente = null;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void ocupar(Paciente paciente) {
        this.paciente = paciente;
        this.ocupado = true;
    }

    public void desocupar() {
        this.paciente = null;
        this.ocupado = false;
    }

    @Override
    public String toString() {
        return "Leito " + numero +
               (ocupado
                   ? " (ocupado por " + paciente.getNome() + ")"
                   : " (disponível)");
    }
}