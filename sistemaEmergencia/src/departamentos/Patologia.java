package departamentos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entidades.Exame;
import funcionarios.Enfermeiro;

public class Patologia {
    private List<Exame> exames;
    private List<Enfermeiro> enfermeiros;

    public Patologia() {
        this.exames = new ArrayList<>();
        this.enfermeiros = new ArrayList<>();
    }

    public Patologia(List<Enfermeiro> enfermeiros) {
        this();
        if (enfermeiros != null) {
            this.enfermeiros.addAll(enfermeiros);
        }
    }

    public void registrarExame(Exame exame) {
        if (exame == null) {
            System.out.println("Exame inválido.");
            return;
        }
        exames.add(exame);
        System.out.println("Exame '" + exame.getNome() + "' registrado em Patologia.");
    }

    public List<Exame> listarExames() {
        return Collections.unmodifiableList(exames);
    }

    public void adicionarEnfermeiro(Enfermeiro enfermeiro) {
        if (enfermeiro != null) {
            enfermeiros.add(enfermeiro);
        }
    }

    public List<Enfermeiro> listarEnfermeiros() {
        return Collections.unmodifiableList(enfermeiros);
    }
}
