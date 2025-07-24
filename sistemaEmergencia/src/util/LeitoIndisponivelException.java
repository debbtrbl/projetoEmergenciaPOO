package util;

public class LeitoIndisponivelException extends SistemaHospitalarException {
    public LeitoIndisponivelException(int numeroLeito) {
        super("Leito " + numeroLeito + " indisponível");
    }
}
