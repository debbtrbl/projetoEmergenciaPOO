package departamentos;

import java.util.ArrayList;
import java.util.List;

import entidades.Leito;
import funcionarios.Medico;
import funcionarios.AuxiliarLimpeza;
import funcionarios.Enfermeiro;

public class Internacao {
    private List<Leito> leitos;
    private List<Leito> leitosDisponiveis;
    private List<Leito> leitosOcupados;
    private Medico medicoResponsavel;
    private List<Enfermeiro> enfermeiros;
    private AuxiliarLimpeza auxiliarLimpeza;

    public Internacao(List<Leito> leitos,
                      Medico medicoResponsavel,
                      List<Enfermeiro> enfermeiros,
                      AuxiliarLimpeza auxiliarLimpeza) {
        this.leitos = new ArrayList<>(leitos);
        this.leitosDisponiveis = new ArrayList<>(leitos);
        this.leitosOcupados = new ArrayList<>();
        this.medicoResponsavel = medicoResponsavel;
        this.enfermeiros = enfermeiros != null
                         ? new ArrayList<>(enfermeiros)
                         : new ArrayList<>();
        this.auxiliarLimpeza = auxiliarLimpeza;
    }


    public void ocuparLeito() {
        if (leitosDisponiveis.isEmpty()) {
            System.out.println("Nenhum leito disponível no momento.");
            return;
        }
        Leito leito = leitosDisponiveis.remove(0);
        leitosOcupados.add(leito);
        System.out.println("Leito " + leito.getNumero() + " agora está ocupado.");
    }

    public void limparLeito() {
        if (leitosDisponiveis.isEmpty()) {
            System.out.println("Não há leitos livres para limpar.");
            return;
        }
        System.out.println("Iniciando limpeza dos leitos disponíveis...");
        for (Leito leito : leitosDisponiveis) {
            auxiliarLimpeza.limpar(leito);
        }
        System.out.println("Limpeza concluída em todos os leitos livres.");
    }

    public List<Leito> getLeitos() {
        return leitos;
    }

    public List<Leito> getLeitosDisponiveis() {
        return leitosDisponiveis;
    }

    public List<Leito> getLeitosOcupados() {
        return leitosOcupados;
    }

    public Medico getMedicoResponsavel() {
        return medicoResponsavel;
    }

    public void setMedicoResponsavel(Medico medicoResponsavel) {
        this.medicoResponsavel = medicoResponsavel;
    }

    public List<Enfermeiro> getEnfermeiros() {
        return enfermeiros;
    }

    public void setEnfermeiros(List<Enfermeiro> enfermeiros) {
        this.enfermeiros = enfermeiros;
    }

    public AuxiliarLimpeza getAuxiliarLimpeza() {
        return auxiliarLimpeza;
    }

    public void setAuxiliarLimpeza(AuxiliarLimpeza auxiliarLimpeza) {
        this.auxiliarLimpeza = auxiliarLimpeza;
    }
}
