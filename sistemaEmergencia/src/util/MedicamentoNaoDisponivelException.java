package util;

public class MedicamentoNaoDisponivelException extends SistemaHospitalarException {
    public MedicamentoNaoDisponivelException(String medicamento) {
        super("Medicamento não disponível: " + medicamento);
    }
}
