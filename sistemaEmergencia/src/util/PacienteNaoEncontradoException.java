package util;

public class PacienteNaoEncontradoException extends SistemaHospitalarException {
    public PacienteNaoEncontradoException(String cpfOuNome) {
        super("Paciente não encontrado: " + cpfOuNome);
    }
}
