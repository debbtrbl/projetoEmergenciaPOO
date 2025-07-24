package departamentos;

import java.util.ArrayList;
import java.util.List;

import entidades.Paciente;
import entidades.Medicamento;
import funcionarios.Enfermeiro;
import funcionarios.Farmaceutico;

public class Enfermaria {
    private Farmaceutico farmaceutico;
    private List<Enfermeiro> enfermeiros = new ArrayList<>();
    private List<Medicamento> medicamentos = new ArrayList<>();

    public Enfermaria(Farmaceutico farmaceutico) {
        this.farmaceutico = farmaceutico;
    }

    public void adicionarEnfermeiro(Enfermeiro enfermeiro) {
        enfermeiros.add(enfermeiro);
    }

    public void adicionarMedicamento(Medicamento medicamento) {
        medicamentos.add(medicamento);
    }

    public void listarEnfermeiros() {
        System.out.println("Enfermeiros da enfermaria:");
        if (enfermeiros.isEmpty()) {
            System.out.println("Nenhum enfermeiro disponível.");
        } else {
            enfermeiros.forEach(e -> System.out.println("- " + e.getNome()));
        }
    }

    public List<Medicamento> listarMedicamentos() {
        return new ArrayList<>(medicamentos);
    }

    public void medicarPaciente(Medicamento medicamento, Paciente paciente) {
        if (!medicamentos.contains(medicamento)) {
            System.out.println("Medicamento \"" + medicamento.getNome() + "\" não disponível na enfermaria.");
            return;
        }
        System.out.println("Administrando \"" + medicamento.getNome()
                + "\" ao paciente " + paciente.getNome());
        paciente.administrarMedicamento(medicamento);
    }

    public Farmaceutico getFarmaceutico() {
        return farmaceutico;
    }

    public void setFarmaceutico(Farmaceutico farmaceutico) {
        this.farmaceutico = farmaceutico;
    }

    public List<Enfermeiro> getEnfermeiros() {
        return enfermeiros;
    }

    public void setEnfermeiros(List<Enfermeiro> enfermeiros) {
        this.enfermeiros = enfermeiros;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }
}
