package util;

public class DadosInvalidosException extends SistemaHospitalarException {
    public DadosInvalidosException(String campo) {
        super("Dados inválidos para o campo: " + campo);
    }
}
