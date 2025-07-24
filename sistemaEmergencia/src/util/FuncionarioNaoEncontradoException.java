package util;

public class FuncionarioNaoEncontradoException extends SistemaHospitalarException {
    public FuncionarioNaoEncontradoException(String matriculaOuNome) {
        super("Funcionário não encontrado: " + matriculaOuNome);
    }
}

